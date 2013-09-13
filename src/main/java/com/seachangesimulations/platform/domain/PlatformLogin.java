
package com.seachangesimulations.platform.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Version;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.PlatformLoginDao;
import com.seachangesimulations.platform.service.TimeStamped;

@Entity
@Component
@Scope("prototype")
public class PlatformLogin extends BaseSCPlatformObject {

	public PlatformLogin() {

	}

	public static final int LOGOUT_USERLOGOUT = 1;
	public static final int LOGOUT_TIMEDOUT = 2;
	public static final int LOGOUT_LOGGEDIN = 3;

	private Long personId;

	/** Indicates how the user was logged out. */
	private Long logoutType;

	private String notes;

	public Long getLogoutType() {
		return logoutType;
	}

	public void setLogoutType(Long logoutType) {
		this.logoutType = logoutType;
	}

	public void setLogoutType(int logoutType) {
		this.logoutType = new Long(logoutType);
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long Login(Long idOfPersonLoggingIn) {

		PlatformLoginDao dao = (PlatformLoginDao) getApplicationContext().getBean("platformLoginDao",
				PlatformLoginDao.class);

		PlatformLogin platformLogin = new PlatformLogin();
		platformLogin.setPersonId(idOfPersonLoggingIn);
		dao.save(platformLogin);

		return platformLogin.getId();
	}

	public void logut(Long id, int logoutType) {

		PlatformLoginDao dao = (PlatformLoginDao) getApplicationContext().getBean("platformLoginDao",
				PlatformLoginDao.class);

		PlatformLogin platformLogin = dao.get(id);
		platformLogin.setLogoutType(logoutType);
		dao.save(platformLogin);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PlatformLogin getById(Long id){
		PlatformLoginDao dao = (PlatformLoginDao) getApplicationContext().getBean("platformLoginDao", PlatformLoginDao.class);
		return dao.get(id);
	}

}
