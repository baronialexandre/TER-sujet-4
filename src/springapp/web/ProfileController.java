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

import springapp.business.IResearcherManager;
import springapp.model.Researcher;

@Controller()
public class ProfileController {
	
	@Autowired
	IResearcherManager researcherManager;

    protected final Log logger = LogFactory.getLog(getClass());
    
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public ModelAndView getProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	long researcherId;
    	try {
    		researcherId = Long.parseLong(request.getParameter("researcherId"));
    		} catch (Exception e) {
    		researcherId=-1;
    	}
    	if(researcherId == -1) {
    		try {
        		researcherId = (long) request.getSession().getAttribute("userId");
        	} catch (Exception e) {
        		return new ModelAndView("login");
        	}
    	}
    	
    	Researcher researcher = researcherManager.getResearcher(researcherId);
    	request.getSession().setAttribute("researcher", researcher);
    	
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        return modelAndView;
    }
}