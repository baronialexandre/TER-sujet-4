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

import springapp.business.ILabManager;
import springapp.model.Lab;
import springapp.model.utils.Role;

@Controller()
public class AdminPanelController {
	@Autowired
	ILabManager labManager;
	
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
        
        ModelAndView modelAndView = new ModelAndView();
        request.getSession().setAttribute("labs", labs);
        modelAndView.setViewName("admin-panel");
        return modelAndView;
    }

}
