# maven-plugin-test-jupiter

[![Build Status](https://travis-ci.org/devbhuwan/maven-plugin-test-jupiter.svg?branch=master)](https://travis-ci.org/devbhuwan/maven-plugin-test-jupiter)


### How to use

- Maven Dependency
```xml
<!-- https://mvnrepository.com/artifact/io.github.devbhuwan/maven-plugin-test-jupiter -->
<dependency>
    <groupId>io.github.devbhuwan</groupId>
    <artifactId>maven-plugin-test-jupiter</artifactId>
    <version>1.0.2.RELEASE</version>
</dependency>
```

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
