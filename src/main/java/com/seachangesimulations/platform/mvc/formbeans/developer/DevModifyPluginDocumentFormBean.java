package com.seachangesimulations.platform.mvc.formbeans.developer;

import org.springframework.beans.BeanUtils;

import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;

public class DevModifyPluginDocumentFormBean {

	Long documentId;
	
	Long pluginId;
	
	String documentName;
	
	String documentText;

	public DevModifyPluginDocumentFormBean() {
	}
	
	public DevModifyPluginDocumentFormBean(PluginObjectDocument pod) {
		BeanUtils.copyProperties(pod,  this);
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getPluginId() {
		return pluginId;
	}

	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
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
