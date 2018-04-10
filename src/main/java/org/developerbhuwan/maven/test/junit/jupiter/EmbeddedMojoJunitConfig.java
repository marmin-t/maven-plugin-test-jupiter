package org.developerbhuwan.maven.test.junit.jupiter;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Bhuwan Prasad Upadhyay
 */
@ExtendWith(EmbeddedMojoExtension.class)
@Documented
@Inherited
@Retention(RUNTIME)
@Target(TYPE)
public @interface EmbeddedMojoJunitConfig {

}  
