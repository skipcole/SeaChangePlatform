package com.seachangesimulations.platform.mvc.formbeans.developer;

import org.springframework.beans.BeanUtils;

import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.domain.PluginFile;

public class DevUploadPluginFilesFormBean {

	public DevUploadPluginFilesFormBean() {

	}

	public DevUploadPluginFilesFormBean(PluginFile pluginFile) {
		
		BeanUtils.copyProperties(pluginFile, this);
	}
	
	private String fileDescription;

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
	
	

}
