
package com.seachangesimulations.platform.domain;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.RolePlayInMotionDao;

@Entity
@Component
@Scope("prototype")
public class RoleplayInMotion extends BaseSCPlatformObject {

	public RoleplayInMotion() {

	}

	/** Id of the role play that this object is associated with. */
	private Long rolePlayId;
	
	private String roleplayInMotionName;
	
	private Long phaseId;
	
	private boolean active = false;

	@SuppressWarnings("unchecked")
	@Override
	public RoleplayInMotion getById(Long id){
		RolePlayInMotionDao dao = (RolePlayInMotionDao) getApplicationContext().getBean("rolePlayInMotionDao", RolePlayInMotionDao.class);
		return dao.get(id);
	}

	public Object getAll() {
		RolePlayInMotionDao dao = (RolePlayInMotionDao) getApplicationContext().getBean("rolePlayInMotionDao", RolePlayInMotionDao.class);
		return dao.getAll();
	}

	public Long getRolePlayId() {
		return rolePlayId;
	}

	public void setRolePlayId(Long rolePlayId) {
		this.rolePlayId = rolePlayId;
	}

	public String getRoleplayInMotionName() {
		return roleplayInMotionName;
	}

	public void setRoleplayInMotionName(String roleplayInMotionName) {
		this.roleplayInMotionName = roleplayInMotionName;
	}

	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public void save() {
		RolePlayInMotionDao dao = (RolePlayInMotionDao) getApplicationContext().getBean("rolePlayInMotionDao", RolePlayInMotionDao.class);
		dao.save(this);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

	public Phase getMyPhase() {

		// This should already be set, and the 3 lines below may be removed.
		if (phaseId == null) {
			phaseId = (new Phase().getFirstForRoleplay(this.rolePlayId));
		}
		
		return (new Phase().getById(this.phaseId));

	}
	
}
