package com.seachangesimulations.platform.customtaglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;

public class DocumentTextTag extends BaseTag {

	private int docIndex;
	
    @Override
    public int doStartTag() throws JspException {
         
        try {
            //Get the writer object for output.
            JspWriter out = pageContext.getOut();
            
            // Using plugin id and doc index to locate the document
            System.out.println("plugin id is " + getSessionInfoBean().getPluginId());
            System.out.println("rpim is " + getSessionInfoBean().getRolePlayInMotionId());
            System.out.println("docindex is " + docIndex);
            
            PluginObjectDocument pod = 
            		new PluginObjectDocument().getByRPimIdPluginIdAndIndex(getSessionInfoBean().getRolePlayInMotionId(), getSessionInfoBean().getPluginId(), docIndex);
            
            //Print out document
            out.println(pod.getDocumentText());
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

	public int getDocIndex() {
		return docIndex;
	}

	public void setDocIndex(int docIndex) {
		this.docIndex = docIndex;
	}
    
    
}
