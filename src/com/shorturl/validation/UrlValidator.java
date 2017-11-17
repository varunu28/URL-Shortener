package com.shorturl.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<UrlStart, String> {

	private String urlPrefix;
	
	@Override
	public void initialize(UrlStart urlStart) {
		urlPrefix = urlStart.value();
	}
	
	@Override
	public boolean isValid(String theCode, 
			ConstraintValidatorContext constraintValidatorContext) {
		
		boolean result = false;
		
		if (theCode != null) {
			result = theCode.startsWith(urlPrefix);
		}
		else {
			result = true;
		}
	
		return result;
	}
		
}
