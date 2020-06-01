package springapp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
import springapp.model.Event;
import springapp.model.Researcher;

@Controller()
public class ProfileController {

	@Autowired
	IResearcherManager researcherManager;

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public ModelAndView getProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long researcherId;
		try {
			researcherId = Long.parseLong(request.getParameter("researcherId"));
		} catch (Exception e) {
			researcherId = -1;
		}
		if (researcherId == -1) {
			try {
				researcherId = (long) request.getSession().getAttribute("userId");
			} catch (Exception e) {
				return new ModelAndView("redirect:/login.jsp");
			}
		}

		Researcher researcher = researcherManager.getResearcher(researcherId);
		List<Event> attending = new ArrayList<Event>(researcher.getEventsAttending());
		List<Event> organized = new ArrayList<Event>(researcher.getEventsOrganized());
		attending.sort(new Comparator<Event>() { // du plus vieux au plus recent
			@Override
			public int compare(Event o1, Event o2) {
				return o1.getBeginDate().compareTo(o2.getBeginDate());
			}
		});
		organized.sort(new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				return o1.getBeginDate().compareTo(o2.getBeginDate());
			}
		});
		List<Event> upcoming = new ArrayList<Event>();
		List<Event> attended = new ArrayList<Event>();
		Date now = new Date();
		for (Event e : attending) {
			if (e.getBeginDate().compareTo(now) > 0)
				upcoming.add(e);
			else
				attended.add(e);
		}
		attended.sort(new Comparator<Event>() { // du plus recent au plus vieux
			@Override
			public int compare(Event o1, Event o2) {
				return o2.getBeginDate().compareTo(o1.getBeginDate());
			}
		});

		request.getSession().setAttribute("researcher", researcher);
		request.getSession().setAttribute("eventsUpcoming", upcoming);
		request.getSession().setAttribute("eventsAttended", attended);
		request.getSession().setAttribute("eventsOrganized", organized);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("profile");
		return modelAndView;
	}
}