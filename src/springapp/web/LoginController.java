package springapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springapp.business.IUserManager;
import springapp.model.utils.User;

@Controller()
@ControllerAdvice
public class LoginController{

	@Autowired
	IUserManager userManager;
	
    protected final Log logger = LogFactory.getLog(getClass());
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView logIn(HttpServletRequest request,
            HttpServletResponse response) {
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	logger.info("username :" + username + " | password :"+ password);
    	User user = new User(username,password);
    	userManager.authentificate(user);
    	if(!user.isLoggedIn()) {
    		request.getSession().setAttribute("loginFailed", true);
    		return new ModelAndView("redirect:/");
    	}
    	request.getSession().setAttribute("userId", user.getId());
        return new ModelAndView("redirect:/actions/list");
    }
    
    @RequestMapping(value = "logout")
    public ModelAndView logOut(HttpServletRequest request,
            HttpServletResponse response) {
    	request.getSession().setAttribute("userId", null);
        return new ModelAndView("redirect:/logout.jsp");
    }

}