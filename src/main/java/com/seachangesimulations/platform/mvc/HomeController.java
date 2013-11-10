
package com.seachangesimulations.platform.mvc;


import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.service.SessionInfoBean;

@Controller
public class HomeController extends BaseController {

	private static final Logger LOGGER = Logger
            .getLogger(HomeController.class.getName());
	
	/**
	 * Shows the Home page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String showHomePage(Map<String, Object> model) {
		LOGGER.debug("In the / Request Mapping");
		return "redirect:/welcome";
	}
	
	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String showWelcomePage(Map<String, Object> model) {
		return "welcome.jsp";
	}
	
	/**
	 * Shows the login page.
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String showLoginForm() {
		LOGGER.debug("In the /login Request Mapping");
		return "login.jsp";
	}
	
	/** Loads info from the user (such as the last role play they edited) to the session info. */
	@RequestMapping(value = { "/loadLoginInfo" }, method = RequestMethod.GET)
	public String loadLoginInfo(Map<String, Object> model, Principal principal, HttpSession session) {
		LOGGER.debug("In the /loadLoginInfo Request Mapping");
		getSessionInfoBean().loadLoginInfo(principal.getName());
		Person person = new Person().getByUsername(principal.getName());
		getSessionInfoBean().setPersonId(person.getId());
		
		session.setAttribute("uname", principal.getName());
		
		return "redirect:/loginLanding";
	}
	
	@RequestMapping(value = { "/loginLanding" }, method = RequestMethod.GET)
	public String showLoginLanding(Map<String, Object> model) {
		LOGGER.debug("In the /loginLanding Request Mapping");
		return "loginLanding.jsp";
	}

	@RequestMapping(value = { "/accessDenied" })
	public String accessDenied() {
		LOGGER.debug("In the /accessDenied Request Mapping");
		return "accessDenied.jsp";
	}

	@RequestMapping(value = { "/admin/index" }, method = RequestMethod.GET)
	public String showAdminPage(Map<String, Object> model) {
		getSessionInfoBean().setPlatformZone(SessionInfoBean.ADMIN_ZONE);
		return "admin/index.jsp";
	}
	
	@RequestMapping(value = { "/admin/index/setZone" }, method = RequestMethod.GET)
	public String setAdminZone(Map<String, Object> model) {
		getSessionInfoBean().setPlatformZone(SessionInfoBean.ADMIN_ZONE);
		return "redirect:/admin/index";
	}
	
	
	
	
	/**
	 * Returns the logout page to confirm to the user that they have logged oout.
	 * @return
	 */
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String showLogoutGet() {
		LOGGER.debug("In the /logout Request Mapping");
		return "logout.jsp";
	}
	
	@RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
	public String showProfilePage() {
		return "userprofile/index.jsp";
	}

}
