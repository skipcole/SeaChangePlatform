package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.AlertDao;
import com.seachangesimulations.platform.rpimobjects.Alert;

@Repository("alertDao")
public class AlertDaoHibernateImpl extends BaseDaoHibernateImpl<Alert> implements AlertDao{

	public AlertDaoHibernateImpl() {
		super(Alert.class);
	}

	@Override
	public List<Alert> getPlayersAlerts(Long targetPersonId, Long targetActorId,
			Long rpimId, Long lastAlertReceived) {

		List <Alert> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		
		String queryString = "from Alert where targetPersonId = :targetPersonId and targetActorId = :targetActorId " +
				" and rpimId = :rpimId and id > :lastAlertReceived order by id";

		Query query = session.createQuery(queryString)
				.setLong("targetPersonId", targetPersonId)
				.setLong("targetActorId", targetActorId)
				.setLong("rpimId", rpimId)
				.setLong("lastAlertReceived", lastAlertReceived);
		
		returnList = query.list();

		session.close();
		
		return returnList;
	}
	
}
