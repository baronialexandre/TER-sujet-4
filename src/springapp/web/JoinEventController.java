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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.business.IEventManager;
import springapp.business.IResearcherManager;
import springapp.model.Event;
import springapp.model.Researcher;

@Controller()
public class JoinEventController {

	@Autowired
	IEventManager eventManager;

	@Autowired
	IResearcherManager researcherManager;

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "joinevent", method = RequestMethod.GET)
	public ModelAndView joinEvent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "eventId", required = true) long eventId) throws ServletException, IOException {
		try {
			if (request.getSession().getAttribute("userId") == null)
				return new ModelAndView("redirect:/login.jsp");
		} catch (Exception e) {
			return new ModelAndView("redirect:/login.jsp");
		}
		logger.info("Find one event");

		if (request.getSession().getAttribute("researcher") == null) {
			Researcher currResearcher = researcherManager
					.getResearcher((Long) request.getSession().getAttribute("userId"));
			logger.info(currResearcher.getRole());
			request.getSession().setAttribute("researcher", currResearcher);
		}

		Event curEvent = eventManager.find(eventId);

		if (curEvent == null) {
			return new ModelAndView("redirect:/actions/events");
		}

		curEvent.addAttendee((Researcher) request.getSession().getAttribute("researcher"));
		eventManager.update(curEvent);

		logger.info(curEvent.toString());
		request.getSession().setAttribute("event", curEvent);
		return new ModelAndView("eventDetail");
	}

}