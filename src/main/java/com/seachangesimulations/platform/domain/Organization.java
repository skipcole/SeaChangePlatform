
package com.seachangesimulations.platform.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.ActorDao;
import com.seachangesimulations.platform.dao.OrganizationDao;

@Entity
@Component
@Scope("prototype")
public class Organization extends BaseSCPlatformObject {

	public Organization() {

	}

	public Long create(String name) {
		OrganizationDao dao = (OrganizationDao) getApplicationContext().getBean("organizationDao",
				OrganizationDao.class);

		return dao.create(name);
	}

	public List <Organization> getAll() {
		OrganizationDao dao = (OrganizationDao) getApplicationContext().getBean("organizationDao",
				OrganizationDao.class);
		
		return dao.getAll();
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
