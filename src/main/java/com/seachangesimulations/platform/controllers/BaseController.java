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

	@Autowired
	protected PluginProvider pluginProvider;
	
	protected void printMyCoordinates() {
		System.out.println("Roleplay" + getSessionInfoBean().getRoleplayName());
		System.out.println("Actor" + getSessionInfoBean().getRoleplayName());
		
	}
	
}
