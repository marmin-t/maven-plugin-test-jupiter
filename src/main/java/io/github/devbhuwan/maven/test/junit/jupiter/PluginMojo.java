package io.github.devbhuwan.maven.test.junit.jupiter;

import io.takari.maven.testing.executor.MavenExecution;
import lombok.NonNull;

/**
 * @author Bhuwan Prasad Upadhyay
 */
public class PluginMojo {

    private final MavenExecution exec;

    PluginMojo(@NonNull MavenExecution exec) {
        this.exec = exec;
    }

    public MavenExecution begin() {
        return exec;
    }
}
