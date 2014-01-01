package com.seachangesimulations.platform.domain;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.PluginFileDao;

@Entity
@Component
@Scope("prototype")
public class PluginFile extends BaseSCPlatformObject {


	private Long pluginId;
	
	private String fileName;
	
	private String fileDescription;

	public Long getPluginId() {
		return pluginId;
	}

	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public void save() {
		PluginFileDao dao = (PluginFileDao) getApplicationContext().getBean("pluginFileDao", PluginFileDao.class);
		dao.save(this);
		
	}
	
	public List getAllForPlugin(Long pluginId){
		PluginFileDao dao = (PluginFileDao) getApplicationContext().getBean("pluginFileDao", PluginFileDao.class);
		return dao.getAllForPlugin(pluginId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PluginFile getById(Long id){
		PluginFileDao dao = (PluginFileDao) getApplicationContext().getBean("pluginFileDao", PluginFileDao.class);
		return dao.get(id);
	}
	
}
