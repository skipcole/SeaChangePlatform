package com.seachangesimulations.platform.service;

import java.io.Serializable;
import java.util.Hashtable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.seachangesimulations.platform.domain.Actor;
import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.Roleplay;

@Service("sessionInfoBean")
@Scope("globalSession")
@XmlAccessorType (XmlAccessType.NONE)   // This means that if you want a field mapped, it has to be annotated.
public class SessionInfoBean{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	
	public static final String ADMIN_ZONE = "admin";
	public static final String DEVELOPER_ZONE = "developer";
	public static final String AUTHOR_ZONE = "author";
	public static final String FACILITATOR_ZONE = "facilitator";
	public static final String PLAYER_ZONE = "player";
	
	private static Hashtable<String, SessionInfoBean> allSessions = new Hashtable();
	
	public static SessionInfoBean getMySessionInfo(String uname) {
		return (SessionInfoBean) allSessions.get(uname);
	}
	
	public SessionInfoBean() {
		
	}
	
	/** Id of user login object - if it is null, the user is not logged in. */
	@XmlAttribute
	private Long platformLoginId;
	
	private String platformZone;

	/** Organization of the section where the user has entered. */
	private Long organizationId;

	/** Records if user is an admin in this organization. */
	private boolean isAdmin = false;

	/** Records if user is authorized to create role plays in this organization. */
	private boolean isAuthor = false;

	/**
	 * Records if user is authorized to facilitate role plays in this
	 * organization.
	 */
	private boolean isFacilitator = false;

	/** Records if user is authorized to create role plays in this organization. */
	private boolean isPlayer = false;
	
	/** Records if user is authorized to create role plays in this organization. */
	private boolean isControl = false;

	/**
	 * Id of this person logged in, not to be confused with the id of the
	 * 'platformLogin'.
	 */
	private Long personId;

	/** Records the email of this user. */
	private String personEmail = "";

	/** ID of Role Play being conducted or worked on. */
	@XmlAttribute
	private Long roleplayId;
	
	/** ID of Person Roleplay Assignment being conducted or worked on. */
	@XmlAttribute
	private Long praId;

	/** Name of simulation being conducted or worked on. */
	@XmlAttribute
	private String roleplayName = "";

	/** Version of the role play be conducted or worked on. */
	private String roleplayVersion = "";

	/** Organization that created the role play. */
	private String roleplayOrganization = "";

	/** ID of Phase being worked on or that the player is in. */
	@XmlAttribute
	private Long actorId = new Long(0);

	/** ID of Phase being worked on or that the player is in. */
	@XmlAttribute
	private Long phaseId = new Long(0);
	
	@XmlAttribute
	private String phaseName = "";
	
	/** Index of what plugin (tab) they are currently on. */
	@XmlAttribute
	private Long pluginIndex = new Long(0);
	
	/** Id of the plugin (tab) they are currently on. */
	@XmlAttribute
	private Long pluginId;

	/** ID of the Role Play in Motion being conducted or worked on. */
	@XmlAttribute
	private Long rolePlayInMotionId;

	/** Name of the Role Play in Motion session. */
	@XmlAttribute
	private String roleplayInMotionName = "";

	public Long getPlatformLoginId() {
		return platformLoginId;
	}

	public void setPlatformLoginId(Long platformLoginId) {
		this.platformLoginId = platformLoginId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isAuthor() {
		return isAuthor;
	}

	public void setAuthor(boolean isAuthor) {
		this.isAuthor = isAuthor;
	}

	public boolean isFacilitator() {
		return isFacilitator;
	}

	public void setFacilitator(boolean isFacilitator) {
		this.isFacilitator = isFacilitator;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	public boolean isControl() {
		return isControl;
	}

	public void setControl(boolean isControl) {
		this.isControl = isControl;
	}

	public Long getPersonId() {
		if (personId != null) {
			return personId;
		} else {
			Person person = new Person().getByUsername(this.personEmail);
			this.personId = person.getId();
			return personId;
		}
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public Long getRoleplayId() {
		return roleplayId;
	}

	public void setRoleplayId(Long roleplayId) {
		this.roleplayId = roleplayId;
	}
	
	public Long getPraId() {
		return praId;
	}

	public void setPraId(Long praId) {
		this.praId = praId;
	}

	public String getRoleplayName() {
		return roleplayName;
	}

	public void setRoleplayName(String roleplayName) {
		this.roleplayName = roleplayName;
	}

	public String getRoleplayVersion() {
		return roleplayVersion;
	}

	public void setRoleplayVersion(String roleplayVersion) {
		this.roleplayVersion = roleplayVersion;
	}

	public String getRoleplayOrganization() {
		return roleplayOrganization;
	}

	public void setRoleplayOrganization(String roleplayOrganization) {
		this.roleplayOrganization = roleplayOrganization;
	}

	public Long getActorId() {
		return actorId;
	}

	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}

	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}
	
	public String getPhaseName() {
		return phaseName;
	}

	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}

	public Long getPluginIndex() {
		return pluginIndex;
	}

	public void setPluginIndex(Long pluginIndex) {
		this.pluginIndex = pluginIndex;
	}

	public Long getRolePlayInMotionId() {
		return rolePlayInMotionId;
	}

	public void setRolePlayInMotionId(Long rolePlayInMotionId) {
		this.rolePlayInMotionId = rolePlayInMotionId;
	}

	public String getRoleplayInMotionName() {
		return roleplayInMotionName;
	}

	public void setRoleplayInMotionName(String rolePlayInMotionName) {
		this.roleplayInMotionName = rolePlayInMotionName;
	}

	/**
	 * Loads info save about the user, such as the id of the last roleplay that
	 * they worked on, into the session.
	 * 
	 * @param username
	 */
	public void loadLoginInfo(String username) {
		Person person = new Person().getByUsername(username);

		Long roleplayBeingEdited = person.getLastRolePlayEdited();

		if (roleplayBeingEdited != null) {
			this.setRoleplayId(roleplayBeingEdited);

			Roleplay roleplay = new Roleplay().getById(roleplayBeingEdited);
			this.setRoleplayName(roleplay.getRoleplayName());

		}
		
		allSessions.put(username, this);

	}

	public String getPlatformZone() {
		return platformZone;
	}

	public void setPlatformZone(String platformZone) {
		this.platformZone = platformZone;
	}
	
	// this old Hashtable class is a candidate for updating. MJS 3.23
	static Hashtable actorsNames = new Hashtable();
	
	public String getActorName(){
		
		String actorName = (String) actorsNames.get(this.actorId);
		
		if (actorName == null) {
			Actor actor = new Actor().getById(this.actorId);
			actorName = actor.getActorName();
			actorsNames.put(actorId, actorName);
		}
		
		return actorName;
		
	}

	public Long getPluginId() {
		return pluginId;
	}

	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
	}

	
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
