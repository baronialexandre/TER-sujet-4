package springapp.web;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import springapp.business.IEventManager;
import springapp.business.IResearcherManager;
import springapp.model.Event;
import springapp.model.Researcher;
import springapp.model.utils.Role;

	@Controller()
	public class CreateEventController {

		@Autowired
		IEventManager eventManager;
		@Autowired
		IResearcherManager researcherManager;
		
		@RequestMapping(value = "/create-event", method = RequestMethod.GET)
	    public ModelAndView eventCreateForm(Model model,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
	    		if(request.getSession().getAttribute("userId") == null || (request.getSession().getAttribute("userRole") != Role.ADMIN && request.getSession().getAttribute("userRole") != Role.ORGANIZER))
	        		return new ModelAndView("redirect:/login.jsp");
	    	} catch (Exception ex) {
	    		return new ModelAndView("redirect:/login.jsp");
	    	}
	        
			
	        model.addAttribute("event",new Event());
			
			return new ModelAndView("eventCreate");
	    }
		
		@RequestMapping(value = "/create-event", method = RequestMethod.POST)
		public ModelAndView createEvent(HttpServletRequest request,@ModelAttribute @Valid Event e) {
			
			try {
	    		if(request.getSession().getAttribute("userId") == null || (request.getSession().getAttribute("userRole") != Role.ADMIN && request.getSession().getAttribute("userRole") != Role.ORGANIZER))
	        		return new ModelAndView("redirect:/login.jsp");
	    	} catch (Exception ex) {
	    		return new ModelAndView("redirect:/login.jsp");
	    	}

			Event curEvent = new Event();
			
			
			curEvent.setBeginDate(e.getBeginDate());
			curEvent.setEndDate(e.getEndDate());
			curEvent.setEventName(new String(e.getEventName().getBytes(),Charset.forName("UTF-8")));
			curEvent.setDescription(new String(e.getDescription().getBytes(),Charset.forName("UTF-8")));
			curEvent.setType(e.getType());
			curEvent.setFee(e.getFee());
			curEvent.setLocation(new String(e.getLocation().getBytes(),Charset.forName("UTF-8")));
			curEvent.setSpeakers(Stream.of(e.getSpeakers().toString().replace("[", "").replace("]", "")).collect(Collectors.toList()));
			curEvent.setAttendeeCap(e.getAttendeeCap());
			
			Researcher organizer = researcherManager.getResearcher((Long)request.getSession().getAttribute("userId"));
			
			curEvent.setOrganizer(organizer);
			
			eventManager.add(curEvent);
			
			return new ModelAndView("redirect:/actions/events");
	    }
}
