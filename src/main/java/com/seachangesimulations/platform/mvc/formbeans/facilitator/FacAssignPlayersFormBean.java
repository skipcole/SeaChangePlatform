package com.seachangesimulations.platform.mvc.formbeans.facilitator;

import java.util.Hashtable;
import java.util.List;

import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;

public class FacAssignPlayersFormBean {

	private String userName;
	
	private Long praId;
	
	public FacAssignPlayersFormBean() {
		
	}
	
	public Hashtable <Long, String> roleTypeAssignments = new Hashtable<Long, String>();
	
	public FacAssignPlayersFormBean(List <PersonRoleplayAssignment> praInfo){
		
		for (PersonRoleplayAssignment pra: praInfo){
			roleTypeAssignments.put(pra.getId(), pra.getRoleType() + "");
		}
	}
	
	/** If this actor has been designated as a normal, observer or control role. */
	private int roleType = PersonRoleplayAssignment.NORMAL_ROLE;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public Long getPraId() {
		return praId;
	}

	public void setPraId(Long praId) {
		this.praId = praId;
	}

	
	
}
