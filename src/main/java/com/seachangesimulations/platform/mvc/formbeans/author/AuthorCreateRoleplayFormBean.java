package com.seachangesimulations.platform.mvc.formbeans.author;

import org.springframework.beans.BeanUtils;

import com.seachangesimulations.platform.domain.Roleplay;

public class AuthorCreateRoleplayFormBean {

	private String roleplayName;
	
	public AuthorCreateRoleplayFormBean() {

	}
	public AuthorCreateRoleplayFormBean(Roleplay roleplay) {
		BeanUtils.copyProperties(roleplay, this);
	}
	public String getRoleplayName() {
		return roleplayName;
	}
	public void setRoleplayName(String roleplayName) {
		this.roleplayName = roleplayName;
	}

	
}
