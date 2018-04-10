package org.developerbhuwan.maven.test.junit.jupiter;

/**
 * @author Bhuwan Prasad Upadhyay
 */
class EmbeddedMojoExtension extends MavenPluginExtension {

    EmbeddedMojoExtension() {
        super(EmbeddedMojoJunitConfig.class);
    }
}
