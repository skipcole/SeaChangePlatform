package com.seachangesimulations.platform.rpimobjects;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.EventDao;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;

@Entity
@Component
@Scope("prototype")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event extends BaseSCPlatformObject {

	public static final String ET_PHASE_CHANGE = "phase_change";
	
	public static final String ET_PHASE_CHANGE_NAME = "Phase Change";

	private String eventType = "";
	
	private String eventName = "";
	
	@Lob
	private String eventDescription = "";
	
	private String oldData = "";
	
	private String newData = "";
	
	private Long roleplayId = new Long(0);
	
	private Long rpimId = new Long(0);
	
	private Long initiatingActorId = new Long(0);
	
	private Long initiatingUserId = new Long(0);
	
	private Date timeOfEvent = new Date();
	
	@Override
	public Event getById(Long id) {
		EventDao dao = (EventDao) getApplicationContext().getBean("eventDao", EventDao.class);
		return dao.get(id);
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getOldData() {
		return oldData;
	}

	public void setOldData(String oldData) {
		this.oldData = oldData;
	}

	public String getNewData() {
		return newData;
	}

	public void setNewData(String newData) {
		this.newData = newData;
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

	public Long getInitiatingActorId() {
		return initiatingActorId;
	}

	public void setInitiatingActorId(Long initiatingActorId) {
		this.initiatingActorId = initiatingActorId;
	}

	public Long getInitiatingUserId() {
		return initiatingUserId;
	}

	public void setInitiatingUserId(Long initiatingUserId) {
		this.initiatingUserId = initiatingUserId;
	}

	public Date getTimeOfEvent() {
		return timeOfEvent;
	}

	public void setTimeOfEvent(Date timeOfEvent) {
		this.timeOfEvent = timeOfEvent;
	}

	public void save() {
		EventDao dao = (EventDao) getApplicationContext().getBean("eventDao", EventDao.class);
		dao.save(this);
	}
	
	
		
	
	

}
