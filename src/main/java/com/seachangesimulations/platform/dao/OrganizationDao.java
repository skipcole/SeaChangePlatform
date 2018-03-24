
package com.seachangesimulations.platform.dao;


import java.util.List;
import java.util.Map;

import com.seachangesimulations.platform.domain.Organization;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface OrganizationDao extends BaseDao<Organization> {

	public Long create(String name);

	public Organization getByName(String name);
	
}
