/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigd.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
/**
 *
 * @author marceloclaudios
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserValidation.class)
public @interface UniqueUser {
  String message() default "{app.UniqueUser.message}";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}