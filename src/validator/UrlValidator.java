package validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<UrlCheck, String> {

	private String urlPrefix;
	
	@Override
	public void initialize(UrlCheck urlCheck) {
		urlPrefix = urlCheck.startValue();
	}
	
	@Override
	public boolean isValid(String url, 
			ConstraintValidatorContext constraintValidatorContext) {
		
		if (url != null) {
			if (url.startsWith(urlPrefix) == false) {
				return false;
			}
		}
		else {
			return false;
		}
		
		return true;
	}
		
}