package com.seachangesimulations.platform.pluginobjects;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class PODPackage {

	private String documentText = "";

	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
	
	public String getAsJSON(){
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(documentText);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Warning: No results returned from PODPackage.getAsJSON.");
		return "";
	}
	
}
