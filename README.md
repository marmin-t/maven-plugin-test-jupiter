# maven-plugin-test-jupiter

![Travis branch](https://img.shields.io/travis/devbhuwan/maven-plugin-test-jupiter.svg?style=for-the-badge) 
![Codecov](https://img.shields.io/codecov/c/github/devbhuwan/maven-plugin-test-jupiter.svg?style=for-the-badge)
![Github Releases](https://img.shields.io/github/downloads/devbhuwan/maven-plugin-test-jupiter/latest/total.svg?style=for-the-badge)

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
