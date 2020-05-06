package springapp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import springapp.business.IPersonManager;
import springapp.model.Person;

@Service
public class PersonValidator implements Validator{
	
	@Autowired
	IPersonManager personManager;

	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Person person = (Person) target;
		
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "person.username", "Field username is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "person.password", "Field password is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "person.firstName", "Field firstName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "person.lastName", "Field lastName is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "person.email", "Field email is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "person.website", "Field website is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDay", "person.birthDay", "Field birthDay is required.");
        
        if(!personManager.getPerson(person.getPersonId()).getUsername().equals(person.getUsername()) && personManager.hasPerson(person.getUsername()))
        	errors.rejectValue("username", "person.username", "Username already used");
	}

}
