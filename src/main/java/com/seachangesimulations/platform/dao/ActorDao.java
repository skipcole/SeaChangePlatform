
package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.Actor;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface ActorDao extends BaseDao<Actor> {

	public Long create(String name, Long rpId, Long orgId);

	public List<Actor> getAllForRoleplay(Long rpId);
}
