package com.seachangesimulations.platform.mvc.formbeans.developer;

import org.springframework.beans.BeanUtils;

import com.seachangesimulations.platform.domain.Plugin;

public class DevAddObjectsToPluginFormBean {
	
	String objectType;
	
	String objectName;
	
	String objectDescription;
	
	String objectGranularity;
	
	Long objectId;
	
	Long pluginId;

	public DevAddObjectsToPluginFormBean() {

	}

	public DevAddObjectsToPluginFormBean(Plugin plugin) {

		// Plugin is source, this is target.
		BeanUtils.copyProperties(plugin, this);

	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectDescription() {
		return objectDescription;
	}

	public void setObjectDescription(String objectDescription) {
		this.objectDescription = objectDescription;
	}

	public String getObjectGranularity() {
		return objectGranularity;
	}

	public void setObjectGranularity(String objectGranularity) {
		this.objectGranularity = objectGranularity;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getPluginId() {
		return pluginId;
	}

	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
	}
	
	
	
}
