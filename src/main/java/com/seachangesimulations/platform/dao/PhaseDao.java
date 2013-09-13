
package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.Phase;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface PhaseDao extends BaseDao<Phase> {

	public Long create(String name, Long rpId, Long orgId);

	public List<Phase> getAllForRoleplay(Long rpId);

	public Phase getFirstPhaseForRoleplay(Long rId);
}
