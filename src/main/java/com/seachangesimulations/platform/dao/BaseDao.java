
package com.seachangesimulations.platform.dao;

import java.util.List;
import java.util.Map;

import com.seachangesimulations.platform.domain.BaseSCPlatformObject;

public interface BaseDao<T extends BaseSCPlatformObject> {

	public T get(Long id);

	public List<T> getAll();
	
	public List<Object[]> searchFor(Map<String, String> params);
	
	public List<String> getDBColumnNames();

	public void save(T object);

	public void delete(T object);

	public void indexEntity(T object);

	public void indexAllItems();

}
