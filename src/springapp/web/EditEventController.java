package springapp.web;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
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
public class EditEventController {

	@Autowired
	IEventManager eventManager;
	@Autowired
	IResearcherManager researcherManager;
	
	@RequestMapping(value = "/edit-event", method = RequestMethod.GET)
    public ModelAndView eventEditForm(Model model,HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "eventId", required = true) long eventId) throws ServletException, IOException {
		Researcher organizer = researcherManager.getResearcher((Long)request.getSession().getAttribute("userId"));
		Event curEvent = eventManager.find(eventId);
		
		try {
    		if(request.getSession().getAttribute("userId") == null || (request.getSession().getAttribute("userRole") != Role.ADMIN && (request.getSession().getAttribute("userRole") != Role.ORGANIZER || organizer.getLab().getLabId() != curEvent.getEventId())))
        		return new ModelAndView("redirect:/events.jsp");
    	} catch (Exception ex) {
    		return new ModelAndView("redirect:/events.jsp");
    	}
        
        request.getSession().setAttribute("event", curEvent);
        model.addAttribute("event",curEvent);
		
		return new ModelAndView("eventEdit");
    }
	
	@RequestMapping(value = "/edit-event", method = RequestMethod.POST)
	public ModelAndView editEvent(HttpServletRequest request,@ModelAttribute @Valid Event e,@RequestParam(value = "eventId", required = true) long eventId) {

		Researcher organizer = researcherManager.getResearcher((Long)request.getSession().getAttribute("userId"));
		Event curEvent = eventManager.find(eventId);
		
		try {
    		if(request.getSession().getAttribute("userId") == null || (request.getSession().getAttribute("userRole") != Role.ADMIN && (request.getSession().getAttribute("userRole") != Role.ORGANIZER || organizer.getLab().getLabId() != curEvent.getEventId())))
        		return new ModelAndView("redirect:/events.jsp");
    	} catch (Exception ex) {
    		return new ModelAndView("redirect:/events.jsp");
    	}
		
		
		curEvent.setBeginDate(e.getBeginDate());
		curEvent.setEndDate(e.getEndDate());
		curEvent.setEventName(new String(e.getEventName().getBytes(),Charset.forName("UTF-8")));
		curEvent.setDescription(new String(e.getDescription().getBytes(),Charset.forName("UTF-8")));
		curEvent.setType(e.getType());
		curEvent.setFee(e.getFee());
		curEvent.setLocation(new String(e.getLocation().getBytes(),Charset.forName("UTF-8")));
		curEvent.setSpeakers(Stream.of(e.getSpeakers().toString().replace("[", "").replace("]", "")).collect(Collectors.toList()));
		curEvent.setAttendeeCap(e.getAttendeeCap());
		
		eventManager.update(curEvent);
		
		return new ModelAndView("redirect:/events.jsp");
    }
	
	@RequestMapping(value = "editevent-searchResearcher")
    public ModelAndView listResearcherSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Researcher organizer = researcherManager.getResearcher((Long)request.getSession().getAttribute("userId"));
		Event curEvent = (Event)request.getSession().getAttribute("event");
		
		try {
    		if(request.getSession().getAttribute("userId") == null || (request.getSession().getAttribute("userRole") != Role.ADMIN && (request.getSession().getAttribute("userRole") != Role.ORGANIZER || organizer.getLab().getLabId() != curEvent.getEventId())))
        		return new ModelAndView("redirect:/events.jsp");
    	} catch (Exception ex) {
    		return new ModelAndView("redirect:/events.jsp");
    	}

    	String researcherToFind = "";
    	try {
    		researcherToFind = request.getParameter("researcher");
    		Collection<Researcher> researchers = researcherManager.findResearchers(researcherToFind);
    		if(researchers == null) {
                return new ModelAndView("redirect:/actions/edit-event?eventId="+curEvent.getEventId());
    		}
    		Iterator<Researcher> itr = curEvent.getAttendees().iterator();
    		while (itr.hasNext()) {
    			Researcher element = itr.next();
    		    researchers = researchers
    					.stream()
    					.filter(r -> r.getResearcherId() != element.getResearcherId())
    					.collect(Collectors.toCollection(HashSet::new));
    		}
    		request.getSession().setAttribute("researchersFound", researchers);
    	}
    	catch (Exception e)
    	{} 
        return new ModelAndView("redirect:/actions/edit-event?eventId="+curEvent.getEventId());
    }
	
	@RequestMapping(value = "/event-remove-user", method = RequestMethod.GET)
	public ModelAndView removeFromEvent(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "researcherId", required = true) long researcherId, @RequestParam(value = "eventId", required = true) long eventId) {

		Researcher organizer = researcherManager.getResearcher((Long)request.getSession().getAttribute("userId"));
		Event curEvent = eventManager.find(eventId);
		
		try {
    		if(request.getSession().getAttribute("userId") == null || (request.getSession().getAttribute("userRole") != Role.ADMIN && (request.getSession().getAttribute("userRole") != Role.ORGANIZER || organizer.getLab().getLabId() != curEvent.getEventId())))
        		return new ModelAndView("redirect:/events.jsp");
    	} catch (Exception ex) {
    		return new ModelAndView("redirect:/events.jsp");
    	}
		
		Set<Researcher> setOfAttendees = curEvent.getAttendees();
		Set<Researcher> collectionOfAttendees = setOfAttendees
				.stream()
				.filter(r -> r.getResearcherId() != researcherId)
				.collect(Collectors.toSet());
		
		curEvent.setAttendees(collectionOfAttendees);
		
		eventManager.update(curEvent);
		
		request.getSession().setAttribute("event", curEvent);

		return new ModelAndView("redirect:/actions/edit-event?eventId="+curEvent.getEventId());
    }
	@RequestMapping(value = "/event-add-user", method = RequestMethod.GET)
	public ModelAndView addToEvent(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "researcherId", required = true) long researcherId, @RequestParam(value = "eventId", required = true) long eventId) {

		Researcher organizer = researcherManager.getResearcher((Long)request.getSession().getAttribute("userId"));
		Event curEvent = eventManager.find(eventId);
		
		try {
    		if(request.getSession().getAttribute("userId") == null || (request.getSession().getAttribute("userRole") != Role.ADMIN && (request.getSession().getAttribute("userRole") != Role.ORGANIZER || organizer.getLab().getLabId() != curEvent.getEventId())))
        		return new ModelAndView("redirect:/events.jsp");
    	} catch (Exception ex) {
    		return new ModelAndView("redirect:/events.jsp");
    	}
				
		curEvent.addAttendee(researcherManager.getResearcher(researcherId));
		eventManager.update(curEvent);
		
		request.getSession().setAttribute("event", curEvent);
		
		return new ModelAndView("redirect:/actions/edit-event?eventId="+curEvent.getEventId());
    }
	
}
