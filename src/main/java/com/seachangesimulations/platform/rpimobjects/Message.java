package com.seachangesimulations.platform.rpimobjects;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.MessageDao;
import com.seachangesimulations.platform.dao.EventDao;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;
import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;

/**
 * A message sent via the messaging system to a player or players.
 *
 */
@Entity
@Component
@Scope("prototype")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Message extends BaseSCPlatformObject {

	public static final String MESSAGE_TYPE_ERROR = "message_type_error";
	public static final String MESSAGE_TYPE_WARRNING = "message_type_warning";
	public static final String MESSAGE_TYPE_INFO = "message_type_info";
	public static final String MESSAGE_TYPE_PHASE_CHANGE = "message_type_phase_change";

	@Lob
	private String messageText = ""; //$NON-NLS-1$

	@Lob
	private String messagePopupMessage = ""; //$NON-NLS-1$

	@Lob
	private String messageEmailText = ""; //$NON-NLS-1$

	private String messageType = "";

	private Long roleplayId;

	private Long rpimId;

	/** Indicates is this message is for specific Actors */
	private boolean hasSpecificActorTargets = false;

	/** Indicates is this message is for specific Actors */
	private boolean hasSpecificPersonTargets = false;

	/* a comma separated list of the actor ids for whom this message is for. */
	private String specificActorTargetIds = ""; //$NON-NLS-1$

	/* a comma separated list of the actor ids for whom this message is for. */
	private String specificPersonTargetIds = ""; //$NON-NLS-1$

	@Override
	public Message getById(Long id) {
		MessageDao dao = (MessageDao) getApplicationContext().getBean(
				"messageDao", MessageDao.class);
		return dao.get(id);
	}

	public Long getRoleplayId() {
		return roleplayId;
	}

	public void setRoleplayId(Long roleplayId) {
		this.roleplayId = roleplayId;
	}

	public Long getRpimId() {
		return rpimId;
	}

	public void setRpimId(Long rpimId) {
		this.rpimId = rpimId;
	}


	public void save() {
		MessageDao dao = (MessageDao) getApplicationContext().getBean(
				"messageDao", MessageDao.class);
		dao.save(this);
	}
	
	
	public List<Message> getPlayersPersonalMessages(Long personId, Long actorId,
			Long rpimId, Long lastMessageReceived) {

		MessageDao dao = (MessageDao) getApplicationContext().getBean(
				"messageDao", MessageDao.class);

		return dao.getPlayersPersonalMessages(personId, rpimId,
				lastMessageReceived);
	}
	
	public List<Message> getPlayersRoleMessages(Long actorId,
			Long rpimId, Long lastMessageReceived) {

		MessageDao dao = (MessageDao) getApplicationContext().getBean(
				"messageDao", MessageDao.class);

		return dao.getPlayersPersonalMessages(actorId, rpimId,
				lastMessageReceived);
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getMessagePopupMessage() {
		return messagePopupMessage;
	}

	public void setMessagePopupMessage(String messagePopupMessage) {
		this.messagePopupMessage = messagePopupMessage;
	}

	public String getMessageEmailText() {
		return messageEmailText;
	}

	public void setMessageEmailText(String messageEmailText) {
		this.messageEmailText = messageEmailText;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public boolean isHasSpecificActorTargets() {
		return hasSpecificActorTargets;
	}

	public void setHasSpecificActorTargets(boolean hasSpecificActorTargets) {
		this.hasSpecificActorTargets = hasSpecificActorTargets;
	}

	public boolean isHasSpecificPersonTargets() {
		return hasSpecificPersonTargets;
	}

	public void setHasSpecificPersonTargets(boolean hasSpecificPersonTargets) {
		this.hasSpecificPersonTargets = hasSpecificPersonTargets;
	}

	public String getSpecificActorTargetIds() {
		return specificActorTargetIds;
	}

	public void setSpecificActorTargetIds(String specificActorTargetIds) {
		this.specificActorTargetIds = specificActorTargetIds;
	}

	public String getSpecificPersonTargetIds() {
		return specificPersonTargetIds;
	}

	public void setSpecificPersonTargetIds(String specificPersonTargetIds) {
		this.specificPersonTargetIds = specificPersonTargetIds;
	}



}
