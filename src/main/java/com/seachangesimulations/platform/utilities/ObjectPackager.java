package com.seachangesimulations.platform.utilities;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.solr.common.util.FileUtils;

import com.seachangesimulations.platform.domain.Phase;
import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.domain.PluginPointer;
import com.seachangesimulations.platform.domain.Roleplay;
import com.seachangesimulations.platform.pluginobjects.PluginFileDescriptor;
import com.seachangesimulations.platform.pluginobjects.PluginObjectAssociation;
import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;
import com.seachangesimulations.platform.roleplayobjects.PhaseImage;

public class ObjectPackager {
	
	public static String PLUGIN_DEF_FILE = "pluginDefinition.xml";
	public static String CONTROL_PLUGIN_DIR = "SystemPluginDefinitions";
	

	public static void main(String[] args) {


		Plugin plugin = createTemplatePlugin();
		packagePlugin(plugin, "C:\\SeaChangePlatform\\zPluginOriginDirectory\\BasicFixedTextPage\\pluginDefinition.xml");

		//loadPluginsFromDisk("C:\\SeaChangePlatform\\zPluginOriginDirectory");
		
		/*
		Roleplay roleplay = new Roleplay();
		
		roleplay.setId(new Long(1));
		roleplay.setRoleplayName("a roleplay name");
		roleplay.save();
		
		String fileLocation = "C:\\SeaChangePlatform\\zSavedRoleplays\\file.xml";
		
		packageRoleplay(roleplay, fileLocation);

		unPackageRoleplay(new File(fileLocation));
		*/

	}
	
	public static Plugin createTemplatePlugin(){
		
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
		
		PluginObjectDocument pod = new PluginObjectDocument();
		pod.setId(new Long(1));
		pod.setDocumentName("basicTextDocName");
		pod.setDocumentDescription("basicTextDocDescription");
		plugin.getPluginObjects().add(pod);
		
		PluginObjectAssociation poa = new PluginObjectAssociation();
		poa.setPluginId(plugin.getId());
		poa.setObjectId(pod.getId());
		poa.setObjectType(PluginObjectDocument.class.getCanonicalName());
		plugin.getPluginObjectAssociations().add(poa);
		
		PluginFileDescriptor pfd = new PluginFileDescriptor();
		pfd.setFileDescription("main html page");
		pfd.setFileName("BasicFixedTextPage.htm");
		pfd.setFilePath("");
		plugin.getPluginFiles().add(pfd);
		
		return plugin;
	}

	private static void unPackageRoleplay(File roleplayFile) {

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Roleplay.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Roleplay roleplay = (Roleplay) jaxbUnmarshaller.unmarshal(roleplayFile);
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
		Class [] classList = {Plugin.class, PluginFileDescriptor.class, PluginObjectAssociation.class, PluginObjectDocument.class};
		saveXMLObjectToFile(file, classList, plugin);
		
	}
	
	private static void saveXMLObjectToFile(File file, Class [] classList, Object objectToSave){
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(classList);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "");

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
		
			// Loop over all of the directories found.
		    for (File fileEntry : file.listFiles()) {
		        if ((fileEntry.isDirectory()) && (!(CONTROL_PLUGIN_DIR.equalsIgnoreCase(fileEntry.getName())))) {
		        	System.out.println("Checking directory: " + fileEntry.getAbsolutePath());
		            File pluginDefinitionFile = new File(fileEntry.getAbsolutePath() + File.separator + "pluginDefinition.xml");
		                        
		            if (pluginDefinitionFile.exists()){
		            	try {

		        			JAXBContext jaxbContext = JAXBContext.newInstance(Plugin.class);

		        			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		        			Plugin plugin = (Plugin) jaxbUnmarshaller.unmarshal(pluginDefinitionFile);
		        			
		        			plugin.setPluginOriginDirectory(fileEntry.getName());
		        			System.out.println(plugin);
		        			
		        			// Save plugin to get its id
		        			plugin.save();
		        			
		        			// ON some systems running into write permissions to create directory. So creating it explicitly.
		        			String saveDirectory = PlatformProperties.getValue("pluginFileDirectory") 
		        					+ File.separator + plugin.generatePluginDirectory();
		        			
		        			File newDir = new File(saveDirectory);
		        			newDir.mkdirs();
		        			
		        			// Save plugin definition file (just for reference, future reads will be from the database.)
		        			String savingToFileName = PlatformProperties.getValue("pluginFileDirectory") 
		        					+ File.separator + plugin.generatePluginDirectory() + File.separator + PLUGIN_DEF_FILE;
		        			
		        			FileUtils.copyFile(pluginDefinitionFile, new File(savingToFileName));
		        			
		        			plugin.saveSubObjects();
		        			

		        		} catch (Exception e) {
		        			e.printStackTrace();
		        		}
		            } else {
		            	System.out.println("   Warning: Plugin Definition File " + pluginDefinitionFile.getAbsolutePath() + " not found.");
		            }
		        } else if ((fileEntry.isDirectory()) && (CONTROL_PLUGIN_DIR.equalsIgnoreCase(fileEntry.getName()))) {
		        	System.out.println("Checking Control Plugin Directory: " + fileEntry.getAbsolutePath());
		        	
		        	for (File possiblePluginDefinition : fileEntry.listFiles()) {
		        		if (possiblePluginDefinition.isFile() && (possiblePluginDefinition.getName().endsWith(".xml"))){
		        			System.out.println("found possible contol plugin: " + possiblePluginDefinition.getName());
		        			try {

			        			JAXBContext jaxbContext = JAXBContext.newInstance(Plugin.class);

			        			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			        			Plugin plugin = (Plugin) jaxbUnmarshaller.unmarshal(possiblePluginDefinition);
			        			
			        			if (plugin.isSystemPlugin()){
			        				
			        				// Save plugin to get its id
			        				plugin.save();
			        				
			        				PluginPointer controlPointer = new PluginPointer();
			        				controlPointer.setSystemPluginPointer(true);
			        				controlPointer.setSystemPluginHandle(PluginPointer.SYSTEM_CONTROL);
			        				controlPointer.setPluginHeading(plugin.getShortFormPluginName());
			        				controlPointer.setPluginId(plugin.getId());
			        				controlPointer.save();
			        				
			        			} else {
			        				System.out.println("Warning: non-system plugin found in system plugin directory.");
			        			}
			        			

			        		} catch (Exception e) {
			        			e.printStackTrace();
			        		}
		        		}
		        	}
		        	
		        }
		}
		
	}
	
}
