package com.seachangesimulations.platform.customtaglib;

import java.io.IOException;
 







import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.seachangesimulations.platform.service.SessionInfoBean;
 
@Controller
@Configurable
public class ActorNameTag extends BaseTag {
     
    @Override
    public int doStartTag() throws JspException {
         
        try {
            //Get the writer object for output.
            JspWriter out = pageContext.getOut();
 
            //Perform substr operation on string.
            out.println(getSessionInfoBean().getActorName());
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
