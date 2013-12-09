package com.seachangesimulations.platform.domain.assignment;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.InstructorRoleplayAssignmentDao;


@Entity
@Component
@Scope("prototype")
public class InstructorRoleplayAssignment extends OrganizationAssignmentObject {

	private Long instructorId;
	
	private Long rpimId;

	public Long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}

	public Long getRpimId() {
		return rpimId;
	}

	public void setRpimId(Long rpimId) {
		this.rpimId = rpimId;
	}

	public void save() {
		InstructorRoleplayAssignmentDao dao = 
				(InstructorRoleplayAssignmentDao) getApplicationContext().getBean("instructorRoleplayAssignmentDao", InstructorRoleplayAssignmentDao.class);
		dao.save(this);
	}
	
	
}
