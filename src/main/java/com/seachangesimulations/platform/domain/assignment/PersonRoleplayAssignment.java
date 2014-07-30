package com.seachangesimulations.platform.domain.assignment;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.PersonRoleplayAssignmentDao;
import com.seachangesimulations.platform.domain.Actor;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;
import com.seachangesimulations.platform.utilities.Util;

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

	/** Id of the person assigned to this actor for this roleplay. */
	private Long personId;

	private Long rpimId;

	/** Id of the role play that this object is associated with. */
	private Long roleplayId;

	private Long actorId;
	
	private String actorName;
	
	private String roleplayName;
	
	private String rpimName;
	
	private String normalSelected = "";
	private String controlSelected = "";
	private String observerSelected = "";
	
	public static final int NORMAL_ROLE = 0;
	public static final int CONTROL_ROLE = 1;
	public static final int OBSERVER_ROLE = 2;
	
	/** If this actor has been designated as a normal, observer or control role. */
	private int roleType = NORMAL_ROLE;
	
	/** Kept here so method isControl is easily reachable by Spring. */
	@SuppressWarnings("unused")
	private boolean control = false;

	@SuppressWarnings("unchecked")
	@Override
	public PersonRoleplayAssignment getById(Long id) {
		PersonRoleplayAssignmentDao dao = (PersonRoleplayAssignmentDao) getApplicationContext().getBean(
				"personRoleplayAssignmentDao", PersonRoleplayAssignmentDao.class);
		return dao.get(id);
	}
	
	public List <PersonRoleplayAssignment> getAllPlayers(Long rpimId){
		
		PersonRoleplayAssignmentDao dao = (PersonRoleplayAssignmentDao) getApplicationContext().getBean(
				"personRoleplayAssignmentDao", PersonRoleplayAssignmentDao.class);
		
		return dao.getAllForRpimId(rpimId);
		
	}
	
	/** Returns a list of all of the person-roleplay assignments (PRAs) for this roleplay. If no assignments have been
	 * made for a particular role, it creates an empty PRA as a placeholder for it.
	 * 
	 * @param rpimId
	 * @return
	 */
	public List <PersonRoleplayAssignment> getRolePlayAssignments(Long rpimId){
		
		List <PersonRoleplayAssignment> returnList = new ArrayList <PersonRoleplayAssignment>();
		
		List <Actor> allActors = new Actor().getAllForRoleplay(rpimId);
		
		for (Actor actor: allActors){
			
			// Get all pras
			List <PersonRoleplayAssignment> setOfAssignments = getAllForActorRpimId(actor.getId(), rpimId);
			
			// if none found, create a starter one
			if ((setOfAssignments == null) || (setOfAssignments.size() ==0)){
				PersonRoleplayAssignment pra = new PersonRoleplayAssignment();
				pra.setActorId(actor.getId());
				pra.setActorName(actor.getActorName());
				pra.setRpimId(rpimId);
				pra.setRoleType(actor.getRoleType());
				
				pra.save();
				
				returnList.add(pra);
			} else {
				returnList.addAll(setOfAssignments);
			}
			
		} // End of loop over actors
		
		return returnList;
		
	}

	private List getAllForActorRpimId(Long actorId, Long rpimId) {
		PersonRoleplayAssignmentDao dao = (PersonRoleplayAssignmentDao) getApplicationContext().getBean(
				"personRoleplayAssignmentDao", PersonRoleplayAssignmentDao.class);
		return dao.getAllForActorRpimId(actorId, rpimId);
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

	public Long getRoleplayId() {
		return roleplayId;
	}

	public void setRoleplayId(Long rolePlayId) {
		this.roleplayId = rolePlayId;
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

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public String getNormalSelected() {
		if (roleType == NORMAL_ROLE){
			normalSelected = " checked ";
		} else {
			normalSelected = " ";
		}
		return normalSelected;
	}

	public String getControlSelected() {
		if (roleType == CONTROL_ROLE){
			controlSelected = " checked ";
		} else {
			controlSelected = " ";
		}
		return controlSelected;
	}

	public String getObserverSelected() {
		if (roleType == OBSERVER_ROLE){
			observerSelected = " checked ";
		} else {
			observerSelected = " ";
		}
		return observerSelected;
	}

	public boolean isControl() {
		if (roleType == CONTROL_ROLE){
			return true;
		} else {
			return false;
		}
	}

	
	
	
	
}
