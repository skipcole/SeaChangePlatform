package com.seachangesimulations.platform.mvc.formbeans.facilitator;

import org.springframework.beans.BeanUtils;

import com.seachangesimulations.platform.domain.Roleplay;

public class FacCreateRPIMFormBean {
	
	private String roleplayName;
	
	private String roleplayInMotionName = "";

	public FacCreateRPIMFormBean() {

	}
	
	public FacCreateRPIMFormBean(Roleplay roleplay) {
		BeanUtils.copyProperties(roleplay,  this);
	}

	public String getRoleplayName() {
		return roleplayName;
	}

	public void setRoleplayName(String roleplayName) {
		this.roleplayName = roleplayName;
	}

	public String getRoleplayInMotionName() {
		return roleplayInMotionName;
	}

	public void setRoleplayInMotionName(String roleplayInMotionName) {
		this.roleplayInMotionName = roleplayInMotionName;
	}
	
	
	
	

}
