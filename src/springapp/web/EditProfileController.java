package springapp.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.business.ILabManager;
import springapp.business.IResearcherManager;
import springapp.model.Lab;
import springapp.model.Researcher;
import springapp.model.utils.Role;

@Controller()
public class EditProfileController {
	@Autowired
	IResearcherManager researcherManager;
	@Autowired
	ResearcherValidator validator;
	
	@Autowired
	ILabManager labManager;
	
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
    public ModelAndView loadEditProfile(@Valid @ModelAttribute("researcher")Researcher researcher, HttpServletRequest request) {
        logger.info("edit profile " + researcher);
		request.getSession().setAttribute("disableMenu", false);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProfile");
        modelAndView.addObject(researcher);
        List<String> labs = labManager.getAllLabNames();
        request.getSession().setAttribute("labs", labs);
        System.out.print("loadEditProfile :: loaded");
        return modelAndView;
    }
    
    @ModelAttribute
    public Researcher newResearcher(
        @RequestParam(value = "researcherId", required = true) Integer researcherNumber) {
    	Researcher researcher = new Researcher("","","","",new Date(), "", Role.USER);
        researcher = researcherManager.getResearcher(researcherNumber);
        logger.info("find researcher " + researcherNumber);
        return researcher;
    }
    
    @RequestMapping(value = "change-admin-profile", method = RequestMethod.POST)
    public ModelAndView saveEditProfile(@Valid @ModelAttribute("researcher")Researcher researcher, BindingResult result, HttpServletRequest request) {
        logger.info("change-admin-profile");
        Researcher resear = researcher;
        validator.validate(researcher, result);
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("editProfile");
            modelAndView.addObject(resear);
            return modelAndView;
        }
        Lab lab = labManager.findByName((String) result.getFieldValue("lab.labName"));
        researcher.setLab(lab);
        researcherManager.update(researcher);
        System.out.println(researcher.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProfile");
        request.getSession().setAttribute("editProfileNotification", "Edited !");
        return modelAndView;
    }
    
}