package com.seachangesimulations.platform.mvc.formbeans.author;

import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.seachangesimulations.platform.domain.Actor;

public class AuthorCreateActorFormBean {

	public AuthorCreateActorFormBean() {
		
	}
	
	public AuthorCreateActorFormBean(Actor actor){
		BeanUtils.copyProperties(actor, this);
	}
	
	/** Id of the role play that this object is associated with. */
	private Long roleplayId;

	/** Name of the Actor */
	@Size(min = 1, max = 50)
	private String actorName;
	
	private String publicDescription;
	
	private boolean controlCharacter = false;
	

	public Long getRoleplayId() {
		return roleplayId;
	}

	public void setRoleplayId(Long roleplayId) {
		this.roleplayId = roleplayId;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}


	public String getPublicDescription() {
		return publicDescription;
	}

	public void setPublicDescription(String publicDescription) {
		this.publicDescription = publicDescription;
	}

	public boolean isControlCharacter() {
		return controlCharacter;
	}

	public void setControlCharacter(boolean controlCharacter) {
		this.controlCharacter = controlCharacter;
	}
	
	
	
}
