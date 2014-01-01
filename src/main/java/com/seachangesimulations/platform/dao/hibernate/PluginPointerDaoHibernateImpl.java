package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.PluginPointerDao;
import com.seachangesimulations.platform.domain.PluginPointer;

@Repository("pluginPointerDao")
public class PluginPointerDaoHibernateImpl extends BaseDaoHibernateImpl<PluginPointer> implements PluginPointerDao{

	public PluginPointerDaoHibernateImpl() {
		super(PluginPointer.class);
	}

	@Override
	public List<PluginPointer> getAllForRoleplay(Long rpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List <PluginPointer> getCurrentSet(Long rpId, Long aId, Long phId) {
		List <PluginPointer> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from PluginPointer where roleplayId = :rpId and actorId = :aId and phaseId = :phId")
				.setLong("rpId", rpId).setLong("aId", aId).setLong("phId", phId);
		
		returnList = query.list();

		session.close();
		
		return returnList;
	}
	
	@Override
	public PluginPointer getControlPluginByHandle(String handle) {
		
		PluginPointer returnPluginPointer = new PluginPointer();
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from PluginPointer where systemPluginHandle = :handle")
				.setString("handle", handle);
		
		returnPluginPointer = (PluginPointer) query.uniqueResult();

		session.close();
		
		return returnPluginPointer;
	}

	@Override
	public PluginPointer getByPlayerValues(Long roleplayId2, Long actorId2, Long phaseId2, Long pluginIndex) {
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from PluginPointer where roleplayId = :rpId and actorId = :aId and phaseId = :phId  and pluginIndex = :pluginIndex")
				.setLong("rpId", roleplayId2).setLong("aId", actorId2).setLong("phId", phaseId2).setLong("pluginIndex", pluginIndex);
		
		PluginPointer pp = (PluginPointer) query.uniqueResult();

		session.close();
		
		return pp;
	}


}
