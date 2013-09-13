package com.seachangesimulations.platform.mvc.formbeans.author;

import org.springframework.beans.BeanUtils;

import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;

public class AuthCustomizePluginDocumentFormBean {

	String documentName;

	String documentText;

	public AuthCustomizePluginDocumentFormBean() {

	}

	public AuthCustomizePluginDocumentFormBean(PluginObjectDocument pod) {
		BeanUtils.copyProperties(pod, this);
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}

}
