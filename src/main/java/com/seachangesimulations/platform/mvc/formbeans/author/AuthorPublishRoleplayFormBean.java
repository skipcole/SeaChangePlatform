package com.seachangesimulations.platform.mvc.formbeans.author;

import org.springframework.beans.BeanUtils;

import com.seachangesimulations.platform.domain.Roleplay;

public class AuthorPublishRoleplayFormBean {
	
	private String makeTestableButton;
	
	private String publishButton;
	
	private String roleplayName;
	
	private int publishedState;
	
	public AuthorPublishRoleplayFormBean() {

	}
	public AuthorPublishRoleplayFormBean(Roleplay roleplay) {
		BeanUtils.copyProperties(roleplay, this);
	}
	public String getRoleplayName() {
		return roleplayName;
	}
	public void setRoleplayName(String roleplayName) {
		this.roleplayName = roleplayName;
	}
	public int getPublishedState() {
		return publishedState;
	}
	public void setPublishedState(int publishedState) {
		this.publishedState = publishedState;
	}
	public String getMakeTestableButton() {
		return makeTestableButton;
	}
	public void setMakeTestableButton(String makeTestableButton) {
		this.makeTestableButton = makeTestableButton;
	}
	public String getPublishButton() {
		return publishButton;
	}
	public void setPublishButton(String publishButton) {
		this.publishButton = publishButton;
	}
	
	
	
	
}
