package springapp.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.business.IResearcherManager;
import springapp.model.Researcher;
import springapp.model.utils.Role;

@Controller()
public class RegisterController {

	@Autowired
	IResearcherManager researcherManager;
	@Autowired
	ResearcherValidator validator;
	
    protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "register")
    public String register(Model model) {
        logger.info("Register");
        model.addAttribute("register", new Researcher());
        return "register";
        /*
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
        */
    }
	
	@ModelAttribute
    public Researcher newResearcher() {
		Researcher researcher = new Researcher("","","","",new Date(), "", Role.USER);
        logger.info("New empty researcher created");
        return researcher;
    }
}
