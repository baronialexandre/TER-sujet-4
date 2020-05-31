package springapp.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.business.ILabManager;
import springapp.business.IResearcherManager;
import springapp.model.Lab;
import springapp.model.Researcher;
import springapp.model.utils.Role;

@Controller()
public class EditProfileController {
	@Autowired
	IResearcherManager researcherManager;
	@Autowired
	ResearcherValidator validator;

	@Autowired
	ILabManager labManager;

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "edit-profile", method = RequestMethod.GET)
	public ModelAndView loadEditProfile(@Valid @ModelAttribute("researcher") Researcher researcher,
			HttpServletRequest request) {
		try {
    		if((Long)request.getSession().getAttribute("userId") != researcher.getResearcherId() && (request.getSession().getAttribute("userRole") != Role.ADMIN))
        		return new ModelAndView("redirect:/actions/events");
    	} catch (Exception ex) {
    		return new ModelAndView("redirect:/actions/events");
    	}
		logger.info("edit profile " + researcher);
		request.getSession().setAttribute("disableMenu", false);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editProfile");
		modelAndView.addObject(researcher);
		Map<Long, String> labs = new LinkedHashMap<Long, String>();
		labs.put(new Long(0), "---");
		labs.putAll(labManager.getLabNameMap());
		request.getSession().setAttribute("labs", labs);

		System.out.print("loadEditProfile :: loaded");
		return modelAndView;
	}

	@ModelAttribute
	public Researcher newResearcher(@RequestParam(value = "researcherId", required = true) Integer researcherNumber) {
		Researcher researcher = new Researcher("", "", "", "", new Date(), "", Role.USER);
		researcher = researcherManager.getResearcher(researcherNumber);
		logger.info("find researcher " + researcherNumber);
		return researcher;
	}

	// no validator here .... we have to create a custom validation maybe
	@RequestMapping(value = "change-password-profile", method = RequestMethod.POST)
	public ModelAndView changePasswordProfile(@ModelAttribute("researcher") Researcher researcher,
			HttpServletRequest request, HttpServletResponse response) {
		String message = "";

		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");

		logger.info("change-password-profile");
		Researcher resear = researcher;

		if (pass1.contentEquals(pass2)) {
			if (!BCrypt.checkpw(pass1, resear.getPassword())) {
				resear.setPassword(BCrypt.hashpw(pass1, BCrypt.gensalt()));
				researcherManager.update(researcher);
				message = "Password changed !";
			} else {
				message = "No modification.";
			}
		} else {
			message = "The two passwords are not the same.";
		}
		System.out.println(researcher.toString());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editProfile");
		request.getSession().setAttribute("editProfileNotification", message);
		return modelAndView;
	}

	// no validator here .... we have to create a custom validation maybe
	@RequestMapping(value = "change-mail-profile", method = RequestMethod.POST)
	public ModelAndView changeMailProfile(@ModelAttribute("researcher") Researcher researcher,
			HttpServletRequest request, HttpServletResponse response) {
		String message = "";

		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");

		logger.info("change-mail-profile");
		Researcher resear = researcher;

		if (email1.contentEquals(email2)) {
			if (!resear.getEmail().contentEquals(email1)) {
				if (!researcherManager.hasResearcher(email1)) {
					resear.setEmail(email1);
					researcherManager.update(researcher);
					message = "Email changed !";
				} else {
					message = "Email already used by another researcher.";
				}
			} else {
				message = "No modification.";
			}
		} else {
			message = "The two emails are not the same.";
		}
		System.out.println(researcher.toString());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editProfile");
		request.getSession().setAttribute("editProfileNotification", message);
		return modelAndView;
	}

	@RequestMapping(value = "change-other-profile", method = RequestMethod.POST)
	public ModelAndView changeOtherProfile(@Valid @ModelAttribute("researcher") Researcher researcher,
			BindingResult result, HttpServletRequest request) {
		logger.info("change-other-profile");
		researcherManager.update(researcher);
		System.out.println(researcher.toString());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editProfile");
		request.getSession().setAttribute("editProfileNotification", "Edited !");
		return modelAndView;
	}

	@RequestMapping(value = "change-admin-profile", method = RequestMethod.POST)
	public ModelAndView changeAdminProfile(@Valid @ModelAttribute("researcher") Researcher researcher,
			BindingResult result, HttpServletRequest request) {
		logger.info("change-admin-profile");
		if (Long.parseLong((String) result.getFieldValue("lab.labId")) != 0) {
			Lab lab = labManager.find(Long.parseLong((String) result.getFieldValue("lab.labId")));
			//logger.info("ID LAB changement: " + lab.getLabId());
			researcher.setLab(lab);
		} else {
			//logger.info("PAS DE LAB");
			researcher.setLab(null);
		}
		researcherManager.update(researcher);
		logger.info(researcher.toString());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("editProfile");
		request.getSession().setAttribute("editProfileNotification", "Edited !");
		return modelAndView;
	}
}