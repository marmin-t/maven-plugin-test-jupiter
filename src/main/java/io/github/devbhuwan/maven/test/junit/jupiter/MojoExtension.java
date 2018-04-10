package io.github.devbhuwan.maven.test.junit.jupiter;

/**
 * @author Bhuwan Prasad Upadhyay
 */
class MojoExtension extends MavenPluginExtension {
    MojoExtension() {
        super(MojoJunitConfig.class);
    }
}
