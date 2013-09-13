package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.InstructorRoleplayAssignmentDao;
import com.seachangesimulations.platform.domain.assignment.InstructorRoleplayAssignment;

@Repository("instructorRoleplayAssignmentDao")
public class InstructorRoleplayAssignmentDaoHibernateImpl extends 
BaseDaoHibernateImpl<InstructorRoleplayAssignment> implements InstructorRoleplayAssignmentDao{

	@Override
	public List<InstructorRoleplayAssignment> getAllForInstructor(Long iId) {
		
		List <InstructorRoleplayAssignment> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from InstructorRoleplayAssignment where instructorId = :iId ")
				.setLong("iId", iId);
		
		returnList = query.list();

		session.close();
		
		return returnList;
	}

}
