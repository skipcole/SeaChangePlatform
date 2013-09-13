package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.PluginFile;

public interface PluginFileDao  extends BaseDao<PluginFile>  {

	public List<PluginFile> getAllForPlugin(Long pluginId);
}
