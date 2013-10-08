package com.seachangesimulations.platform.customtaglib;

import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.seachangesimulations.platform.service.SessionInfoBean;

@Controller
@Configurable
public class BaseTag  extends TagSupport{
	
	public SessionInfoBean getSessionInfoBean(){
		WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext( pageContext.getServletContext());
		SessionInfoBean sib = (SessionInfoBean) wc.getBean("sessionInfoBean");
		
		return sib;
	}
	
}
