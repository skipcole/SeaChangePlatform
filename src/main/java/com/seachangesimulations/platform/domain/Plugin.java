package com.seachangesimulations.platform.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.seachangesimulations.platform.dao.PluginDao;
import com.seachangesimulations.platform.pluginobjects.BasePluginObject;
import com.seachangesimulations.platform.pluginobjects.PluginFileDescriptor;
import com.seachangesimulations.platform.pluginobjects.PluginObjectAssociation;
import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;
import com.seachangesimulations.platform.utilities.PlatformProperties;

@Entity
@Component
@Scope("prototype")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Plugin extends BaseSCPlatformObject implements MayHaveSubObjects{

	private static final String BASE_PLUGIN_DIRECTORY = "/resources/plugins/";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Plugin() {

	}

	public Plugin getById(Long id) {
		PluginDao dao = (PluginDao) getApplicationContext().getBean("pluginDao", PluginDao.class);
		return dao.get(id);
	}

	public List<Plugin> getAllUncustomized() {
		PluginDao dao = (PluginDao) getApplicationContext().getBean("pluginDao", PluginDao.class);

		return dao.getAllUncustomized();
	}

	private boolean customized;

	private String author;

	private String authorURI;

	private String description;

	private String language;

	private String license;

	private String pluginName;

	private String pluginURI;

	private String pluginReleaseMajorNumber;

	private String pluginReleaseMinorNumber;

	private String pluginReleaseMicroNumber;

	private String pluginReleaseLetters;

	private String shortFormOrgName;

	private String shortFormPluginName;

	private String pluginDirectory;
	
	/** The origin where the plugin began in if it was copied over during an auto-load of plugins. */
	private String pluginOriginDirectory = "";

	private boolean isSystemPlugin;
	
	

	/**
	 * Creates the plugin paths for the links on the tabs to show each plugin.
	 * 
	 * @return
	 */
	public String generatePluginPath() {
		return BASE_PLUGIN_DIRECTORY + pluginDirectory + "/";
	}
	
	/**
	 * Creates the plugin paths for the links on the tabs to show each plugin.
	 * 
	 * @return
	 */
	public String generateLinkToPlugin() {
		return BASE_PLUGIN_DIRECTORY + pluginDirectory + "/" + shortFormPluginName + ".htm";
	}

	/**
	 * Constructs the directory to hold the plugin files based on the plugins
	 * name, who constructed it (to disambiguate plugins of the same name by
	 * different creators) and release.
	 * 
	 * @return
	 */
	public String generatePluginDirectory() {
		String pd = shortFormPluginName + "_" + shortFormOrgName;

		if (StringUtils.hasText(pluginReleaseMajorNumber)) {
			pd += "_" + pluginReleaseMajorNumber;
		}
		if (StringUtils.hasText(pluginReleaseMinorNumber)) {
			pd += "_" + pluginReleaseMinorNumber;
		}
		if (StringUtils.hasText(pluginReleaseMicroNumber)) {
			pd += "_" + pluginReleaseMicroNumber;
		}
		if (StringUtils.hasText(pluginReleaseLetters)) {
			pd += "_" + pluginReleaseLetters;
		}
		return pd;

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

	public String getPluginDirectory() {
		return pluginDirectory;
	}

	public void setPluginDirectory(String pluginDirectory) {
		this.pluginDirectory = pluginDirectory;
	}

	public boolean isSystemPlugin() {
		return isSystemPlugin;
	}

	public void setSystemPlugin(boolean isSystemPlugin) {
		this.isSystemPlugin = isSystemPlugin;
	}

	public void save() {

		if (!this.isSystemPlugin) {
			pluginDirectory = this.generatePluginDirectory();
		}

		PluginDao dao = (PluginDao) getApplicationContext().getBean("pluginDao", PluginDao.class);
		dao.save(this);
	}

	/**
	 * Returns a copy of the plugin passed in, and all objects associated with
	 * it.
	 * 
	 * @param rawPluginId
	 * @return
	 */
	public static Plugin getPluginCopyForRoleplay(Long rpId, Long originalPluginId) {

		Plugin originalPlugin = new Plugin().getById(originalPluginId);

		Plugin newPlugin = new Plugin();

		BeanUtils.copyProperties(originalPlugin, newPlugin);

		newPlugin.setId(null);
		newPlugin.setVersion(null);
		newPlugin.setCustomized(true);
		newPlugin.save();
		System.out.println("id is " + originalPlugin.getId());

		// Get base list of objects associated with this plugin.
		List<PluginObjectAssociation> poas = new PluginObjectAssociation().getAllForPlugin(originalPlugin.getId(),
				null, null);

		for (PluginObjectAssociation poa : poas) {
			System.out.println(poa.getObjectType());

			BasePluginObject bpo = null;
			String newObjectType = null;

			if (PluginObjectDocument.class.getCanonicalName().equalsIgnoreCase(poa.getObjectType())) {

				bpo = getDocumentCopy(poa.getObjectId(), rpId);
				newObjectType = PluginObjectDocument.class.getCanonicalName();

			}

			/* Create and save the new association */
			PluginObjectAssociation newPoa = new PluginObjectAssociation();
			newPoa.setPluginId(newPlugin.getId());
			newPoa.setRpId(rpId);
			newPoa.setObjectIndex(poa.getObjectIndex());
			newPoa.setObjectName(poa.getObjectName());
			newPoa.setObjectId(bpo.getId());
			newPoa.setObjectType(newObjectType);
			newPoa.setAssociationType(PluginObjectAssociation.RP_PLUGIN_ASSOCIATION);
			
			newPoa.save();
		}

		return newPlugin;

	}
	
	private static BasePluginObject getDocumentCopy(Long podId, Long rpId){

		PluginObjectDocument pod = new PluginObjectDocument().getById(podId);

		PluginObjectDocument newPod = new PluginObjectDocument();
		
		
		try {
			BeanUtils.copyProperties(pod, newPod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		newPod.setRoleplayId(rpId);
		newPod.setId(null);
		newPod.setVersion(null);
		newPod.save();
		
		return newPod;
	}

	@Override
	public void loadSubObjects() {
		// TODO Auto-generated method stub
		
		// Get all of the Plugin Object Associations and load them.
		
	}

	/**
	 * Assumes that the plugin itself has been saved (so its id has been set.)
	 * 
	 */
	@Override
	public void saveSubObjects() {

		String baseFromDirectory = PlatformProperties.getValue("pluginSourceDirectory") + File.separator
				+ this.getPluginOriginDirectory() + File.separator;
		String baseToDirectory = PlatformProperties.getValue("pluginFileDirectory") + File.separator;
		
		Hashtable <String, PluginObjectDocument> pluginObjectsReadIn = new Hashtable();
		
		// Get all objects and hold them in memory.
		for (Object pluginObject: this.getPluginObjects()){
		
			if (true){
				PluginObjectDocument pod = (PluginObjectDocument) pluginObject;
				String key = pluginObject.getClass().getCanonicalName() + "_" + pod.getId();
				System.out.println("KKKKKKKKKKKKKKKKKey was : " + key);
				pluginObjectsReadIn.put(key,  pod);
				
			}
			
		}
		// Get all object associations and look up the objects read in in the previous step
		// Then save the object and the object association.
		for (PluginObjectAssociation poa: this.getPluginObjectAssociations()){
			
			poa.setPluginId(this.id);
			String key = poa.getObjectType() + "_" + poa.getObjectId();
			System.out.println("LLLLLLLLLLLLLLLLLLKey was : " + key);
			PluginObjectDocument pod = pluginObjectsReadIn.get(key);
			
			// Remove any id that the plugin object may have had associated before. It will be different here.
			pod.setId(null);
			pod.setVersion(null);
			
			pod.save();
			
			poa.setObjectId(pod.getId());
			poa.setId(null);
			poa.setVersion(null);
			poa.save();
			
			
		}
		
		// Get all files and save them.
		for (PluginFileDescriptor pfd: this.getPluginFiles()){
			
			String readingFromFileName = baseFromDirectory + pfd.getFilePath() + File.separator + pfd.getFileName();
			System.out.println("reading plugin file " + readingFromFileName);
			
			String savingToFileName = baseToDirectory + this.generatePluginDirectory() + File.separator + pfd.getFileName();
			System.out.println("saving plugin file " + savingToFileName);
			
			try {
				FileUtils.copyFile(new File(readingFromFileName), new File(savingToFileName));
			} catch (Exception e) {
				e.printStackTrace();
			} 
				
		}
		// Get all objects from list and save them.
		
		
	}
	

	@Transient
	@XmlElementWrapper
	@XmlElement(name="pluginObject")
	private List <PluginObjectDocument> pluginObjects = new ArrayList();
	
	@Transient
	@XmlElementWrapper
	@XmlElement(name="pluginObjectAssociation")
	private List <PluginObjectAssociation> pluginObjectAssociations = new ArrayList();

	@Transient
	@XmlElementWrapper
	@XmlElement(name="pluginFile")
	private List <PluginFileDescriptor> pluginFiles = new ArrayList<PluginFileDescriptor>();
	
	public List <PluginFileDescriptor> getPluginFiles () {
		return pluginFiles;
	}

	public void setPluginFiles(List<PluginFileDescriptor> pluginFiles) {
		this.pluginFiles = pluginFiles;
	}


	public List<PluginObjectDocument> getPluginObjects() {
		return pluginObjects;
	}

	public void setPluginObjects(List<PluginObjectDocument> pluginObjects) {
		this.pluginObjects = pluginObjects;
	}

	public String getPluginOriginDirectory() {
		return pluginOriginDirectory;
	}

	public void setPluginOriginDirectory(String pluginOriginDirectory) {
		this.pluginOriginDirectory = pluginOriginDirectory;
	}

	public List<PluginObjectAssociation> getPluginObjectAssociations() {
		return pluginObjectAssociations;
	}

	public void setPluginObjectAssociations(
			List<PluginObjectAssociation> pluginObjectAssociations) {
		this.pluginObjectAssociations = pluginObjectAssociations;
	}
	

}
