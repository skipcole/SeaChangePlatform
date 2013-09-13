
package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface PersonRoleplayAssignmentDao extends BaseDao<PersonRoleplayAssignment> {

	public List<PersonRoleplayAssignment> getAllRoleplaysForPerson(Long pId);
}
