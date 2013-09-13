
package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seachangesimulations.platform.dao.ActorDao;
import com.seachangesimulations.platform.domain.Actor;

@Repository("actorDao")
public class ActorDaoHibernateImpl extends BaseDaoHibernateImpl<Actor> implements ActorDao {

	public ActorDaoHibernateImpl() {
		super(Actor.class);
	}

	public Long create(String name, Long rpId, Long orgId) {

		Actor actor = new Actor();
		actor.setActorName(name);
		actor.setInitialOrgId(orgId);
		actor.setRoleplayId(rpId);
		this.save(actor);
		return actor.getId();
	}

	@Override
	public List<Actor> getAllForRoleplay(Long rpId) {
		
		List <Actor> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Actor where roleplayId = :rpId ")
				.setLong("rpId", rpId);
		
		returnList = query.list();

		session.close();
		
		return returnList;
	}

}
