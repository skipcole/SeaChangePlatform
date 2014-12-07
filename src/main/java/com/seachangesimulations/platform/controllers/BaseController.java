package com.seachangesimulations.platform.controllers;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;
import com.seachangesimulations.platform.service.PluginProvider;
import com.seachangesimulations.platform.service.SessionInfoBean;

/**
 * 
 *
 */
@Controller
public class BaseController implements ApplicationContextAware {

	@Autowired
	protected ApplicationContext thisApplicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		thisApplicationContext = applicationContext;
		
	}
	
	/** Returns the bean that holds session info for the user. */
	protected SessionInfoBean getSessionInfoBean(){
		SessionInfoBean sib = (SessionInfoBean) thisApplicationContext.getBean("sessionInfoBean");
		return sib;
	}
	
	/** Assigns global constants to values that can be extracted using Spring EL in the forms. */
	protected void assignRoleTypeConstants(Model model) {
		model.addAttribute("normalValue", PersonRoleplayAssignment.NORMAL_ROLE);
		model.addAttribute("controlValue", PersonRoleplayAssignment.CONTROL_ROLE);
		model.addAttribute("observerValue", PersonRoleplayAssignment.OBSERVER_ROLE);
		
	}
	
	/** Used for debugging purposes to determine where a user is in the game. */
	protected void printMyCoordinates() {
		System.out.println("Roleplay" + getSessionInfoBean().getRoleplayName());
		System.out.println("Actor" + getSessionInfoBean().getActorName());
		
		System.out.println("RoleplayId" + getSessionInfoBean().getRoleplayId());
		System.out.println("ActorId" + getSessionInfoBean().getActorId());
		System.out.println("PhaseId" + getSessionInfoBean().getPhaseId());
		System.out.println("PluginIndex" + getSessionInfoBean().getPluginIndex());
		System.out.println("PluginId" + getSessionInfoBean().getPluginId());
		System.out.flush();
		
		
	}
	
}
