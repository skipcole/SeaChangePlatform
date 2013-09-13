
package com.seachangesimulations.platform.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.RolePlayDao;
import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.Roleplay;

@Repository("rolePlayDao")
public class RolePlayDaoHibernateImpl extends BaseDaoHibernateImpl<Roleplay> implements RolePlayDao {

	public RolePlayDaoHibernateImpl() {
		super(Roleplay.class);
	}

	public Long create(String rolePlayName, Long orgId) {
		Roleplay rp = new Roleplay();
		rp.setRoleplayName(rolePlayName);
		rp.setInitialOrgId(orgId);
		this.save(rp);
		return rp.getId();
	}

	@Override
	public List<Roleplay> getAllForOrganization(Long orgId) {

		List returnList;
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Roleplay where organizationId = :orgId ")
				.setLong("orgId", orgId);
		
		returnList = query.list();

		session.close();
		
		return returnList;
	}

}