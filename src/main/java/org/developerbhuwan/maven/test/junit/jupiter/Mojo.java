package org.developerbhuwan.maven.test.junit.jupiter;

import io.takari.maven.testing.executor.MavenExecution;
import lombok.NonNull;

/**
 * @author Bhuwan Prasad Upadhyay
 */
public class Mojo {

    private final MavenExecution exec;

    Mojo(@NonNull MavenExecution exec) {
        this.exec = exec;
    }

    public MavenExecution begin() {
        return exec;
    }
}
