
package com.seachangesimulations.platform.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.seachangesimulations.platform.dao.OrganizationDao;
import com.seachangesimulations.platform.domain.Organization;

@Repository("organizationDao")
public class OrganizationDaoHibernateImpl extends BaseDaoHibernateImpl<Organization> implements OrganizationDao {

	public OrganizationDaoHibernateImpl() {
		super(Organization.class);
	}

	public Long create(String name) {

		Organization org = new Organization();
		org.setName(name);
		
		this.save(org);
		return org.getId();
	}

	public Organization getByName(String name) {

		Session session = getSessionFactory().openSession();
		Query query = session.createQuery("from Organization where name = :name").setString("name", name);

		return (Organization) query.uniqueResult();

	}

}