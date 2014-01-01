package com.seachangesimulations.platform.customtaglib;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;

import com.seachangesimulations.platform.service.SessionInfoBean;

@Controller
@Configurable
public class BaseTag  extends TagSupport{
	
	public SessionInfoBean getSessionInfoBean(){
		
		HttpSession session = pageContext.getSession();
		String uname = (String) session.getAttribute("uname");
		SessionInfoBean sib = SessionInfoBean.getMySessionInfo(uname);
		
		//WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext( pageContext.getServletContext());
		//SessionInfoBean sib = (SessionInfoBean) wc.getBean("sessionInfoBean");
		
		return sib;
	}
	
}
