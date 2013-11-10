package com.seachangesimulations.platform.pluginobjects.dao;

import java.util.List;

import com.seachangesimulations.platform.dao.BaseDao;
import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;

public interface PluginObjectDocumentDao extends BaseDao<PluginObjectDocument>{

	List<PluginObjectDocument> getAllForPlugin(Long pluginId);

	PluginObjectDocument getByRPimIdPluginIdAndIndex(Long rpimId, Long pluginId, int docIndex);

}
