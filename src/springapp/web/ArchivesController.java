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

import springapp.business.IEventManager;
import springapp.business.IResearcherManager;
import springapp.model.Event;

@Controller()
public class ArchivesController {

	@Autowired
	IEventManager eventManager;
	
	@Autowired
	IResearcherManager researcherManager;
	
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "archives")
    public ModelAndView listArchives(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		if(request.getSession().getAttribute("userId") == null)
    			return new ModelAndView("redirect:/login.jsp");
    	} catch (Exception e) {
    		return new ModelAndView("redirect:/login.jsp");
    	}
    	
    	Collection<Event> events;
    	try {
    		int yearResearched = Integer.parseInt(request.getParameter("year"));
    		logger.info("List of events (archive) year " + yearResearched);
    		events = eventManager.findArchiveAtYear(yearResearched);
    	}
    	catch (Exception e)
    	{
    		events = eventManager.findLastArchives(5);
    		logger.info("List of events (archive) last " + events.size());
    	}        
        
        logger.info(events.toString());
        ModelAndView modelAndView = new ModelAndView();
        request.getSession().setAttribute("events", events);
        modelAndView.setViewName("archives");
        return modelAndView;
    }

}
