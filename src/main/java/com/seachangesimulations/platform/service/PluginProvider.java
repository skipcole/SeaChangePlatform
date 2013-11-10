package com.seachangesimulations.platform.service;

import java.io.File;
import java.util.Hashtable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.domain.PluginFile;
import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;
import com.seachangesimulations.platform.utilities.PlatformProperties;
import com.seachangesimulations.platform.utilities.Util;

@Service("pluginProvider")
@Scope("singleton")
public class PluginProvider {
	
	private static Hashtable pluginTexts = new Hashtable();
	


	/**
	 * Saves the plugin to a file on the system.
	 * 
	 * @param pluginFile
	 * @param pluginFileObject
	 * @return
	 */
	public boolean savePluginToFile(MultipartFile pluginFile, PluginFile pluginFileObject) {
		
		boolean fileSaved = false;
		
		if (!pluginFile.isEmpty()) {
			System.out.println(pluginFile.getOriginalFilename());

			pluginFileObject.setFileDescription(pluginFile.getOriginalFilename());
			
			pluginFileObject.save();

			System.out.println(PlatformProperties.getValue("pluginFileDirectory"));

			Plugin plugin = new Plugin().getById(pluginFileObject.getPluginId());

			try {
				String fullFilePath = PlatformProperties.getValue("pluginFileDirectory") + File.separator
						+ plugin.generatePluginDirectory() + File.separator + pluginFile.getOriginalFilename();

				System.out.println("             ");
				System.out.println(fullFilePath);
				System.out.println("             ");

				fileSaved = Util.saveFileContent(fullFilePath, pluginFile.getBytes());
			} catch (Exception e) {
				System.out.println("problem with saving plugin file: " + e.getMessage());
			}

		}
		return fileSaved;
	}
	
	public static String getPluginFile(Plugin plugin){
		
		String filePathAndName = PlatformProperties.getValue("pluginFileDirectory") + File.separator
				+ plugin.generatePluginDirectory() + File.separator + "1.htm";
		
		//Caching on plugin file location, so plugins that pull from the same file will not need
		// to pull separately.
		String pluginText = (String) pluginTexts.get(filePathAndName);
		
		if (pluginText == null){
			//open and pull it from file.
			pluginText = new String(Util.getFileContent(filePathAndName));
			
			pluginTexts.put(filePathAndName, pluginText);
		}
		
		return pluginText;
	}

}
