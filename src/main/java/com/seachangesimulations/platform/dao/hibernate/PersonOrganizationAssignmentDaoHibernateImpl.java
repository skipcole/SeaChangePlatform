
package com.seachangesimulations.platform.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.PersonOrganizationAssignmentDao;
import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.assignment.PersonOrganizationAssignment;

@Repository("personOrganizationAssignmentDao")
public class PersonOrganizationAssignmentDaoHibernateImpl extends BaseDaoHibernateImpl<PersonOrganizationAssignment>
		implements PersonOrganizationAssignmentDao {

	public PersonOrganizationAssignmentDaoHibernateImpl() {
		super(PersonOrganizationAssignment.class);
	}

	public PersonOrganizationAssignment getByPersonAndOrg(Long personId, Long organizationId) {

		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session
				.createQuery(
						"from PersonOrganizationAssignment where personId = :personId AND organizationId = :organizationId ")
				.setLong("personId", personId).setLong("organizationId", organizationId);

		PersonOrganizationAssignment p = (PersonOrganizationAssignment) query.uniqueResult();

		session.close();

		return p;

	}

	public List getByPerson(Long personId) {

		List returnList;

		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from PersonOrganizationAssignment where personId = :personId").setLong(
				"personId", personId);

		returnList = query.list();

		session.close();

		return returnList;

	}

	@Override
	public void create(Long personId, Long orgId, int authLevel) {
		// TODO Auto-generated method stub
		PersonOrganizationAssignment poa = new PersonOrganizationAssignment();
		poa.setPersonId(personId);
		poa.setInitialOrgId(orgId);
		poa.setOrganizationId(orgId);
		poa.setAuthorizationLevel(authLevel);
		poa.save();
	}

}
