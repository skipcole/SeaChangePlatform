
package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.assignment.PersonOrganizationAssignment;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface PersonOrganizationAssignmentDao extends BaseDao<PersonOrganizationAssignment> {

	public PersonOrganizationAssignment getByPersonAndOrg(Long personId, Long organizationId);

	public List<PersonOrganizationAssignment> getByPerson(Long personId);

	public void create(Long personId, Long orgId, int authLevel);
}