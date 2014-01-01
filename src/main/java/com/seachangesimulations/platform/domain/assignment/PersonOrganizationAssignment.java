
package com.seachangesimulations.platform.domain.assignment;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.PersonOrganizationAssignmentDao;


@Entity
@Component
@Scope("prototype")
public class PersonOrganizationAssignment extends OrganizationAssignmentObject {

	public PersonOrganizationAssignment() {

	}

	private Long personId;

	public static final int ADMIN_LEVEL = 50;
	
	public static final int DEV_LEVEL = 42;

	public static final int AUTHOR_LEVEL = 30;

	public static final int FACILITATOR_LEVEL = 20;

	public static final int PLAYER_LEVEL = 10;
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_DEV = "ROLE_DEV";
	public static final String ROLE_AUTHOR = "ROLE_AUTHOR";
	public static final String ROLE_FACILITATOR = "ROLE_FACILITATOR";
	public static final String ROLE_PLAYER = "ROLE_PLAYER";
	

	private Long authorizationLevel;

	public Long getAuthorizationLevel() {
		return authorizationLevel;
	}

	public void setAuthorizationLevel(int authorizationLevel) {
		this.authorizationLevel = new Long(authorizationLevel);
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public PersonOrganizationAssignment getByPersonAndOrg(Long personId, Long organizationId) {
		
		if ((personId == null) || (organizationId == null)){
			System.out.println("PersonOrganizationAssignment getByPersonAndOrg, p/o: " + personId + "/" + organizationId);
		}
		

		PersonOrganizationAssignmentDao dao = (PersonOrganizationAssignmentDao) getApplicationContext().getBean(
				"personOrganizationAssignmentDao", PersonOrganizationAssignmentDao.class);

		return (dao.getByPersonAndOrg(personId, organizationId));

	}

	public List<PersonOrganizationAssignment> getByPerson(Long personId) {

		PersonOrganizationAssignmentDao dao = (PersonOrganizationAssignmentDao) getApplicationContext().getBean(
				"personOrganizationAssignmentDao", PersonOrganizationAssignmentDao.class);

		return (dao.getByPerson(personId));

	}

	public void save() {
		PersonOrganizationAssignmentDao dao = (PersonOrganizationAssignmentDao) getApplicationContext().getBean(
				"personOrganizationAssignmentDao", PersonOrganizationAssignmentDao.class);
		dao.save(this);
	}
	
	public void create(Long personId, Long orgId, int authLevel){
		PersonOrganizationAssignmentDao dao = (PersonOrganizationAssignmentDao) getApplicationContext().getBean(
				"personOrganizationAssignmentDao", PersonOrganizationAssignmentDao.class);
		
		dao.create(personId, orgId, authLevel);
		
	}

}
