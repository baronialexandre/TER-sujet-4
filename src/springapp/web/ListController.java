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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springapp.business.ITeamManager;
import springapp.model.Team;

@Controller()
public class ListController{

	@Autowired
	ITeamManager teamManager;
	
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "list")
    public ModelAndView listTeams(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	try {
    		if(request.getSession().getAttribute("userId") == null)
    			return new ModelAndView("redirect:/login.jsp");
    	} catch (Exception e) {
    		return new ModelAndView("redirect:/login.jsp");
    	}
        logger.info("List of teams");
        Collection<Team> teams = teamManager.findAll();
        logger.info(teams.toString());
        ModelAndView modelAndView = new ModelAndView();
        request.getSession().setAttribute("teams", teams);
        modelAndView.setViewName("redirect:/list.jsp");
        return modelAndView;
    }
    
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	String search = (String) request.getParameter("search");
        logger.info("Searching team containing: "+ search);
        Collection<Team> teams = teamManager.search(search);
        logger.info(teams.toString());
        ModelAndView modelAndView = new ModelAndView();
        request.getSession().setAttribute("teams", teams);
        modelAndView.setViewName("redirect:/list.jsp?search="+search);
        return modelAndView;
    }


}