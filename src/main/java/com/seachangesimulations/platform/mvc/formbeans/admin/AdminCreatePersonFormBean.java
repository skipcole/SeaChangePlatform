package com.seachangesimulations.platform.mvc.formbeans.admin;


/**
 * Binds to the form used by the system adminstrator to create users. 
 * Values entered here are copied into the Person table. 
 * (This is for security purposes, since the other way of preventing possible
 * malicious activity is to use InitBinding.)
 * 
 */
public class AdminCreatePersonFormBean {

	private String username;
	
	private String password;
	
	private String firstName;
	
	private String middleName;  // currently unused - not on the form. MJS 3.18
	
	private String lastName;
	
	private int initialAuthLevel;

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

	public int getInitialAuthLevel() {
		return initialAuthLevel;
	}

	public void setInitialAuthLevel(int initialAuthLevel) {
		this.initialAuthLevel = initialAuthLevel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	
}
