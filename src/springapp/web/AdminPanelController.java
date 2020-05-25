package springapp.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import springapp.business.ILabManager;
import springapp.business.IResearcherManager;
import springapp.model.Lab;
import springapp.model.Researcher;
import springapp.model.utils.Role;

@Controller()
public class AdminPanelController {
	@Autowired
	ILabManager labManager;
	@Autowired
	IResearcherManager researcherManager;
	
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "admin-panel")
    public ModelAndView listAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		if(request.getSession().getAttribute("userId") == null && request.getSession().getAttribute("userRole") != Role.ADMIN)
        		return new ModelAndView("redirect:/login.jsp");
    	} catch (Exception e) {
    		return new ModelAndView("redirect:/login.jsp");
    	}
        logger.info("List of Labs with Researchers");
        
        Collection<Lab> labs = labManager.findAll();
        
        ModelAndView modelAndView = new ModelAndView("admin-panel");
        request.getSession().setAttribute("labs", labs);
        request.getSession().setAttribute("researchersWithoutLab", researcherManager.getResearchersWithNoLab());
        return modelAndView;
    }
    
    @RequestMapping(value = "admin-panel-searchResearcher")
    public ModelAndView listResearcherSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		if(request.getSession().getAttribute("userId") == null && request.getSession().getAttribute("userRole") != Role.ADMIN)
        		return new ModelAndView("redirect:/login.jsp");
    	} catch (Exception e) {
    		return new ModelAndView("redirect:/login.jsp");
    	}
    	
    	String researcherToFind = "";
    	try {
    		researcherToFind = request.getParameter("researcher");
    		Collection<Researcher> researchers = researcherManager.findResearchers(researcherToFind);
    		if(researchers == null) {
    			String error = "Nobody with the firstname/lastname " + researcherToFind;
        		request.getSession().setAttribute("adminPanelError", error);
                return new ModelAndView(new RedirectView("admin-panel"));
    		}
    		logger.info("researcherToFind : " + researcherToFind);
    		request.getSession().setAttribute("researchersFound", researchers);
    	}
    	catch (Exception e)
    	{
    		String error = "There is no researcher called " + researcherToFind + " in the database";
    		request.getSession().setAttribute("adminPanelError", error);
            return new ModelAndView(new RedirectView("admin-panel"));
    	} 
        logger.info("Researchers with name or lastname " + researcherToFind);
                
        ModelAndView modelAndView = new ModelAndView(new RedirectView("admin-panel"));
        return modelAndView;
    }
    
    @RequestMapping(value = "admin-panel-AddLab")
    public ModelAndView addLab(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		if(request.getSession().getAttribute("userId") == null && request.getSession().getAttribute("userRole") != Role.ADMIN)
        		return new ModelAndView("redirect:/login.jsp");
    	} catch (Exception e) {
    		return new ModelAndView("redirect:/login.jsp");
    	}
        //test si le lab existe ou pas
    	String labName = "";
    	try {
    		labName = request.getParameter("labName");
    		if(labManager.findByName(labName) != null) {
    			String error = "The lab " + labName + " exists";
        		request.getSession().setAttribute("adminPanelError", error);
                return new ModelAndView(new RedirectView("admin-panel"));
    		}
    		logger.info("LabName : " + labName);
    		Lab lab = new Lab(labName);
    		labManager.add(lab);
    	}
    	catch (Exception e)
    	{
    		String error = "Enter a valid lab name";
    		request.getSession().setAttribute("adminPanelError", error);
            return new ModelAndView(new RedirectView("admin-panel"));
    	} 
    	
    	String success = "You created the lab : " + labName;
		request.getSession().setAttribute("adminPanelSuccess", success);
        return new ModelAndView(new RedirectView("admin-panel"));
    }
    
    @RequestMapping(value = "admin-panel-removeLab")
    public ModelAndView removeLab(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		if(request.getSession().getAttribute("userId") == null && request.getSession().getAttribute("userRole") != Role.ADMIN)
        		return new ModelAndView("redirect:/login.jsp");
    	} catch (Exception e) {
    		return new ModelAndView("redirect:/login.jsp");
    	}
        //test si le lab existe ou pas
    	try {
    		int labId = Integer.parseInt(request.getParameter("labId"));
    		if(labManager.find(labId) == null) {
    			String error = "The lab " + labId + " doesn't exists";
        		request.getSession().setAttribute("adminPanelError", error);
                return new ModelAndView(new RedirectView("admin-panel"));
    		}
    		logger.info("LabName : " + labId);
    		labManager.delete(labId);
    	}
    	catch (Exception e)
    	{
    		String error = "This lab can't be delete";
    		request.getSession().setAttribute("adminPanelError", error);
            return new ModelAndView(new RedirectView("admin-panel"));
    	} 
    	
    	String success = "You successfully deleted a lab";
		request.getSession().setAttribute("adminPanelSuccess", success);
        return new ModelAndView("redirect:admin-panel");
    }
}
