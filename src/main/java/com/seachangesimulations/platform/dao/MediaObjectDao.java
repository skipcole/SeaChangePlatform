package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.Actor;
import com.seachangesimulations.platform.domain.MediaObject;;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface MediaObjectDao extends BaseDao<MediaObject>  {

	public Long create(String name, Long rpId, Long orgId);

	public List<Actor> getAllForRoleplay(Long rpId);
	
}
