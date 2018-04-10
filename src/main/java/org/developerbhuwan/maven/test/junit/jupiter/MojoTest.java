package org.developerbhuwan.maven.test.junit.jupiter;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@Test
@Documented
@Inherited
@Retention(RUNTIME)
@Target(METHOD)
public @interface MojoTest {

    String project() default EMPTY;

}
