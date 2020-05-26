package springapp.web;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springapp.business.ILabManager;
import springapp.business.IResearcherManager;
import springapp.model.Lab;
import springapp.model.Researcher;
import springapp.model.utils.Role;

@Controller()
public class RegisterController {

	@Autowired
	IResearcherManager researcherManager;
	@Autowired
	ILabManager labManager;
	@Autowired
	ResearcherValidator validator;

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "register")
	public String register(Model model) {
		logger.info("Register");
		model.addAttribute("register", new Researcher());

		Map<Long, String> labs = new LinkedHashMap<Long, String>();
		labs.put(new Long(0), "---");
		labs.putAll(labManager.getLabNameMap());
		model.addAttribute("labs", labs);

		return "register";
	}

	@ModelAttribute
	public Researcher newResearcher() {
		return new Researcher();
	}

	@RequestMapping(value = "register-account", method = RequestMethod.POST)
	public ModelAndView registerAccount(@Valid @ModelAttribute("researcher") Researcher researcher,
			BindingResult result, HttpServletRequest request) {
		logger.info("register-account");
		Researcher resear = researcher;
		validator.validate(researcher, result);
		if (result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("register");
			modelAndView.addObject(resear);
			Map<Long, String> labs = new LinkedHashMap<Long, String>();
			labs.put(new Long(0), "---");
			labs.putAll(labManager.getLabNameMap());
			modelAndView.addObject("labs", labs);
			return modelAndView;
		}
		researcher.setRole(Role.USER);
		if (Long.parseLong((String) result.getFieldValue("lab.labId")) != 0) {
			Lab lab = labManager.find(Long.parseLong((String) result.getFieldValue("lab.labId")));
			// logger.info("ID LAB changement: " + lab.getLabId());
			researcher.setLab(lab);
		} else {
			researcher.setLab(null);
		}
		researcherManager.add(researcher);
		request.getSession().setAttribute("registerMessage", "Account registered! Please log in");
		request.getSession().setAttribute("registeredEmail", researcher.getEmail());
		return new ModelAndView("redirect:/login.jsp");
	}
}
