package org.developerbhuwan.maven.test.junit.jupiter;

import io.takari.maven.testing.TestProperties;
import io.takari.maven.testing.executor.MavenExecution;
import io.takari.maven.testing.executor.MavenRuntime;
import lombok.Getter;
import lombok.NonNull;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import static io.takari.maven.testing.TestProperties.PROP_LOCAL_REPOSITORY;
import static java.lang.System.getenv;
import static java.util.List.of;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.reflect.FieldUtils.getFieldsListWithAnnotation;
import static org.apache.commons.lang3.reflect.FieldUtils.writeField;
import static org.developerbhuwan.maven.test.junit.jupiter.MavenRuntimeTestContextManager.PathDiscovery.*;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@Getter
class MavenRuntimeTestContextManager {

    private static final Map<Class<? extends Annotation>, Function<File, MavenRuntime>> FACTORIES = new LinkedHashMap<>();

    static {
        FACTORIES.put(MojoJunitConfig.class, (mavenHome) -> MavenRuntime.builder(mavenHome, null).forkedBuilder().build());
        FACTORIES.put(EmbeddedMojoJunitConfig.class, (mavenHome) -> {
            try {
                return MavenRuntime.builder(mavenHome, null).build();
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        });
    }

    private final Mojo mojo;

    private MavenRuntimeTestContextManager(@NonNull String project, @NonNull Class<? extends Annotation> junitConfigClass) throws IllegalAccessException {
        final File mavenHome = new File(discoverMavenHome());
        final String localRepository = discoverMavenLocalRepository();
        if (!mavenHome.exists())
            throw new IllegalArgumentException(String.format("Please defined %s, %s environment variable or %s in system properties", MAVEN_HOME, M2_HOME, MAVEN_DOT_HOME));
        logs(mavenHome, localRepository);
        MavenRuntime runtime = FACTORIES.get(junitConfigClass).apply(mavenHome);
        injectProperties(localRepository, runtime);
        MavenExecution mavenExecution = runtime.forProject(new File(project));
        this.mojo = new Mojo(mavenExecution);
    }

    static MavenRuntimeTestContextManager create(@NonNull String project, @NonNull Class<? extends Annotation> junitConfigClass) {
        try {
            return new MavenRuntimeTestContextManager(project, junitConfigClass);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void injectProperties(String localRepository, MavenRuntime runtime) throws IllegalAccessException {
        TestProperties testProperties = new TestProperties();
        Map<String, String> properties = new HashMap<>();
        properties.put(PROP_LOCAL_REPOSITORY, localRepository);
        writeField(testProperties, "properties", properties, true);
        writeField(runtime, "properties", testProperties, true);
    }

    private void logs(File mavenHome, String localRepository) {
        System.out.println("---------------------MOJO---------------------------");
        System.out.println("Maven Home: " + mavenHome.getAbsolutePath());
        System.out.println("Maven Local Repository: " + localRepository);
        System.out.println("----------------------------------------------------");
        System.out.println();
    }

    void initializeMojoContexts(Object testInstance) {
        ofNullable(getFieldsListWithAnnotation(testInstance.getClass(), MojoContext.class))
                .orElse(of())
                .forEach(field -> {
                    try {
                        writeField(field, testInstance, getMojo(), true);
                    } catch (IllegalAccessException e) {
                        throw new IllegalCallerException(e);
                    }
                });
    }

    static class PathDiscovery {

        static final String MAVEN_HOME = "MAVEN_HOME";
        static final String M2_HOME = "M2_HOME";
        static final String MAVEN_DOT_HOME = "maven.home";

        static String discoverMavenHome() {
            return ofNullable(getenv(MAVEN_HOME)).orElse(tryToFindMavenHome());
        }

        private static String tryToFindMavenHome() {
            return ofNullable(getenv(M2_HOME)).orElse(System.getProperty(MAVEN_DOT_HOME));
        }

        static String discoverMavenLocalRepository() {
            return getenv("HOME") + "/.m2/repository";
        }

    }
}
