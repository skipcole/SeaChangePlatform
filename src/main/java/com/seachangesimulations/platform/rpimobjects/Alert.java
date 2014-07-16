package com.seachangesimulations.platform.rpimobjects;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.AlertDao;
import com.seachangesimulations.platform.domain.BaseSCPlatformObject;

@Entity
@Component
@Scope("prototype")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Alert extends BaseSCPlatformObject{
	
	public enum AlertTypes {
	    Error, Warning, Info, PhaseChange
	}
	
	private AlertTypes myAlertType = AlertTypes.Info;
	
	private String alertText = "";

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

	public AlertTypes getMyAlertType() {
		return myAlertType;
	}

	public void setMyAlertType(AlertTypes myAlertType) {
		this.myAlertType = myAlertType;
	}

	


}
