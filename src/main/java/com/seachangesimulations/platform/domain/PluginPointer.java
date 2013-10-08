package com.seachangesimulations.platform.domain;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.PluginPointerDao;

/**
 * This class represents the pointer to a plugin for a particular actor in a particular roleplay
 * at a particular phase, and contains all of the customizations associated with that.
 * 
 * @author Skip
 *
 */
@Entity
@Component
@Scope("prototype")
public class PluginPointer extends BaseSCPlatformObject implements Comparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String SYSTEM_CONTROL = "CONTROL";


	public PluginPointer(){
		
	}
	
	public PluginPointer(Long rpId, Long aId, Long phId, String pHeading, Long rawPluginId) {

		this.rolePlayId = rpId;
		this.actorId = aId;
		this.phaseId = phId;
		this.pluginHeading = pHeading;
		this.pluginId = rawPluginId;
		
		// Need to get the list and add this entry at the end.
		List theCurrentSet = this.getCurrentSet(rpId, aId, phId);
		
		this.pluginIndex = new Integer(theCurrentSet.size() + 1);
		
		this.save();
	}
	
	public PluginPointer(Long rpId, Long phId, String pHeading, Long rawPluginId, boolean addedAsUniversal) {

		this.rolePlayId = rpId;
		this.actorId = new Long(0);
		this.phaseId = phId;
		this.pluginHeading = pHeading;
		this.pluginId = rawPluginId;
		this.addedAsUniversal = true;
		
		// Need to get the list and add this entry at the end.
		List theCurrentSet = this.getCurrentSet(rpId, this.actorId, phId);
		
		this.pluginIndex = new Integer(theCurrentSet.size() + 1);
		
		this.save();
	}

	public List getCurrentSet(Long rpId, Long aId, Long phId) {
		PluginPointerDao dao = (PluginPointerDao) getApplicationContext().getBean("pluginPointerDao", PluginPointerDao.class);
		return dao.getCurrentSet(rpId, aId, phId);
	}
	
	public PluginPointer getControlPluginByHandle(String handle){
		PluginPointerDao dao = (PluginPointerDao) getApplicationContext().getBean("pluginPointerDao", PluginPointerDao.class);
		return dao.getControlPluginByHandle(handle);
	}

	/** Id of the role play that this object is associated with. */
	private Long rolePlayId;
	
	/** Id of the actor this pointer is for. */
	private Long actorId;
	
	/** Id of the phase this pointer is for. */
	private Long phaseId;
	
	/** Id of the plugin this pointer is for. */
	private Long pluginId;
	
	/** Index of this plugin (shows up first in list or second, etc.) */
	private Integer pluginIndex;
	
	private String pluginHeading;
	
	/** True if this was added as a universal section. */
	private boolean addedAsUniversal = false;
	
	private boolean isSystemPluginPointer = false;
	
	private String systemPluginHandle;
	
	public void save(){
		PluginPointerDao dao = (PluginPointerDao) getApplicationContext().getBean("pluginPointerDao", PluginPointerDao.class);
		dao.save(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PluginPointer getById(Long id){
		PluginPointerDao dao = (PluginPointerDao) getApplicationContext().getBean("pluginPointerDao", PluginPointerDao.class);
		return dao.get(id);
	}

	public Long getRolePlayId() {
		return rolePlayId;
	}

	public void setRolePlayId(Long rolePlayId) {
		this.rolePlayId = rolePlayId;
	}

	public Long getActorId() {
		return actorId;
	}

	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}

	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public Long getPluginId() {
		return pluginId;
	}

	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
	}

	public Integer getPluginIndex() {
		return pluginIndex;
	}

	public void setPluginIndex(Integer pluginIndex) {
		this.pluginIndex = pluginIndex;
	}

	public boolean isAddedAsUniversal() {
		return addedAsUniversal;
	}

	public void setAddedAsUniversal(boolean addedAsUniversal) {
		this.addedAsUniversal = addedAsUniversal;
	}

	@Override
	public int compareTo(Object object) {
		PluginPointer pp = (PluginPointer) object;
		
		return this.pluginIndex.compareTo(pp.pluginIndex);

	}

	public String getPluginHeading() {
		return pluginHeading;
	}

	public void setPluginHeading(String pluginHeading) {
		this.pluginHeading = pluginHeading;
	}

	public boolean isSystemPluginPointer() {
		return isSystemPluginPointer;
	}

	public void setSystemPluginPointer(boolean isSystemPluginPointer) {
		this.isSystemPluginPointer = isSystemPluginPointer;
	}

	public String getSystemPluginHandle() {
		return systemPluginHandle;
	}

	public void setSystemPluginHandle(String systemPluginHandle) {
		this.systemPluginHandle = systemPluginHandle;
	}

	public PluginPointer getByPlayerValues(Long roleplayId2, Long actorId2, Long phaseId2, Long pluginIndex2) {

		PluginPointerDao dao = (PluginPointerDao) getApplicationContext().getBean("pluginPointerDao", PluginPointerDao.class);
		return dao.getByPlayerValues(roleplayId2, actorId2, phaseId2, pluginIndex2);
	}	
	
	
}
