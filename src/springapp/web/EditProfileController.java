package springapp.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import springapp.business.IPersonManager;
import springapp.model.Person;
import springapp.model.utils.User;

@Controller()
public class EditProfileController {
	@Autowired
	IPersonManager personManager;
	@Autowired
	PersonValidator validator;
	
    protected final Log logger = LogFactory.getLog(getClass());

    /*
    @RequestMapping(value = "edit-profile")
    public ModelAndView loadEditProfile(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        logger.info("Load Edit Profile");
        
    	long personId = -1;
    	try {
			personId = (long) request.getSession().getAttribute("userId");
		} catch (Exception e2e) {
			e2e.printStackTrace();
		}
    	
    	Person person = personManager.getPerson(personId);
        
        ModelAndView modelAndView = new ModelAndView();
        request.getSession().setAttribute("person", person);
        
        modelAndView.setViewName("redirect:/edit-profile.jsp");
        return modelAndView;
    }*/
    
    /*
    @RequestMapping(value = "save-profile", method = RequestMethod.POST)
    public ModelAndView saveEditProfile(HttpServletRequest request,
            HttpServletResponse response) {
        logger.info("Save Edit Profile");
        
        long personId = -1;
    	try {
			personId = (long) request.getSession().getAttribute("userId");
		} catch (Exception e2e) {
			e2e.printStackTrace();
		}
    	
    	Person person = personManager.getPerson(personId);
        
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String website = request.getParameter("website");
        String birthDay = request.getParameter("birthDay");
        
        
        person.setUsername(username);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setWebsite(website);
        try {
        	if(!birthDay.contains("-")) throw new Exception("Date error");
        	Date birthDayDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDay);  
        	person.setBirthDay(birthDayDate);
        } catch(Exception e) {
        	
        }

        personManager.update(person);
        request.getSession().setAttribute("person", person);
        
        System.out.println(person.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile.jsp");
        return modelAndView;
    }*/
    
     ///////////////////////////////////////////
    @RequestMapping(value = "edit-profile", method = RequestMethod.GET)
    public ModelAndView submit(@Valid @ModelAttribute("person")Person person, HttpServletRequest request) {
        logger.info("edit profile " + person);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/edit");
        modelAndView.addObject(person);
		request.getSession().setAttribute("disableMenu", true);
        return modelAndView;
    }
    
    @ModelAttribute
    public Person newPerson(
        @RequestParam(value = "id", required = true) Integer personNumber) {
        logger.info("find person " + personNumber);
        return personManager.getPerson(personNumber);
    }
    
    @RequestMapping(value = "save-profile", method = RequestMethod.POST)
    public ModelAndView saveEditProfile(@Valid @ModelAttribute("person")Person person, BindingResult result, HttpServletRequest request) {
        logger.info("Save Edit Profile");
        Person pers = person;
        validator.validate(person, result);
        if (result.hasErrors()) {
            logger.info("Epic fail - "+ person.getBirthDay());
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/edit");
            modelAndView.addObject(pers);
    		request.getSession().setAttribute("disableMenu", true);
            return modelAndView;
        }
        personManager.update(person);
        
        System.out.println(person.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:profile?personId="+person.getPersonId());
        return modelAndView;
    }
    
    
    
}