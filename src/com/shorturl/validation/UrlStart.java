package com.shorturl.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UrlValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlStart {

	public String value() default "www";
	
	public String message() default "must start with www";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default {};
}