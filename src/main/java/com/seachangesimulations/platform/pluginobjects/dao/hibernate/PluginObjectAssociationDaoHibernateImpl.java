package com.seachangesimulations.platform.pluginobjects.dao.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.hibernate.BaseDaoHibernateImpl;
import com.seachangesimulations.platform.pluginobjects.BasePluginObject;
import com.seachangesimulations.platform.pluginobjects.PluginObjectAssociation;
import com.seachangesimulations.platform.pluginobjects.dao.PluginObjectAssociationDao;

@Repository("pluginObjectAssociationDao")
public class PluginObjectAssociationDaoHibernateImpl extends
		BaseDaoHibernateImpl<PluginObjectAssociation> implements
		PluginObjectAssociationDao {

	public PluginObjectAssociationDaoHibernateImpl() {
		super(PluginObjectAssociation.class);
	}

	@Override
	public List<PluginObjectAssociation> getAllForPlugin(Long pluginId) {
		List<PluginObjectAssociation> returnList;

		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery(
				"from PluginObjectAssociation where pluginId = :pluginId order by objectIndex").setLong(
				"pluginId", pluginId);

		returnList = query.list();

		session.close();

		return returnList;
	}

	@Override
	public List<BasePluginObject> getAllObjectsForPlugin(Long pluginId) {
		
		List <PluginObjectAssociation> poAssocios = getAllForPlugin(pluginId);
		
		List allObjectsOnPlugin = new ArrayList<BasePluginObject>();
		
		// TODO Loop over objects, and pull them out
		
		Collections.sort(allObjectsOnPlugin);
		
		return allObjectsOnPlugin;
	}

	@Override
	public List<PluginObjectAssociation> getAllForPlugin(Long pluginId, String objectType) {
		List<PluginObjectAssociation> returnList;

		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		System.out.println("               ");
		
		System.out.println("p:o/" + pluginId + "/" + objectType);
		System.out.println("from PluginObjectAssociation where pluginId = :pluginId and objectType = :objectType order by objectIndex");

		System.out.println("               ");
		System.out.println("               ");
		Query query = session.createQuery(
				"from PluginObjectAssociation where pluginId = :pluginId and objectType = :objectType order by objectIndex")
				.setLong("pluginId", pluginId)
				.setString("objectType", objectType);

		returnList = query.list();

		session.close();

		return returnList;
	}

}
