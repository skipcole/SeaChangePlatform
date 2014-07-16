package com.seachangesimulations.platform.utilities;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.seachangesimulations.platform.domain.Phase;
import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.domain.Roleplay;
import com.seachangesimulations.platform.roleplayobjects.PhaseImage;

public class ObjectPackager {

	public static void main(String[] args) {

		Plugin plugin = new Plugin();
		
		plugin.setAuthor("Skip Cole");
		plugin.setAuthorURI("http://www.seachangesimulations.com");
		plugin.setPluginName("Basic Fixed Text Page");
		plugin.setPluginURI("http://www.seachangesimulations.com/registeredPlugins/SeaChangeSimulationsLLC/basicFixedTextPage");
		plugin.setDescription("A basic page to change roleplay text that never changes.");
		plugin.setPluginReleaseMajorNumber("1");
		plugin.setPluginReleaseMinorNumber("0");
		plugin.setPluginReleaseMicroNumber("0");
		plugin.setPluginReleaseLetters("SNAPSHOT");
		plugin.setLicense("Creative Commons");
		plugin.setShortFormOrgName("SeaChangeSimulations");
		plugin.setShortFormPluginName("BasicFixedTextPage");
		
		//packagePlugin(plugin, "C:\\SeaChangePlatform\\zPluginOriginDirectory\\BasicFixedTextPage\\pluginDefinition.xml");

		loadPluginsFromDisk("C:\\SeaChangePlatform\\zPluginOriginDirectory");
		
		/*
		Roleplay roleplay = new Roleplay();
		
		roleplay.setId(new Long(1));
		roleplay.setRoleplayName("a roleplay name");
		roleplay.save();
		
		
		
		packageRoleplay(roleplay, "C:\\SeaChangePlatform\\zSavedRoleplays\\file.xml");

		unPackageRoleplay();
		*/

	}

	private static void unPackageRoleplay() {

		try {

			File file = new File(
					"C:\\SeaChangePlatform\\zSavedRoleplays\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Roleplay.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Roleplay roleplay = (Roleplay) jaxbUnmarshaller.unmarshal(file);
			System.out.println(roleplay);
			
			roleplay.saveSubObjects();
			
			// Save all Assignment objects read in
			
			

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	private static void packageRoleplay(Roleplay roleplay, String fileLocation) {

		
		Phase phaseOne = new Phase();
		phaseOne.setId(new Long(1));
		phaseOne.setRoleplayId(roleplay.getId());
		phaseOne.setPhaseName("first phase");
		phaseOne.save();
		
		PhaseImage phaseImageOne = new PhaseImage();
		phaseImageOne.setPhaseId(new Long(1));
		phaseImageOne.setPhaseImageName("my image name");
		phaseImageOne.save();
		
		// Load assignment objects into array
		roleplay.loadSubObjects();

		File file = new File(fileLocation);
		
		Class [] classList = {Roleplay.class};
		saveXMLObjectToFile(file, classList, Roleplay.class);


	}
	
	private static void packagePlugin(Plugin plugin, String fileLocation) {
		
		File file = new File(fileLocation);
		Class [] classList = {Plugin.class};
		saveXMLObjectToFile(file, classList, plugin);
		
	}
	
	private static void saveXMLObjectToFile(File file, Class [] classList, Object objectToSave){
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(classList);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(objectToSave, file);
			jaxbMarshaller.marshal(objectToSave, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	// Move to build helpers?
	public static void loadPluginsFromDisk(String fileLocation){
		File file = new File(fileLocation);
		

		    for (File fileEntry : file.listFiles()) {
		        if (fileEntry.isDirectory()) {
		        	System.out.println("Checking directory: " + fileEntry.getAbsolutePath());
		            File pluginDefinitionFile = new File(fileEntry.getAbsolutePath() + File.separator + "pluginDefinition.xml");
		            
		            
		            if (pluginDefinitionFile.exists()){
		            	try {

		        			JAXBContext jaxbContext = JAXBContext.newInstance(Plugin.class);

		        			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		        			Plugin plugin = (Plugin) jaxbUnmarshaller.unmarshal(pluginDefinitionFile);
		        			System.out.println(plugin);
		        			
		        			plugin.saveSubObjects();
		        			

		        		} catch (JAXBException e) {
		        			e.printStackTrace();
		        		}
		            } else {
		            	System.out.println("   Warning: Plugin Definition File " + pluginDefinitionFile.getAbsolutePath() + " not found.");
		            }
		        } 
		}
		
	}
	
}
