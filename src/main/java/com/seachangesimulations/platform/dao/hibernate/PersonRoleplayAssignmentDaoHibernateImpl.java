
package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.PersonRoleplayAssignmentDao;
import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;

@Repository("personRoleplayAssignmentDao")
public class PersonRoleplayAssignmentDaoHibernateImpl extends BaseDaoHibernateImpl<PersonRoleplayAssignment> implements
		PersonRoleplayAssignmentDao {

	public PersonRoleplayAssignmentDaoHibernateImpl() {
		super(PersonRoleplayAssignment.class);
	}

	@Override
	public List<PersonRoleplayAssignment> getAllRoleplaysForPerson(Long personId) {
		List <PersonRoleplayAssignment> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from PersonRoleplayAssignment where personId = :personId ")
				.setLong("personId", personId);
		
		returnList = query.list();

		session.close();
		
		return returnList;
	}

	@Override
	public List<PersonRoleplayAssignment> getAllForActorRpimId(Long actorId, Long rpimId) {
		
		List <PersonRoleplayAssignment> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from PersonRoleplayAssignment where actorId = :actorId and rpimId = :rpimId")
				.setLong("actorId", actorId)
				.setLong("rpimId", rpimId);
		
		returnList = query.list();

		session.close();
		
		return returnList;
	}
	
	@Override
	public List<PersonRoleplayAssignment> getAllForRpimId(Long rpimId) {
		
		List <PersonRoleplayAssignment> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from PersonRoleplayAssignment where rpimId = :rpimId")
				.setLong("rpimId", rpimId);
		
		returnList = query.list();

		session.close();
		
		return returnList;
	}

}