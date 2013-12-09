
package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.PluginDao;
import com.seachangesimulations.platform.domain.Plugin;

@Repository("pluginDao")
public class PluginDaoHibernateImpl extends BaseDaoHibernateImpl<Plugin> implements PluginDao {

	public PluginDaoHibernateImpl() {
		super(Plugin.class);
	}

	@Override
	public List<Plugin> getAllUncustomized() {
		List <Plugin> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Plugin where customized is false and isSystemPlugin is false ");
		returnList = query.list();
		session.close();
		
		return returnList;
	}
}