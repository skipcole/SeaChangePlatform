package com.seachangesimulations.platform.pluginobjects;

import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.domain.BaseSCPlatformObject;
import com.seachangesimulations.platform.pluginobjects.dao.PluginObjectAssociationDao;

/**
 * This object represents the many to many link between a plugin and its objects.
 * 
 * @author Skip
 *
 */
@Entity
@Component
@Scope("prototype")
@XmlRootElement
public class PluginObjectAssociation extends BaseSCPlatformObject implements Comparable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long pluginId;
	
	private Long objectId;
	
	private String objectName;
	
	private String objectType;
	
	private int objectIndex;

	public Long getPluginId() {
		return pluginId;
	}

	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	
	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public int getObjectIndex() {
		return objectIndex;
	}

	public void setObjectIndex(int objectIndex) {
		this.objectIndex = objectIndex;
	}

	@Override
	@SuppressWarnings("unchecked")
	public PluginObjectAssociation getById(Long id){
		PluginObjectAssociationDao dao = (PluginObjectAssociationDao) getApplicationContext().getBean("pluginObjectAssociationDao", PluginObjectAssociationDao.class);
		return dao.get(id);
	}
	
	public List <PluginObjectAssociation> getAllForPlugin(Long pluginId){
		PluginObjectAssociationDao dao = (PluginObjectAssociationDao) getApplicationContext().getBean("pluginObjectAssociationDao", PluginObjectAssociationDao.class);
		return dao.getAllForPlugin(pluginId);
	}
	
	public List <PluginObjectAssociation> getAllForPlugin(Long pluginId, String objectType){
		PluginObjectAssociationDao dao = (PluginObjectAssociationDao) getApplicationContext().getBean("pluginObjectAssociationDao", PluginObjectAssociationDao.class);
		return dao.getAllForPlugin(pluginId, objectType);
	}

	public void save() {
		PluginObjectAssociationDao dao = (PluginObjectAssociationDao) getApplicationContext().getBean("pluginObjectAssociationDao", PluginObjectAssociationDao.class);
		dao.save(this);
		
	}

	@Override
	public int compareTo(Object o) {
		PluginObjectAssociation poa = (PluginObjectAssociation) o;
		
		return poa.getObjectIndex() - this.getObjectIndex();
	}

	/** Gets the size of the current list and adds one. Not thread safe, but not
	 * anticipated that this will get hit a lot by different users at the same time.
	 * @return
	 */
	public int getNextObjectIndex(Long pluginId) {

		List currentPlugins = getAllForPlugin(pluginId);
		
		return currentPlugins.size() + 1;
	}

}
