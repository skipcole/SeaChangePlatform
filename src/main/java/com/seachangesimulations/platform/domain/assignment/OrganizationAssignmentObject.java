package com.seachangesimulations.platform.domain.assignment;

import javax.persistence.MappedSuperclass;

import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.OrganizationAssignmentObjectDao;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;

@Component
@MappedSuperclass
public class OrganizationAssignmentObject extends BaseSCPlatformObject{
	
	protected Long organizationId;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public OrganizationAssignmentObject getById(Long id){
		OrganizationAssignmentObjectDao dao = (OrganizationAssignmentObjectDao) getApplicationContext().getBean("organizationAssignmentObjectDao", OrganizationAssignmentObjectDao.class);
		return dao.get(id);
	}
	
}
