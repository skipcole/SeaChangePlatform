package com.seachangesimulations.platform.mvc.formbeans.admin;


/**
 * A utility object to hold all the various pieces of data used when installing
 * a platform.  This is for both a new administrator account and a new organization.
 * 
 * 
 */
public class AdminInstallationFormBean {

	private String installationKey;

	private String firstName;

	private String middleName;  // unused as of 3.18

	private String lastName;

	private String username;

	private String password;

	private String organizationName;

	public String getInstallationKey() {
		return installationKey;
	}

	public void setInstallationKey(String installationKey) {
		this.installationKey = installationKey;
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

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

}
