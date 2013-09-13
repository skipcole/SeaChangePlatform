package com.seachangesimulations.platform.domain;

import java.io.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.seachangesimulations.platform.dao.MediaObjectDao;

/**
 * This class holds information on media objects (images, videos, soundclips,
 * etc.) associated with an online role play.
 * 
 */
@Entity
@Component
@Scope("prototype")
public class MediaObject extends BaseSCPlatformObject {

	public static final int MEDIA_CATEGORY_IMAGE = 1;

	public static final int MEDIA_TYPE_PNG = 1001;

	public void save(){
		MediaObjectDao dao = (MediaObjectDao) getApplicationContext().getBean("mediaObjectDao", MediaObjectDao.class);
		dao.save(this);
		
	}
	
	public void saveContent(byte[] bytes) {
		try {
			File file = new File("C:\\test\\file.jpg");
			FileUtils.writeByteArrayToFile(file, bytes);
		} catch (Exception e) {
			System.out.println("XXX Unable to save image: " + e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public MediaObject getById(Long id) {
		MediaObjectDao dao = (MediaObjectDao) getApplicationContext().getBean(
				"mediaObjectDao", MediaObjectDao.class);
		return dao.get(id);
	}

	/** Id of the role play that this object is associated with. */
	private Long roleplayId;

	/** Actor id, if any, that this object is associated with. */
	private Long actorId;

	/** Phase id, if any, that this object is associated with. */
	private Long phaseId;

	/** Plugin id, if any, that this object is associated with. */
	private Long pluginId;

	/** Base plugin id, if any, that this object is associated with. */
	private Long basePluginId;

	private String fileName;

	private String fileDirectory;

	/**
	 * The exact type of the media (.png, .mov, etc.) generally based on the
	 * file extension.
	 */
	private int mediaType;

	/** The caterogy of the media (image, movie, etc.) */
	private int mediaCategory;

	public Long getRoleplayId() {
		return roleplayId;
	}

	public void setRoleplayId(Long roleplayId) {
		this.roleplayId = roleplayId;
	}

	public Long getActorId() {
		return actorId;
	}

	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}

	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public Long getPluginId() {
		return pluginId;
	}

	public void setPluginId(Long pluginId) {
		this.pluginId = pluginId;
	}

	public Long getBasePluginId() {
		return basePluginId;
	}

	public void setBasePluginId(Long basePluginId) {
		this.basePluginId = basePluginId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDirectory() {
		return fileDirectory;
	}

	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}

	public int getMediaType() {
		return mediaType;
	}

	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}

	public int getMediaCategory() {
		return mediaCategory;
	}

	public void setMediaCategory(int mediaCategory) {
		this.mediaCategory = mediaCategory;
	}



}
