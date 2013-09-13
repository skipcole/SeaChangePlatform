package com.seachangesimulations.platform.pluginobjects;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.domain.BaseSCPlatformObject;
import com.seachangesimulations.platform.pluginobjects.dao.PluginObjectAttributeDao;

/**
 * This class represents an attribute attached to an object attached to a plugin. 
 * when the author modified a plugin for their role play, they add objects to it.
 * For example, on a 'Read Document' page, the author will add the specific document
 * to it that they will want the players to read. The plugin designer will create
 * the plugin page that the players will see, and also give control to the 
 * author on how that page can be tailored. This is largely done through the use
 * of PluginObjectAttributes.
 */
@Entity
@Component
@Scope("prototype")
@XmlRootElement
public class PluginObjectAttribute extends BaseSCPlatformObject {

	private String poaName;
	
	private String poaDescription;
	
	private String poaValue;
	
	private String poaGranularity;
	
	public String getPoaName() {
		return poaName;
	}



	public void setPoaName(String poaName) {
		this.poaName = poaName;
	}



	public String getPoaDescription() {
		return poaDescription;
	}



	public void setPoaDescription(String poaDescription) {
		this.poaDescription = poaDescription;
	}



	public String getPoaValue() {
		return poaValue;
	}



	public void setPoaValue(String poaValue) {
		this.poaValue = poaValue;
	}



	public String getPoaGranularity() {
		return poaGranularity;
	}



	public void setPoaGranularity(String poaGranularity) {
		this.poaGranularity = poaGranularity;
	}



	@SuppressWarnings("unchecked")
	@Override
	public PluginObjectAttribute getById(Long id){
		PluginObjectAttributeDao dao = (PluginObjectAttributeDao) getApplicationContext().getBean("pluginObjectAttributeDao", PluginObjectAttributeDao.class);
		return dao.get(id);
	}
	
}
