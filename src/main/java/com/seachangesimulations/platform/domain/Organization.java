
package com.seachangesimulations.platform.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.OrganizationDao;
import com.seachangesimulations.platform.dao.PersonDao;

@Entity
@Component
@Scope("prototype")
public class Organization extends BaseSCPlatformObject {

	public Organization() {

	}
	
	public Organization getByName(String name){
		
		OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean("organizationDao", OrganizationDao.class);
		return organizationDao.getByName(name);
	}

	public Long create(String name) {
		OrganizationDao dao = (OrganizationDao) getApplicationContext().getBean("organizationDao",
				OrganizationDao.class);

		return dao.create(name);
	}

	public List<Object[]> searchFor(Map<String, String> params) {
		OrganizationDao dao = (OrganizationDao) getApplicationContext().getBean("organizationDao",
				OrganizationDao.class);		
		return dao.searchFor(params);
	}
	
	public List <Organization> getAll() {
		OrganizationDao dao = (OrganizationDao) getApplicationContext().getBean("organizationDao",
				OrganizationDao.class);		
		return dao.getAll();
	}

	/*** 
	 * Return the HQL database column names as a List<String>.
	 */
	public List<String> getDBColumnNames() {
		OrganizationDao dao = (OrganizationDao) getApplicationContext().getBean("organizationDao",
				OrganizationDao.class);		
		return dao.getDBColumnNames();
	}
	
	@Column(unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void save(){
		OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean("organizationDao", OrganizationDao.class);
		organizationDao.save(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Organization getById(Long id){
		OrganizationDao dao = (OrganizationDao) getApplicationContext().getBean("organizationDao", OrganizationDao.class);
		return dao.get(id);
	}
	
}
