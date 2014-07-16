
package com.seachangesimulations.platform.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.RoleplayDao;
import com.seachangesimulations.platform.pluginobjects.PluginObjectAssociation;
import com.seachangesimulations.platform.service.SessionInfoBean;

@Entity
@Component
@Scope("prototype")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Roleplay extends BaseSCPlatformObject implements MayHaveSubObjects{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int EDIT_PERMISSION_SPECIFIC_USERS = 0;
	public static final int EDIT_PERMISSION_ALL_AUTHORS = 1;

	public Roleplay() {

	}

	public Roleplay getById(Long id){
		RoleplayDao dao = (RoleplayDao) getApplicationContext().getBean("roleplayDao", RoleplayDao.class);
		return dao.get(id);
	}
	
	public void save(){
		RoleplayDao dao = (RoleplayDao) getApplicationContext().getBean("roleplayDao", RoleplayDao.class);
		dao.save(this);
	}
	
	/**
	 * Saves this roleplay and creates the initial objects that come with it.
	 */
	public void createWithStarterObjects(SessionInfoBean sessionInfoBean){
		this.save();
		Phase initialPhase = new Phase();
		initialPhase.setRoleplayId(this.id);
		initialPhase.setFirstPhase(true);
		initialPhase.setPhaseName("First Phase");
		initialPhase.setPhaseOrder(1);
		initialPhase.save();
		sessionInfoBean.setPhaseId(initialPhase.getId());
		
	}
	
	/**
	 * Saves copy of object associations.
	 */
	public void createCopyofObjectsAndAssociationsForRpim(Long rpimId){
		
		// Get all plugin object associations for this roleplay
		List <PluginObjectAssociation> poas = new PluginObjectAssociation().getAllForRoleplay(this.getId());
		
		// Make copies of them for this roleplay in motion.
		for (PluginObjectAssociation poa : poas) {
			PluginObjectAssociation newPoa = new PluginObjectAssociation();
			
			BeanUtils.copyProperties(poa, newPoa);
			newPoa.setId(null);
			newPoa.setVersion(null);
			newPoa.setRpimId(rpimId);
			newPoa.setAssociationType(newPoa.RPIM_PLUGIN_ASSOCIATION);
			
			// The original object id pointer will point back original object.
			// If we have to make a copy for this roleplay, do so and point the association to i.
			if (false){
				// Create new objects based on old objects
				//TODO makes it real
				newPoa.setObjectId(null);
			} 
			newPoa.save();
		}
		
	}

	public Long create(String rolePlayName, Long orgId) {

		RoleplayDao dao = (RoleplayDao) getApplicationContext().getBean("roleplayDao", RoleplayDao.class);

		return dao.create(rolePlayName, orgId);
	}
	
	
	public List <Roleplay> getAll(){
		RoleplayDao dao = (RoleplayDao) getApplicationContext().getBean("roleplayDao", RoleplayDao.class);
		
		return dao.getAll();
		
	}

	/** Indicates the role play can be edited only by specific users. */
	public static final int CAN_BE_EDITED_BY_SPECIFIC_USERS = 0;

	/** Indicates the role play can be edited by everyone author. */
	public static final int CAN_BE_EDITED_BY_EVERYONE = 1;

	/** Indicate that the role play is locked on this platform. */
	public static final int CAN_BE_EDITED_BY_NO_ONE = -1;

	public static final int NOT_PUBLISHED = 0;
	public static final int READY_FOR_TESTING = 1;
	public static final int PUBLISHED = 2;

	/** AAR starter text for this role play. */
	@Lob
	private String aarStarterText = "";

	/** Indicates if Players can register themselves to play in the role play. */
	private boolean allowPlayerAutoreg = false;

	/** Planned audience of this Role Play. */
	@Lob
	private String audience = "";

	/** A paragraph introducing what this role play is all about. */
	@Lob
	private String blurb = "";

	/** Copyright information to be shown on every page footer. */
	private String copyrightString = "";

	/** Creating Organization of this role play. */
	private String creationOrg = "";

	/** Author of this role play. */
	private String creator = "";

	/** Hidden Objectives of this role play. */
	@Lob
	private String hiddenLearningObjectives = "";

	/** Introduction to this role play. */
	@Lob
	private String introduction = "";

	/** Flag to let web visitors know it can be used. */
	private boolean isExternallyPublished = false;

	/** Flag to let instructors know it can be used. */
	private boolean isInternallyPublished = false;

	/** Learning Objectives of this role play. */
	@Lob
	private String objectives = "";

	@Lob
	private String listingKeyWords = "";

	/** Name of this role play. */
	@Column(unique = true)
	private String roleplayName = "";

	/** Thoughts on how this role play should be conducted. */
	@Lob
	private String plannedPlayIdeas = "";

	/** Date role play was externally published. */
	private Date madeTestableDate;

	/** Date role play was internally published. */
	private Date publishedDate;

	private int publishedState = NOT_PUBLISHED;

	private int rolePlayEditingRestrictions = CAN_BE_EDITED_BY_SPECIFIC_USERS;

	/** Version of this role play. */
	private String rolePlayVersion = "0.0.1 - RAW";

	/** Version of the software this role play was made with. */
	private String softwareVersion = "";

	private boolean timed = false;

	public String getAarStarterText() {
		return aarStarterText;
	}

	public void setAarStarterText(String aarStarterText) {
		this.aarStarterText = aarStarterText;
	}

	public boolean isAllowPlayerAutoreg() {
		return allowPlayerAutoreg;
	}

	public void setAllowPlayerAutoreg(boolean allowPlayerAutoreg) {
		this.allowPlayerAutoreg = allowPlayerAutoreg;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public String getCopyrightString() {
		return copyrightString;
	}

	public void setCopyrightString(String copyrightString) {
		this.copyrightString = copyrightString;
	}

	public String getCreationOrg() {
		return creationOrg;
	}

	public void setCreationOrg(String creationOrg) {
		this.creationOrg = creationOrg;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getHiddenLearningObjectives() {
		return hiddenLearningObjectives;
	}

	public void setHiddenLearningObjectives(String hiddenLearningObjectives) {
		this.hiddenLearningObjectives = hiddenLearningObjectives;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public boolean isExternallyPublished() {
		return isExternallyPublished;
	}

	public void setExternallyPublished(boolean isExternallyPublished) {
		this.isExternallyPublished = isExternallyPublished;
	}

	public boolean isInternallyPublished() {
		return isInternallyPublished;
	}

	public void setInternallyPublished(boolean isInternallyPublished) {
		this.isInternallyPublished = isInternallyPublished;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getListingKeyWords() {
		return listingKeyWords;
	}

	public void setListingKeyWords(String listingKeyWords) {
		this.listingKeyWords = listingKeyWords;
	}

	public String getRoleplayName() {
		return roleplayName;
	}

	public void setRoleplayName(String name) {
		this.roleplayName = name;
	}

	public String getPlannedPlayIdeas() {
		return plannedPlayIdeas;
	}

	public void setPlannedPlayIdeas(String plannedPlayIdeas) {
		this.plannedPlayIdeas = plannedPlayIdeas;
	}

	public Date getMadeTestableDate() {
		return madeTestableDate;
	}

	public void setMadeTestableDate(Date madeTestableDate) {
		this.madeTestableDate = madeTestableDate;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public int getPublishedState() {
		return publishedState;
	}

	public void setPublishedState(int publishedState) {
		this.publishedState = publishedState;
	}

	public int getRolePlayEditingRestrictions() {
		return rolePlayEditingRestrictions;
	}

	public void setRolePlayEditingRestrictions(int rolePlayEditingRestrictions) {
		this.rolePlayEditingRestrictions = rolePlayEditingRestrictions;
	}

	public String getRolePlayVersion() {
		return rolePlayVersion;
	}

	public void setRolePlayVersion(String rolePlayVersion) {
		this.rolePlayVersion = rolePlayVersion;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public boolean isTimed() {
		return timed;
	}

	public void setTimed(boolean timed) {
		this.timed = timed;
	}
	
	/* Added the code below to try to get around needed to pull rolepay back out of the database
	 * before saving. It didn't help. */
	@Override
	public boolean equals (Object obj){
		
		Roleplay rp = (Roleplay) obj;
		
		if ((rp == null) || (rp.getId() == null)) {
			return false;
		}
		
		if (rp.getId().intValue() == this.getId().intValue()){
			return true;
		} else {
			return false;
		}
	}
	
	@Transient
	private List<Phase> phases = new ArrayList<Phase>();

	@Override
	public void loadSubObjects() {

		phases = new Phase().getAllForRoleplay(this.id);
		
	}

	@Override
	public void saveSubObjects() {
		// TODO Auto-generated method stub
		
	}

	

}
