package springapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import springapp.business.IResearcherManager;
import springapp.model.Researcher;

@Service
public class ResearcherValidator implements Validator{
	
	@Autowired
	IResearcherManager researcherManager;

	@Override
	public boolean supports(Class<?> clazz) {
		return Researcher.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Researcher researcher = (Researcher) target;
		
		//(String email, String firstName, String lastName,  String website, Date birthDay, String password, Role role)
		//role unused
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "researcher.email", "Field email is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "researcher.firstName", "Field firstName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "researcher.lastName", "Field lastName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDay", "researcher.birthDay", "Field birthDay is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "researcher.password", "Field password is required.");
        if(researcherManager.hasResearcher(researcher.getEmail()))
        	errors.rejectValue("email", "researcher.email", "Email already used");
        
	}

}
