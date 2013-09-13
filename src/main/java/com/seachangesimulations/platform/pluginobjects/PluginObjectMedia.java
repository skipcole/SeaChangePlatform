package com.seachangesimulations.platform.pluginobjects;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.pluginobjects.dao.PluginObjectMediaDao;

/**
 * 
 * @author Skip
 *
 */
@Entity
@Component
@Scope("prototype")
@XmlRootElement
public class PluginObjectMedia extends BasePluginObject {

	
	@SuppressWarnings("unchecked")
	@Override
	public PluginObjectMedia getById(Long id){
		PluginObjectMediaDao dao = (PluginObjectMediaDao) getApplicationContext().getBean("pluginObjectMediaDao", PluginObjectMediaDao.class);
		return dao.get(id);
	}
	
	
}
