package com.seachangesimulations.platform.controllers;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seachangesimulations.platform.domain.Actor;
import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.domain.PluginPointer;
import com.seachangesimulations.platform.pluginobjects.PluginObjectAssociation;
import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;
import com.seachangesimulations.platform.utilities.Util;

@Controller
@RequestMapping(CMC.PLUGINOBJECT_BASE)
public class PluginObjectController extends BaseController{


	@RequestMapping(value = {"/testRest"}, method = RequestMethod.GET)
	public String testRest(){
		return "developing/testRest.jsp";
	}
	
	/**
	 * Returns an xml representation of an actor, if the requestor is authorized
	 * to obtain it. The amount of information passed back can be modulated by
	 * the optMod token.
	 * 
	 * @param scAuthToken
	 * @param actorId
	 * @param mod
	 * @return
	 */
	@RequestMapping(value = { "/xml/scauthtoken/{scAuthToken}/actor/{actorId}/optMod/{mod}" }, method = RequestMethod.GET)
	@ResponseBody
	public String getXmlActor(@PathVariable String scAuthToken, @PathVariable Long actorId, @PathVariable String mod) {

		System.out.println("scauth was: " + scAuthToken);
		System.out.println("actor id was: " + actorId);
		System.out.println("mod was: " + mod);
		Actor a = new Actor();
		a.setActorName("Richard");

		return Util.packageIntoXML(Actor.class, a);

	}
	
	@RequestMapping(value = { CMC.PO_DOC }, method = RequestMethod.GET)
	public  @ResponseBody String getString(@PathVariable Long pId, @PathVariable Long objectIndex) {
		
		
		Plugin plugin = new Plugin().getById(pId);
		
		// Storing the id of the Plugin we are on.
		mark();
		System.out.println("We are on plugin pointer" + pId);
		System.out.println("We are on plugin " + plugin.getId());
		System.out.flush();
		mark();
		
		getSessionInfoBean().setPluginId(plugin.getId());
		
		System.out.println("object index was " + objectIndex);
		
		printMyCoordinates();
		
		PluginObjectAssociation poa = new PluginObjectAssociation();
		
		List poaList = poa.getAllForPlugin(getSessionInfoBean().getPluginId());
		
		PluginObjectDocument pod = new PluginObjectDocument();
		
		if ((poaList != null) && (poaList.size() > 0) ) {
			PluginObjectAssociation poa1 = (PluginObjectAssociation) poaList.get(0);
			
			pod = new PluginObjectDocument().getById(poa1.getObjectId());
			System.out.println("***********************************888");
			System.out.println(pod.getDocumentText());
			System.out.println("***********************************888");
			
		}

		printMyCoordinates();
		
		return pod.getDocumentText();
		
	}
	
	@RequestMapping(value = { "getPluginNumber" }, method = RequestMethod.GET)
	public  @ResponseBody String getPluginNumber(){
		String returnString = "P id:P index are " + getSessionInfoBean().getPluginId() 
				+ ":" + getSessionInfoBean().getPluginIndex();
		return returnString;
	}
	
	@RequestMapping(value = {"setPluginId/{pluginPointerId}"}, method = RequestMethod.GET)
	public String setPluginId(@PathVariable Long pluginPointerId) {
		
		PluginPointer pluginPointer = new PluginPointer().getById(pluginPointerId);
		
		Plugin plugin = new Plugin().getById(pluginPointer.getPluginId());
		
		// Storing the id of the Plugin we are on.
		mark();
		System.out.println("We are on plugin pointer" + pluginPointerId);
		System.out.println("We are on plugin " + plugin.getId());
		System.out.flush();
		mark();
		
		getSessionInfoBean().setPluginId(plugin.getId());
		return "set to " + plugin.getId();
		
	}
}
