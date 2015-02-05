package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.MessageDao;
import com.seachangesimulations.platform.rpimobjects.Message;

@Repository("alertDao")
public class MessageDaoHibernateImpl extends BaseDaoHibernateImpl<Message> implements MessageDao{

	public MessageDaoHibernateImpl() {
		super(Message.class);
	}

	@Override
	public List<Message> getPlayersPersonalMessages(Long targetPersonId,
			Long rpimId, Long lastMessageReceived) {

		List <Message> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		
		String queryString = "from Message where targetPersonId = :targetPersonId  " +
				" and rpimId = :rpimId and id > :lastMessageReceived order by id";

		Query query = session.createQuery(queryString)
				.setLong("targetPersonId", targetPersonId)
				.setLong("rpimId", rpimId)
				.setLong("lastMessageReceived", lastMessageReceived);
		
		returnList = query.list();

		session.close();
		
		return returnList;
	}

	@Override
	public List<Message> getPlayersRoleMessages(Long actorId, Long rpimId,
			Long lastMessageReceived) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
