package springapp.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springapp.business.IPersonManager;
import springapp.model.Person;

@Controller()
public class ProfileController {
	
	@Autowired
	IPersonManager personManager;

    protected final Log logger = LogFactory.getLog(getClass());
    
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public ModelAndView getProfile(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	long personId;
    	try {
    		personId = Long.parseLong(request.getParameter("personId"));
    		} catch (Exception e) {
    		personId=-1;
    	}
    	if(personId == -1) {
    		try {
        		personId = (long) request.getSession().getAttribute("userId");
        	} catch (Exception e) {
        		return new ModelAndView("redirect:/login.jsp");
        	}
    	}
    	
    	Person person = personManager.getPerson(personId);
    	request.getSession().setAttribute("person", person);
    	
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profile.jsp");
        return modelAndView;
    }
}