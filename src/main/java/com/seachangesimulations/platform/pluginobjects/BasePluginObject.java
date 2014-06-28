package com.seachangesimulations.platform.pluginobjects;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.domain.BaseSCPlatformObject;

@Component
@MappedSuperclass
@XmlAccessorType (XmlAccessType.NONE)  // This means that if you want a field mapped, it has to be annotated.
public abstract class BasePluginObject extends BaseSCPlatformObject implements Comparable{
	
	@Override
	public int compareTo(Object o) {
		BasePluginObject bpo = (BasePluginObject) o;
		return   this.getIndexOfObject() - bpo.getIndexOfObject();

	}
	
	// TODO Consider ramifications of allowing author set the granularity.
	private boolean authorSettableGranularity = false;
	
	
	/** The base, uncustomized, plugin id that this object is associated with. */
	protected Long basePluginId;
	
	/** Id of the role play that this object is associated with. */
	protected Long roleplayId;
	
	/** Id of the role play in motion that this object is associated with . */
	protected Long roleplayInMotionId;
	
	/** Id of the set of roleplays (in motion) that this object is associated with. */
	protected Long roleplaySetId;
	
	/** The index of this object (1,2, 3, etc.) on the plugin. */
	protected int indexOfObject = 0;

	public boolean isAuthorSettableGranularity() {
		return authorSettableGranularity;
	}

	public void setAuthorSettableGranularity(boolean authorSettableGranularity) {
		this.authorSettableGranularity = authorSettableGranularity;
	}

	public Long getBasePluginId() {
		return basePluginId;
	}

	public void setBasePluginId(Long basePluginId) {
		this.basePluginId = basePluginId;
	}

	public Long getRoleplayId() {
		return roleplayId;
	}

	public void setRoleplayId(Long roleplayId) {
		this.roleplayId = roleplayId;
	}

	public Long getRoleplayInMotionId() {
		return roleplayInMotionId;
	}

	public void setRoleplayInMotionId(Long roleplayInMotionId) {
		this.roleplayInMotionId = roleplayInMotionId;
	}

	public Long getRoleplaySetId() {
		return roleplaySetId;
	}

	public void setRoleplaySetId(Long roleplaySetId) {
		this.roleplaySetId = roleplaySetId;
	}

	public int getIndexOfObject() {
		return indexOfObject;
	}

	public void setIndexOfObject(int indexOfObject) {
		this.indexOfObject = indexOfObject;
	}
	
	
	
	
}
