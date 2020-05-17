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
import springapp.model.Researcher;

@Controller()
public class EventsController{

	@Autowired
	IEventManager eventManager;
	
	@Autowired
	IResearcherManager researcherManager;
	
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "events")
    public ModelAndView listEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		if(request.getSession().getAttribute("userId") == null)
        		return new ModelAndView("redirect:/login.jsp");
    	} catch (Exception e) {
    		return new ModelAndView("redirect:/login.jsp");
    	}
        logger.info("List of events (actives)");
        
        if(request.getSession().getAttribute("researcher") == null)
        {
	        Researcher currResearcher = researcherManager.getResearcher((Long)request.getSession().getAttribute("userId"));
	        logger.info(currResearcher.getRole());
	        request.getSession().setAttribute("researcher",currResearcher);
        }
        
        Collection<Event> events = eventManager.findActive();
        logger.info(events.toString());
        ModelAndView modelAndView = new ModelAndView();
        request.getSession().setAttribute("events", events);
        modelAndView.setViewName("events");
        return modelAndView;
    }

}