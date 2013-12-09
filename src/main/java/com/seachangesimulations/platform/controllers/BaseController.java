package com.seachangesimulations.platform.controllers;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

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
	
	@Autowired
	protected PluginProvider pluginProvider;
	
}
