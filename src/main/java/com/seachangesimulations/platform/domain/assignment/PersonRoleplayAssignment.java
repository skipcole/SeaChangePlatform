package com.seachangesimulations.platform.domain.assignment;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.PersonRoleplayAssignmentDao;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;

@Entity
@Component
@Scope("prototype")
public class PersonRoleplayAssignment extends BaseSCPlatformObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonRoleplayAssignment() {

	}

	private Long personId;

	private Long rpimId;

	/** Id of the role play that this object is associated with. */
	private Long rolePlayId;

	private Long actorId;
	
	private String actorName;
	
	private String roleplayName;
	
	private String rpimName;

	@SuppressWarnings("unchecked")
	@Override
	public PersonRoleplayAssignment getById(Long id) {
		PersonRoleplayAssignmentDao dao = (PersonRoleplayAssignmentDao) getApplicationContext().getBean(
				"personRoleplayAssignmentDao", PersonRoleplayAssignmentDao.class);
		return dao.get(id);
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getRpimId() {
		return rpimId;
	}

	public void setRpimId(Long rpimId) {
		this.rpimId = rpimId;
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

	public void save() {

		PersonRoleplayAssignmentDao dao = (PersonRoleplayAssignmentDao) getApplicationContext().getBean(
				"personRoleplayAssignmentDao", PersonRoleplayAssignmentDao.class);
		dao.save(this);

	}
	
	public List<PersonRoleplayAssignment> getAllRoleplaysForPerson(Long personId){
		
		PersonRoleplayAssignmentDao dao = (PersonRoleplayAssignmentDao) getApplicationContext().getBean(
				"personRoleplayAssignmentDao", PersonRoleplayAssignmentDao.class);
		
		return dao.getAllRoleplaysForPerson(personId);
		
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getRoleplayName() {
		return roleplayName;
	}

	public void setRoleplayName(String roleplayName) {
		this.roleplayName = roleplayName;
	}

	public String getRpimName() {
		return rpimName;
	}

	public void setRpimName(String rpimName) {
		this.rpimName = rpimName;
	}
	
	

}
