
package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.PhaseDao;
import com.seachangesimulations.platform.domain.Phase;

@Repository("phaseDao")
public class PhaseDaoHibernateImpl extends BaseDaoHibernateImpl<Phase> implements PhaseDao {

	public PhaseDaoHibernateImpl() {
		super(Phase.class);
	}

	@Override
	public Long create(String name, Long rpId, Long orgId) {
		
		Phase phase = new Phase();
		phase.setPhaseName(name);
		phase.setInitialOrgId(orgId);
		phase.setRoleplayId(rpId);
		this.save(phase);
		return phase.getId();
	}

	@Override
	public List<Phase> getAllForRoleplay(Long rpId) {
		List <Phase> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Phase where roleplayId = :rpId order by phaseOrder")
				.setLong("rpId", rpId);
		
		returnList = query.list();

		session.close();
		
		return returnList;
	}

	@Override
	public Phase getFirstPhaseForRoleplay(Long rId) {
		List <Phase> returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Phase where roleplayId = :rId and isFirstPhase is true")
				.setLong("rId", rId);
		
		returnList = query.list();

		session.close();
		
		Phase intialPhase = returnList.get(0);
		
		return intialPhase;
		

	}

}