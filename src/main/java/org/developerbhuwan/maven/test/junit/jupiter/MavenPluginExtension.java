package org.developerbhuwan.maven.test.junit.jupiter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.developerbhuwan.maven.test.junit.jupiter.MavenRuntimeTestContextManager.create;

/**
 * @author Bhuwan Prasad Upadhyay
 */
abstract class MavenPluginExtension implements ParameterResolver {

    private final Class<? extends Annotation> clazz;

    MavenPluginExtension(Class<? extends Annotation> clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean supportsParameter(ParameterContext ctx, ExtensionContext context) throws ParameterResolutionException {
        return ctx.getParameter().getType() == Mojo.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context) throws ParameterResolutionException {
        Method method = context.getRequiredTestMethod();
        return create(method.getAnnotation(MojoTest.class).project(), clazz).getMojo();
    }

}
