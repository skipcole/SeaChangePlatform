package com.seachangesimulations.platform.mvc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import com.seachangesimulations.platform.service.SessionInfoBean;

/**
 * 
 * @author Skip
 *
 */
@Controller
public class BaseController implements ApplicationContextAware {

	protected ApplicationContext thisApplicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		thisApplicationContext = applicationContext;
		
	}
	
	protected SessionInfoBean getSessionInfoBean(){
		SessionInfoBean sib = (SessionInfoBean) thisApplicationContext.getBean("sessionInfoBean");
		return sib;
	}
	
}
