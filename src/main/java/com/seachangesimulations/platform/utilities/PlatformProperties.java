
package com.seachangesimulations.platform.utilities;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class PlatformProperties {

	private static ResourceBundle resourceBundle;

	private static boolean foundPropertiesFile = false;

	public static boolean isFoundPropertiesFile() {
		return foundPropertiesFile;
	}

	public static void setFoundPropertiesFile(boolean foundPropertiesFile) {
		PlatformProperties.foundPropertiesFile = foundPropertiesFile;
	}

	static {

		try {
			resourceBundle = ResourceBundle.getBundle("seachangeplatform");
			foundPropertiesFile = true;
		} catch (Exception e) {
			foundPropertiesFile = false;
			Logger.getRootLogger().warn(
					"Properties file seachangeplatform.properties not found. Need it. Its a Big Deal."); //$NON-NLS-1$
		}
	}

	/**
	 * Gets the value for the property directly from the properties file.
	 * 
	 * @param propertyName
	 * @return
	 */
	public static String getValue(String propertyName) {
		return resourceBundle.getString(propertyName);
	}

	/**
	 * This method returns the release number which is stored here.
	 * 
	 * @return
	 */
	public static String getRelease() {
		return "0.0.0";
	}
}
