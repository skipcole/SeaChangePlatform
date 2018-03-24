
package com.seachangesimulations.platform.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.PersonDao;
import com.seachangesimulations.platform.domain.assignment.PersonOrganizationAssignment;
import com.seachangesimulations.platform.rpimobjects.Message;
import com.seachangesimulations.platform.rpimobjects.AlertJSON;
import com.seachangesimulations.platform.service.SessionInfoBean;

@Entity
@Component
@Scope("prototype") 
public class Person extends BaseSCPlatformObject implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Assumes all of the data has been filled in and creates the person.
	 * 
	 * @return
	 */
	public Long create(){
		return create(this.username, this.password, this.initialAuthLevel);
	}
	/**
	 * Assumes that the password has not been encrypted.
	 * 
	 * @param username
	 * @param password
	 * @param initialAuthLevel
	 * @return
	 */
	public Long create(String username, String password, int initialAuthLevel) {

		Long newPersonId = null;
		System.out.println("MJS - domain/person.java Creating New Person with parameters.");
		JFrame frame = new JFrame("person.create(3 params)");
		String msg = "Beginning Person.create(params) in platform.domain.Person.java";
		// JOptionPane.showMessageDialog(frame,  msg + " Username param = " + username);
		frame.dispose();
		
		
		/* Encrypt password. Stopped MJS 3.9.18 */
		PasswordEncoder pp = new Md5PasswordEncoder();
		password = pp.encodePassword(password, null);
		
		PersonDao dao = (PersonDao) getApplicationContext().getBean("personDao", PersonDao.class);
		// call 4 parameter version of Person.create - 3rd parameter is organizationID
		newPersonId = create(username, password, new Long(1), initialAuthLevel);
		
		PersonOrganizationAssignment poa = new PersonOrganizationAssignment();
		// really doubt this should always be a 1! MJS 3.18
		poa.create(newPersonId, new Long(1), initialAuthLevel);
		
		return newPersonId;
	}
	
	/**
	 * Creates a person with the attributes passed in.
	 * 
	 * @param username
	 * @param password
	 * @param orgId
	 * @param initialAuthLevel
	 * @return
	 */
	public Long create(String username, String password, Long orgId, int initialAuthLevel) {

		System.out.println("MJS - domain/person.java Creating New Person with 4 parameters.");
		JFrame frame = new JFrame("person.create(4 params)");
		String msg = "Beginning Person.create(4 params) in platform.domain.Person.java ... calling person.save()";
		// JOptionPane.showMessageDialog(frame,  msg + " Username param = " + username);
		frame.dispose();
		
		Person p = new Person();
		p.setUsername(username);
		p.setPassword(password);
		p.setInitialOrgId(orgId);
		p.setInitialAuthLevel(initialAuthLevel);
		p.save();
		return p.getId();

	}

	
	public Person getByUsername(String userName){
		
		PersonDao personDao = (PersonDao) getApplicationContext().getBean("personDao", PersonDao.class);
		return personDao.getByUsername(userName);
	}
	
	public List<Object[]> searchFor(Map<String, String> params) {
		// since getApplicationContext is non-static, method must be as well.
		PersonDao personDao = (PersonDao) getApplicationContext().getBean("personDao", PersonDao.class);
		return personDao.searchFor(params);
	}  // end searchFor
	
	public List<String> getDBColumnNames() {
		// since getApplicationContext is non-static, method must be as well.
		PersonDao personDao = (PersonDao) getApplicationContext().getBean("personDao", PersonDao.class);
		return personDao.getDBColumnNames( );
	}  // end getDBColumnNames
	
	public List<Person> searchByName(String name){
		// since getApplicationContext is non-static, method must be as well.
		PersonDao personDao = (PersonDao) getApplicationContext().getBean("personDao", PersonDao.class);
		return personDao.searchByName(name);
	}  // end searchByName

	public Person() {
	}

	/**
	 * Saves this person to the database.
	 * 
	 */
	public void save() {
		System.out.println("MJS - platform/domain/person.java.  Save() called..");
		JFrame frame = new JFrame("person.save ");
		String msg = "Beginning Person.save() in platform.domain.Person.java";
		// JOptionPane.showMessageDialog(frame,  msg + " .... calling getBean (PersonDAO)");
		PersonDao personDao = (PersonDao) getApplicationContext().getBean("personDao", PersonDao.class);
		frame.dispose();
		personDao.save(this);
	}

	private Long lastRolePlayEdited;
	private Long lastRolePlayInMotionFacilitated;
	private Long lastRolePlayInMotionPlayed;
	
	private Long lastPluginEdited;

	private String firstName;
	private String middleName;
	private String lastName;
	
	@Transient
	private String fullName = "Max";

	@Column(unique = true)
	private String username;

	private String password;
	private Integer roleLevel;
	
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    

    private int initialAuthLevel;
    

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}

	public Long getLastRolePlayEdited() {
		return lastRolePlayEdited;
	}

	public void setLastRolePlayEdited(Long lastRolePlayEdited) {
		this.lastRolePlayEdited = lastRolePlayEdited;
	}

	public Long getLastRolePlayInMotionFacilitated() {
		return lastRolePlayInMotionFacilitated;
	}

	public void setLastRolePlayInMotionFacilitated(Long lastRolePlayInMotionFacilitated) {
		this.lastRolePlayInMotionFacilitated = lastRolePlayInMotionFacilitated;
	}

	public Long getLastRolePlayInMotionPlayed() {
		return lastRolePlayInMotionPlayed;
	}

	public void setLastRolePlayInMotionPlayed(Long lastRolePlayInMotionPlayed) {
		this.lastRolePlayInMotionPlayed = lastRolePlayInMotionPlayed;
	}

	public Long getLastPluginEdited() {
		return lastPluginEdited;
	}
	public void setLastPluginEdited(Long lastPluginEdited) {
		this.lastPluginEdited = lastPluginEdited;
	}
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getInitialAuthLevel() {
		return initialAuthLevel;
	}


	public void setInitialAuthLevel(int initialAuthLevel) {
		this.initialAuthLevel = initialAuthLevel;
	}

	/**
	 * Sets last authored simulation to this one, so if user logs off then on, it will be remembered.
	 * 
	 * @param username
	 * @param roleplayId
	 */
	public static void saveLastRolePlayEdited(String username, Long roleplayId) {
				Person person = new Person().getByUsername(username);
				person.setLastRolePlayEdited(roleplayId);
				person.save();
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getFullName() {
		return StringUtils.defaultString(firstName) + " " + StringUtils.defaultString(lastName);
	}
	
	@Transient
	private Collection authorities;
	
	
	public void setAuthorities(Collection authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Person getById(Long id){
		PersonDao dao = (PersonDao) getApplicationContext().getBean("personDao", PersonDao.class);
		return dao.get(id);
	}
	
	public static AlertJSON getNextAlert(SessionInfoBean sessionInfoBean,
			Long lastAlertIGot) {
		
		List <Message> myAlerts = 
				new Message().getPlayersRoleMessages(sessionInfoBean.getActorId(), 
						sessionInfoBean.getRolePlayInMotionId(), lastAlertIGot);
		
		
		AlertJSON alertJSON = new AlertJSON();
		alertJSON.setAlertId(new Long(2));
		alertJSON.setAlertText("boom");
		alertJSON.setAlertType(Message.MESSAGE_TYPE_PHASE_CHANGE);
		
		return alertJSON;
		
	}

	
}
