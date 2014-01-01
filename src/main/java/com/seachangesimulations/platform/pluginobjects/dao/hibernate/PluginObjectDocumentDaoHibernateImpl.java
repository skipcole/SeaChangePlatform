package com.seachangesimulations.platform.pluginobjects.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.hibernate.BaseDaoHibernateImpl;
import com.seachangesimulations.platform.pluginobjects.PluginObjectAssociation;
import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;
import com.seachangesimulations.platform.pluginobjects.dao.PluginObjectDocumentDao;

@Repository("pluginObjectDocumentDao")
public class PluginObjectDocumentDaoHibernateImpl extends
		BaseDaoHibernateImpl<PluginObjectDocument> implements
		PluginObjectDocumentDao {

	public PluginObjectDocumentDaoHibernateImpl() {
		super(PluginObjectDocument.class);
	}

	@Override
	public List<PluginObjectDocument> getAllForPlugin(Long pluginId) {
		List<PluginObjectDocument> returnList;

		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery(
				"from PluginObjectDocument where roleplayId = :rpId ").setLong(
				"rpId", pluginId);

		returnList = query.list();

		session.close();

		return returnList;
	}

	@Override
	public PluginObjectDocument getByRPimIdPluginIdAndIndex(Long rpimId, Long pluginId, int docIndex) {
		List<PluginObjectAssociation> returnList;

		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery(
				"from PluginObjectAssociation where rpimId = :rpimId and pluginId = :pluginId and objectIndex = :docIndex")
				.setLong("rpimId", rpimId).setLong("pluginId", pluginId).setInteger("docIndex", docIndex);

		PluginObjectAssociation poa = (PluginObjectAssociation) query.uniqueResult();
		
		session.close();
		PluginObjectDocument pod = new PluginObjectDocument().getById(poa.getObjectId());

		return pod;
	}

	


}
