
package com.seachangesimulations.platform.controllers;


import java.security.Principal; 
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.service.SessionInfoBean;

@Controller
public class HomeController extends BaseController {

	private static final Logger LOGGER = Logger
            .getLogger(HomeController.class.getName());
	
	/**
	 * Shows the Home page.
	 * 
	 * @param model Model to hold objects for the view.
	 * @return path to JSP. 
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String showHomePage(Map<String, Object> model) {
		String msg = "/ RequestMapped to showHomePage - redirects to /welecome.";
		LOGGER.debug(msg);
		// JFrame frame = new JFrame(msg);
		// JOptionPane.showMessageDialog(frame, msg);
		// frame.dispose();
		LOGGER.debug("In the / Request Mapping MJS showHomePage -> welcome.");
		return "redirect:/welcome";
	}
	
	/**
	 * 
	 * @param model Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String showWelcomePage(Map<String, Object> model) {
		LOGGER.debug("Starting showWelcomePage . . . ");
		return "welcome.jsp";
	}
	
	/**
	 * Shows the login page.
	 * 
	 * @return path to JSP.
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String showLoginForm() {
		System.out.println("Starting showLogin Form.");
		LOGGER.debug("In the /login Request Mapping showLoginForm MJS in HomeController.java");
		return "login.jsp";
	}
	
	/** 
	 * Loads info from the user (such as the last role play they edited) to the session info.
	 * 
	 * @param model Model to hold objects for the view.
	 * @param principal
	 * @param session
	 * @return path to JSP.
	 */
	@RequestMapping(value = { "/loadLoginInfo" }, method = RequestMethod.GET)
	public String loadLoginInfo(Map<String, Object> model, Principal principal, HttpSession session) {
		String msg = "In the /loadLoginInfo GET Request Mapping MJS in HomeController.java";
		LOGGER.debug(msg); 
		JFrame frame = new JFrame(msg);
		// JOptionPane.showMessageDialog(frame, msg + " Calling principle.getName() with principal=" + principal.getName());
		try {
			String prin = principal.getName();
			getSessionInfoBean().loadLoginInfo(prin);
			Person person = new Person().getByUsername(principal.getName());
			getSessionInfoBean().setPersonId(person.getId());
		
			session.setAttribute("uname", principal.getName());
		} catch (Exception ex) {
			msg = "Exception in loadLoginInfo at /loadLoginInfo(GET) ";
			msg = msg + " Type: " + ex.getClass().getSimpleName();
			msg = msg + " Cause: " +ex.getMessage();
			JOptionPane.showMessageDialog(frame, msg);  // allow popup msg for major errors.
		}
		frame.dispose();
		return "redirect:/loginLanding";  // go to the next method which actually goes to the JSP page.
	}
	
	/**
	 * 
	 * @param model Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { "/loginLanding" }, method = RequestMethod.GET)
	public String showLoginLanding(Map<String, Object> model) {
		LOGGER.debug("In the /loginLanding Request Mapping MJS in HomeController.java");
		return "loginLanding.jsp";
	}

	/**
	 * 
	 * @return path to JSP.
	 */
	@RequestMapping(value = { "/accessDenied" })
	public String accessDenied() {
		LOGGER.debug("In the /accessDenied Request Mapping MJS in HomeController.java");
		return "accessDenied.jsp";
	}
	
	
	
	
	/**
	 * Returns the logout page to confirm to the user that they have logged out.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String showLogoutGet() {
		LOGGER.debug("In the /logout Request Mapping MJS in showLogoutGet in HC");
		return "logout.jsp";
	}
	
	 // Returns the loginFailed get page to test pages. MJS 2.18
	@RequestMapping(value = { "/loginFailed" }, method = RequestMethod.GET)
	public String showLoginFailedGet() {
		LOGGER.debug("In the /loginFailed Request Mapping MJS in showLogoutGet in HC");
		return "loginFailed.jsp";
	}
	
	 // Returns the loginFailed post page to test pages.  MJS 2.18
	@RequestMapping(value = { "/loginFailed" }, method = RequestMethod.POST)
	public String showLoginFailedPost() {
		LOGGER.debug("In the /loginFailed Request Mapping MJS in showLogouPOSTt in HC");
		return "loginFailed.jsp";
	}
	
	/**
	 * Returns the create(User) page..
	 * @return path to JSP. 
	 */
	@RequestMapping(value = { "/create" })
	public String createUserPostOrGet() {
		LOGGER.debug("In the /create Request Mapping MJS in createUserPostOrGet in HC");
		return "create.jsp";
	}

	/**
	 * Returns the loginPassed get page to test pages.
	 * @return path to JSP.  Seems like able to use the post routine even for get request!
	 * Seems like its the RequestMapping value that really counds, not the method name.
	 */
	@RequestMapping(value = { "/loginPassed" })
	public String showLoginPassedPostOrGet() {
		LOGGER.debug("In the /loginPassed Request Mapping MJS in showLoginPassedPOST in HC");
		return "loginPassed.jsp";
	}
	
	/**
	 * 
	 * @return path to JSP.
	 */
	@RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
	public String showProfilePage() {
		return "userprofile/index.jsp";
	}

}
