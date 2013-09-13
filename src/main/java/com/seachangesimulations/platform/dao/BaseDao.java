
package com.seachangesimulations.platform.dao;

import java.util.List;

import com.seachangesimulations.platform.domain.BaseSCPlatformObject;

public interface BaseDao<T extends BaseSCPlatformObject> {

	public T get(Long id);

	public List<T> getAll();

	public void save(T object);

	public void delete(T object);

	public void indexEntity(T object);

	public void indexAllItems();

}
