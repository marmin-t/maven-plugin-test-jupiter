package org.developerbhuwan.maven.test.junit.jupiter;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@EmbeddedMojoJunitConfig
class EmbeddedMojoJUnitConfigUnitTests {

    @MojoTest(project = "src/projects/mojo-junit")
    void definedCorrectPropertyValuesWithMojoThenBuildSuccessful(Mojo mojo) throws Exception {
        mojo.begin()
                .execute("package").assertErrorFreeLog();
    }

    @MojoTest(project = "src/projects/mojo-junit-2")
    void buildSuccessUsingClassLevelMojo(Mojo mojo) throws Exception {
        mojo.begin().execute("verify").assertErrorFreeLog();
    }

}