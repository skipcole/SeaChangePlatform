
package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.Plugin;

/**
 * This Database Access Object (DAO) Interface is the contract that any supporting persistence layer must meet to
 * function correctly in this software.
 * 
 */
public interface PluginDao extends BaseDao<Plugin> {

	public List<Plugin> getAllUncustomized();
	
}
