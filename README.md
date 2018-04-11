# maven-plugin-test-jupiter

[![Build Status](https://travis-ci.org/devbhuwan/maven-plugin-test-jupiter.svg?branch=master)](https://travis-ci.org/devbhuwan/maven-plugin-test-jupiter)


# How to use

- Using `@EmbeddedMojoJunitConfig`

Example
```java
@EmbeddedMojoJunitConfig
class EmbeddedMojoJUnitConfigUnitTests {

    @MojoTest
    void definedCorrectPropertyValuesWithMojoThenBuildSuccessful(Mojo mojo) throws Exception {
        mojo.begin()
                .execute("your-plugin-goal").assertErrorFreeLog();
    }

    @MojoTest
    void buildSuccessUsingClassLevelMojo(Mojo mojo) throws Exception {
        mojo.begin().execute("your-plugin-goal").assertErrorFreeLog();
    }

}
```

- Using `@MojoJunitConfig` 
```java
@MojoJunitConfig
class MojoJUnitConfigUnitTests {

    @MojoTest(project = "src/projects/mojo-junit")
    void definedCorrectPropertyValuesWithMojoThenBuildSuccessful(Mojo mojo) throws Exception {
        mojo.begin()
                .execute("your-plugin-goal").assertErrorFreeLog();
    }

    @MojoTest(project = "src/projects/mojo-junit-2")
    void buildSuccessUsingClassLevelMojo(Mojo mojo) throws Exception {
        mojo.begin().execute("verify").assertErrorFreeLog();
    }
 
}
```
