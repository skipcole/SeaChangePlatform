package com.seachangesimulations.platform.pluginobjects.dao;

import java.util.List;

import com.seachangesimulations.platform.dao.BaseDao;
import com.seachangesimulations.platform.pluginobjects.BasePluginObject;
import com.seachangesimulations.platform.pluginobjects.PluginObjectAssociation;

public interface PluginObjectAssociationDao extends BaseDao<PluginObjectAssociation>{

	public List<PluginObjectAssociation> getAllForPlugin(Long pluginId);
	
	public List<PluginObjectAssociation> getAllForPlugin(Long pluginId, String objectTypeName);
	
	public List<BasePluginObject> getAllObjectsForPlugin(Long pluginId);
}
