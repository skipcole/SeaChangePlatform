package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.PluginPointer;

public interface PluginPointerDao extends BaseDao<PluginPointer>{

	public List<PluginPointer> getAllForRoleplay(Long rpId);

	public List getCurrentSet(Long rpId, Long aId, Long phId);

	public PluginPointer getByPlayerValues(Long roleplayId2, Long actorId2, Long phaseId2, Long objectIndex);

	public PluginPointer getControlPluginByHandle(String handle);
	
}
