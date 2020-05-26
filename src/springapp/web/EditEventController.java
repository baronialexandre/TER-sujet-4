package springapp.web;

import java.io.IOException;
import java.nio.charset.Charset;
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

import springapp.business.IEventManager;
import springapp.model.Event;
import springapp.model.Researcher;

@Controller()
public class EditEventController {

	@Autowired
	IEventManager eventManager;
	
	@RequestMapping(value = "/edit-event", method = RequestMethod.GET)
    public ModelAndView eventEditForm(Model model,HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "eventId", required = true) long eventId) throws ServletException, IOException {
		try {
    		if(request.getSession().getAttribute("userId") == null)
        		return new ModelAndView("redirect:/login.jsp");
    	} catch (Exception e) {
    		return new ModelAndView("redirect:/login.jsp");
    	}
		
		Event curEvent = eventManager.find(eventId);
        
        if(curEvent == null)
        {
        	return new ModelAndView("redirect:/events.jsp");
        }
        
        request.getSession().setAttribute("event", curEvent);
        model.addAttribute("event",curEvent);
		
		return new ModelAndView("eventEdit");
    }
	
	@RequestMapping(value = "/edit-event", method = RequestMethod.POST)
	public ModelAndView editEvent(HttpServletRequest request,@ModelAttribute @Valid Event e,@RequestParam(value = "eventId", required = true) long eventId) {

		Event curEvent = (Event)request.getSession().getAttribute("event");
		
		
		curEvent.setBeginDate(e.getBeginDate());
		curEvent.setEndDate(e.getEndDate());
		curEvent.setEventName(new String(e.getEventName().getBytes(),Charset.forName("UTF-8")));
		curEvent.setDescription(new String(e.getDescription().getBytes(),Charset.forName("UTF-8")));
		curEvent.setType(e.getType());
		curEvent.setFee(e.getFee());
		curEvent.setLocation(new String(e.getLocation().getBytes(),Charset.forName("UTF-8")));
		curEvent.setSpeakers(Stream.of(e.getSpeakers().toString().replace("[", "").replace("]", "")).collect(Collectors.toList()));
		
		eventManager.update(curEvent);
		
		return new ModelAndView("redirect:/events.jsp");
    }
	
	@RequestMapping(value = "/event-remove-user", method = RequestMethod.GET)
	public ModelAndView removeFromEvent(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "researcherId", required = true) long researcherId) {

		Event curEvent = (Event)request.getSession().getAttribute("event");
		Set<Researcher> setOfAttendees = curEvent.getAttendees();
		Set<Researcher> collectionOfAttendees = setOfAttendees
				.stream()
				.filter(r -> r.getResearcherId() != researcherId)
				.collect(Collectors.toSet());
		
		curEvent.setAttendees(collectionOfAttendees);
		
		eventManager.update(curEvent);
		
		request.getSession().setAttribute("event", curEvent);
		
        return new ModelAndView("eventDetail");
    }
}
