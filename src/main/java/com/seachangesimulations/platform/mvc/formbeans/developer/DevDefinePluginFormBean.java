package com.seachangesimulations.platform.mvc.formbeans.developer;

import org.springframework.beans.BeanUtils;

import com.seachangesimulations.platform.domain.Plugin;

public class DevDefinePluginFormBean {

	private Long id;
	
	private boolean customized;
	
	private String author;
	
	private String authorURI;
	
	private String description;
	
	private String language;
	
	private String license;
	
	private String pluginName;
	
	private String pluginURI;
	
	private String pluginVersion;
	
	private String starterCustomizationFile;
	
	private String starterFile;
	
	private String pluginReleaseMajorNumber = "0";
	
	private String pluginReleaseMinorNumber = "0";
	
	private String pluginReleaseMicroNumber = "0";
	
	private String pluginReleaseLetters = "";
	
	private String shortFormOrgName = "SeaChangeSimulations";
	
	private String shortFormPluginName = "";
	

	public DevDefinePluginFormBean() {

	}
	
	/**
	 * Copy in parameters (avoiding need for InitBinding or white/black listing)
	 * 
	 * @param plugin
	 */
	public DevDefinePluginFormBean(Plugin plugin) {
		
		// Plugin is source, this is target.
		BeanUtils.copyProperties(plugin, this);
		
	}

	public boolean isCustomized() {
		return customized;
	}

	public void setCustomized(boolean customized) {
		this.customized = customized;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorURI() {
		return authorURI;
	}

	public void setAuthorURI(String authorURI) {
		this.authorURI = authorURI;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public String getPluginURI() {
		return pluginURI;
	}

	public void setPluginURI(String pluginURI) {
		this.pluginURI = pluginURI;
	}

	public String getPluginVersion() {
		return pluginVersion;
	}

	public void setPluginVersion(String pluginVersion) {
		this.pluginVersion = pluginVersion;
	}

	public String getStarterCustomizationFile() {
		return starterCustomizationFile;
	}

	public void setStarterCustomizationFile(String starterCustomizationFile) {
		this.starterCustomizationFile = starterCustomizationFile;
	}

	public String getStarterFile() {
		return starterFile;
	}

	public void setStarterFile(String starterFile) {
		this.starterFile = starterFile;
	}
	
	

	public String getPluginReleaseMajorNumber() {
		return pluginReleaseMajorNumber;
	}

	public void setPluginReleaseMajorNumber(String pluginReleaseMajorNumber) {
		this.pluginReleaseMajorNumber = pluginReleaseMajorNumber;
	}

	public String getPluginReleaseMinorNumber() {
		return pluginReleaseMinorNumber;
	}

	public void setPluginReleaseMinorNumber(String pluginReleaseMinorNumber) {
		this.pluginReleaseMinorNumber = pluginReleaseMinorNumber;
	}

	public String getPluginReleaseMicroNumber() {
		return pluginReleaseMicroNumber;
	}

	public void setPluginReleaseMicroNumber(String pluginReleaseMicroNumber) {
		this.pluginReleaseMicroNumber = pluginReleaseMicroNumber;
	}

	public String getPluginReleaseLetters() {
		return pluginReleaseLetters;
	}

	public void setPluginReleaseLetters(String pluginReleaseLetters) {
		this.pluginReleaseLetters = pluginReleaseLetters;
	}

	public String getShortFormOrgName() {
		return shortFormOrgName;
	}

	public void setShortFormOrgName(String shortFormOrgName) {
		this.shortFormOrgName = shortFormOrgName;
	}

	public String getShortFormPluginName() {
		return shortFormPluginName;
	}

	public void setShortFormPluginName(String shortFormPluginName) {
		this.shortFormPluginName = shortFormPluginName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns a plugin with value filled from this bean.
	 * 
	 * @return
	 */
	public Plugin load() {
		Plugin plugin = new Plugin();
		
		BeanUtils.copyProperties(this, plugin);
		
		return plugin;
	}
	
	
}
