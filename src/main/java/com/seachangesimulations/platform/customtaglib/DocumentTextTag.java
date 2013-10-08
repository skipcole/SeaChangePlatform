package com.seachangesimulations.platform.customtaglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;

public class DocumentTextTag extends BaseTag {

    @Override
    public int doStartTag() throws JspException {
         
        try {
            //Get the writer object for output.
            JspWriter out = pageContext.getOut();
 
            // Baby steps ...
            PluginObjectDocument pod = new PluginObjectDocument().getById(new Long(2));
            
            //Perform substr operation on string.
            out.println(pod.getDocumentText());
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
