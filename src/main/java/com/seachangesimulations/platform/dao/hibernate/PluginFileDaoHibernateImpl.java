package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.PluginFileDao;
import com.seachangesimulations.platform.domain.PluginFile;

@Repository("pluginFileDao")
public class PluginFileDaoHibernateImpl extends BaseDaoHibernateImpl<PluginFile> implements PluginFileDao{

	@Override
	public List<PluginFile> getAllForPlugin(Long pId) {
		List <PluginFile> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from PluginFile where pluginId = :pId");
		returnList = query.setLong("pId", pId).list();
		session.close();
		
		return returnList;
	}

	
}
