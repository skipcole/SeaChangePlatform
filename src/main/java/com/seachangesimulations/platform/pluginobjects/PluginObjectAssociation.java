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
 * It is a very important class, and I'm thinking about splitting it into three classes.
 * An object can be associated with a plugin at the 'developer' level. If this is the case,
 * the role play and roleplay in motion (rpim) ids are null. An object can be associated with a plugin at the
 * author (or roleplay) level, in which case the rpim is null. And an object can be 
 * associated with a roleplay in motion.
 * 
 * @author Skip
 *
 */
@Entity
@Component
@Scope("prototype")
@XmlRootElement
public class PluginObjectAssociation extends BaseSCPlatformObject implements Comparable{

	public static final String BASE_PLUGIN_ASSOCIATION = "BASE_PLUGIN_ASSOCIATION";
	
	public static final String RP_PLUGIN_ASSOCIATION = "RP_PLUGIN_ASSOCIATION";
	
	public static final String RPIM_PLUGIN_ASSOCIATION = "RPIM_PLUGIN_ASSOCIATION";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long pluginId;
	
	/** Indicates if this object is associated with a plugin at the level of a plugin, or roleplay, or rpim. */
	private String associationType;
	
	private Long objectId;
	
	private String objectName;
	
	private String objectType;
	
	private int objectIndex;
	
	/** Id of the Roleplay that this object is associated with. */
	private Long rpId;
	
	/** Id of the Roleplay in Motion that this object is associated with. */
	private Long rpimId;

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
	
	public List <PluginObjectAssociation> getAllForRoleplay(Long rId){
		PluginObjectAssociationDao dao = (PluginObjectAssociationDao) getApplicationContext().getBean("pluginObjectAssociationDao", PluginObjectAssociationDao.class);
		return dao.getAllForRoleplay(rId);
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

	
	public Long getRpId() {
		return rpId;
	}

	public void setRpId(Long rpId) {
		this.rpId = rpId;
	}

	public Long getRpimId() {
		return rpimId;
	}

	public void setRpimId(Long rpimId) {
		this.rpimId = rpimId;
	}
	
	public String getAssociationType() {
		return associationType;
	}

	public void setAssociationType(String associationType) {
		this.associationType = associationType;
	}

	public List<PluginObjectAssociation> getAllForPlugin(Long id, Long rpId2, Long rpimId2) {
		PluginObjectAssociationDao dao = (PluginObjectAssociationDao) getApplicationContext().getBean("pluginObjectAssociationDao", PluginObjectAssociationDao.class);
		return dao.getAllForPlugin(id, rpId2, rpimId2);
	}

	public void delete() {
		PluginObjectAssociationDao dao = (PluginObjectAssociationDao) getApplicationContext().getBean("pluginObjectAssociationDao", PluginObjectAssociationDao.class);
		dao.delete(this);
	}
	
	

}
