package com.seachangesimulations.platform.rpimobjects;

import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.AlertDao;
import com.seachangesimulations.platform.dao.EventDao;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;
import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;

@Entity
@Component
@Scope("prototype")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Alert extends BaseSCPlatformObject{
	
	
	public static String ALERT_TYPE_ERROR = "alert_type_error";
	public static String ALERT_TYPE_WARRNING = "alert_type_warning";
	public static String ALERT_TYPE_INFO = "alert_type_info";
	public static String ALERT_TYPE_PHASE_CHANGE = "alert_type_phase_change";
	
	private String alertType = "";
	
	private String alertText = "";
	
	private Long roleplayId;
	
	private Long rpimId;
	
	private Long targetActorId;
	
	private Long targetPersonId;
	
	

	@Override
	public Alert getById(Long id) {
		AlertDao dao = (AlertDao) getApplicationContext().getBean("alertDao", AlertDao.class);
		return dao.get(id);
	}

	public String getAlertText() {
		return alertText;
	}

	public void setAlertText(String alertText) {
		this.alertText = alertText;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
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

	public Long getTargetActorId() {
		return targetActorId;
	}

	public void setTargetActorId(Long targetActorId) {
		this.targetActorId = targetActorId;
	}

	public Long getTargetPersonId() {
		return targetPersonId;
	}

	public void setTargetPersonId(Long targetPersonId) {
		this.targetPersonId = targetPersonId;
	}

	/**
	 * Creates the alerts to notify players of phase change.
	 * 
	 * @param event
	 */
	public static void createAlertsForPhaseChange(Event event) {

		List <PersonRoleplayAssignment> allPeopleInThisRoleplay = new PersonRoleplayAssignment().getAllPlayers(event.getRpimId());
		
		for (PersonRoleplayAssignment thisPra: allPeopleInThisRoleplay){
			
			Alert alert = new Alert();
			alert.setAlertType(Alert.ALERT_TYPE_PHASE_CHANGE);
			alert.setAlertText(event.getEventDescription());
			alert.setRoleplayId(event.getRoleplayId());
			alert.setRpimId(event.getRpimId());
			alert.setTargetActorId(thisPra.getActorId());
			alert.setTargetPersonId(thisPra.getPersonId());
			
			alert.save();
			
		}
		
	}

	public void save() {
		AlertDao dao = (AlertDao) getApplicationContext().getBean("alertDao", AlertDao.class);
		dao.save(this);
	}


}
