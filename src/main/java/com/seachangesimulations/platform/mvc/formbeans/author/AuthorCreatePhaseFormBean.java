package com.seachangesimulations.platform.mvc.formbeans.author;

import org.springframework.beans.BeanUtils;

import com.seachangesimulations.platform.domain.Phase;

public class AuthorCreatePhaseFormBean {
	
	
	private String phaseName;
	
	private Long roleplayId;
	
	public AuthorCreatePhaseFormBean() {
		
	}
	
	public AuthorCreatePhaseFormBean(Phase phase){
		BeanUtils.copyProperties(phase, this);
	}
	
	
	

	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public Long getRoleplayId() {
		return roleplayId;
	}

	public void setRoleplayId(Long roleplayId) {
		this.roleplayId = roleplayId;
	}
	
	

}
