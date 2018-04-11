package io.github.devbhuwan.maven.test.junit.jupiter;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@EmbeddedMojoJunitConfig
class EmbeddedMojoJUnitConfigUnitTests {

    @MojoTest
    void definedCorrectPropertyValuesWithMojoThenBuildSuccessful(PluginMojo mojo) throws Exception {
        mojo.begin()
                .execute("package").assertErrorFreeLog();
    }

    @MojoTest
    void buildSuccessUsingClassLevelMojo(PluginMojo mojo) throws Exception {
        mojo.begin().execute("verify").assertErrorFreeLog();
    }

}