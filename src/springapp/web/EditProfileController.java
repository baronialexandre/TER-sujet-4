package springapp.web;

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
import springapp.business.IResearcherManager;
import springapp.model.Researcher;

@Controller()
public class EditProfileController {
	@Autowired
	IResearcherManager researcherManager;
	@Autowired
	ResearcherValidator validator;
	
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
    public ModelAndView submit(@Valid @ModelAttribute("researcher")Researcher researcher, HttpServletRequest request) {
        logger.info("edit profile " + researcher);
		//request.getSession().setAttribute("disableMenu", true);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/editProfile");
        modelAndView.addObject(researcher);
        return modelAndView;
    }
    
    @ModelAttribute
    public Researcher newResearcher(
        @RequestParam(value = "researcherId", required = true) Integer researcherNumber) {
        logger.info("find researcher " + researcherNumber);
        return researcherManager.getResearcher(researcherNumber);
    }
    
    @RequestMapping(value = "save-profile", method = RequestMethod.POST)
    public ModelAndView saveEditProfile(@Valid @ModelAttribute("researcher")Researcher researcher, BindingResult result, HttpServletRequest request) {
        logger.info("Save Edit Profile");
        Researcher resear = researcher;
        validator.validate(researcher, result);
        if (result.hasErrors()) {
            logger.info("Epic fail - "+ researcher.getBirthDay());
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/edit");
            modelAndView.addObject(resear);
    		request.getSession().setAttribute("disableMenu", true);
            return modelAndView;
        }
        researcherManager.update(researcher);
        
        System.out.println(researcher.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:profile?researcherId="+researcher.getResearcherId());
        return modelAndView;
    }
    
}