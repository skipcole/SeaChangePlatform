package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.assignment.InstructorRoleplayAssignment;

public interface InstructorRoleplayAssignmentDao extends BaseDao<InstructorRoleplayAssignment> {
	 
	public List<InstructorRoleplayAssignment> getAllForInstructor(Long iId);
}
