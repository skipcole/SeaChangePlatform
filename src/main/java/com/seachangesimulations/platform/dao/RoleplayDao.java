
package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.Roleplay;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface RoleplayDao extends BaseDao<Roleplay> {

	public Long create(String rolePlayName, Long orgId);
	
	public List<Roleplay> getAllForOrganization(Long orgId);
	
}
