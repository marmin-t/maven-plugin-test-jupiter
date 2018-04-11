package io.github.devbhuwan.maven.test.junit.jupiter;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@MojoJunitConfig
class MojoJUnitConfigUnitTests {

    @MojoTest(project = "src/projects/mojo-junit")
    void definedCorrectPropertyValuesWithMojoThenBuildSuccessful(PluginMojo mojo) throws Exception {
        mojo.begin()
                .execute("package").assertErrorFreeLog();
    }

    @MojoTest(project = "src/projects/mojo-junit-2")
    void buildSuccessUsingClassLevelMojo(PluginMojo mojo) throws Exception {
        mojo.begin().execute("verify").assertErrorFreeLog();
    }

}