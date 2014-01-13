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

	@Override
	public List<PluginObjectAssociation> getAllForPlugin(Long pluginId, Long rpId, Long rpimId) {

		List<PluginObjectAssociation> returnList;
		
		String queryHQL = "from PluginObjectAssociation where pluginId = :pluginId ";
		
		if (rpId != null) {
			queryHQL += " and rpId = " + rpId.intValue() + " ";
		} else {
			queryHQL += " and rpId is null and rpimId is null ";
		}

		if (rpimId != null) {
			queryHQL += " and rpimId = " + rpimId.intValue() + " ";
		} else {
			queryHQL += " and rpId is null ";
		}
				
		queryHQL += " order by objectIndex";
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery(queryHQL)
				.setLong("pluginId", pluginId);

		returnList = query.list();

		session.close();

		return returnList;
		
	}

	/**
	 * 
	 */
	@Override
	public List<PluginObjectAssociation> getAllForRoleplay(Long rpId, String canonicalName) {
		List<PluginObjectAssociation> returnList;

		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery(
				"from PluginObjectAssociation where rpId = :roleplayId and objectType = :objectType order by objectIndex")
				.setLong("roleplayId", rpId)
				.setString("objectType", canonicalName);

		returnList = query.list();

		session.close();

		return returnList;
	}

	@Override
	public List<PluginObjectAssociation> getAllForRoleplay(Long rpId) {
		List<PluginObjectAssociation> returnList;

		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery(
				"from PluginObjectAssociation where rpId = :rpId order by objectIndex").setLong(
				"rpId", rpId);

		returnList = query.list();

		session.close();

		return returnList;
	}

}
