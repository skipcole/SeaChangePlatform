package com.seachangesimulations.platform.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.Phase;
import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.domain.PluginPointer;
import com.seachangesimulations.platform.domain.RoleplayInMotion;
import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;
import com.seachangesimulations.platform.pluginobjects.PluginObjectAssociation;
import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;
import com.seachangesimulations.platform.service.SessionInfoBean;

@Controller
@RequestMapping(CMC.PLAYING_BASE)
public class PlayerController extends BaseController {

	/**
	 * Shows the page where the player can select a role play to enter into.
	 * 
	 * @param principal
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {CMC.INDEX}, method = RequestMethod.GET) // NO_UCD (unused code)
	public String showPlayerEntryPage(Principal principal, Model model) {

		getSessionInfoBean().setPlatformZone(SessionInfoBean.PLAYER_ZONE);
		
		Person person = new Person().getByUsername(principal.getName());
		model.addAttribute("personRoleplayAssignments", 
				new PersonRoleplayAssignment().getAllRoleplaysForPerson(person.getId()));
		
		return "playing/index.jsp";
	}
	
	@RequestMapping(value = { "index/setZone" }, method = RequestMethod.GET)
	public String setPlayerZone(Model model) {

		getSessionInfoBean().setPlatformZone(SessionInfoBean.PLAYER_ZONE);
		return "redirect:/playing/index";
	}
	
	/**
	 * Creates the roleplay page with it tab headings.
	 * 
	 * @param praId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {CMC.P_PERSONROLEPLAYASSIGNMENT}, method = RequestMethod.GET)
	public String showTheRoleplayPage(@PathVariable Long praId, Model model) {

		PersonRoleplayAssignment pra = new PersonRoleplayAssignment().getById(praId);
		
		RoleplayInMotion rpim = new RoleplayInMotion().getById(pra.getRpimId());

		Phase phase = rpim.getMyPhase();
		
		getSessionInfoBean().setRoleplayId(pra.getRolePlayId());
		getSessionInfoBean().setRolePlayInMotionId(pra.getRpimId());
		getSessionInfoBean().setActorId(pra.getActorId());
		getSessionInfoBean().setPhaseId(phase.getId());
		
		// When entering show the plugin loaded at the first (most left) index
		getSessionInfoBean().setPluginIndex(new Long(0));
		
		model.addAttribute("personRoleplayAssignments", pra);
		
		List playersTabs = new PluginPointer().getCurrentSet(pra.getRolePlayId(), pra.getActorId(), phase.getId());
		
		if (true) {
			playersTabs.add(new PluginPointer().getControlPluginByHandle(PluginPointer.SYSTEM_CONTROL));
			model.addAttribute("phasesForThisRoleplay", new Phase().getAllForRoleplay(getSessionInfoBean().getRoleplayId()));
		}
		
		model.addAttribute("pluginPointers", playersTabs);
		
		return "playing/theRoleplay.jsp";
	}
	
	/**
	 * Shows the one individual plugin.
	 * 
	 * @param pluginPointerId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {CMC.P_SHOWPLUGINPOINTER}, method = RequestMethod.GET)
	public String showPlugin(@PathVariable Long pluginPointerId, Model model) {
		
		PluginPointer pluginPointer = new PluginPointer().getById(pluginPointerId);
		
		Plugin plugin = new Plugin().getById(pluginPointer.getPluginId());
		
		// Storing the id of the Plugin we are on.
		mark();
		System.out.println("We are on plugin pointer" + pluginPointerId);
		System.out.println("We are on plugin " + plugin.getId());
		System.out.flush();
		mark();
		
		getSessionInfoBean().setPluginId(plugin.getId());
		
		if (plugin.isSystemPlugin()){
			Phase phase = new Phase().getById(this.getSessionInfoBean().getPhaseId());
			model.addAttribute("phase", phase);
			model.addAttribute("phasesForThisRoleplay", 
					new Phase().getAllForRoleplay(this.getSessionInfoBean().getRoleplayId()));
			return plugin.getPluginDirectory();
		} else {
			// We have everything we need now so when the plugin sends a request for an object
			// we can send it what it needs
			printMyCoordinates();
			return "redirect:" + plugin.generateLinkToPlugin() + "#" + plugin.getId();
		}
		
		//model.addAttribute("pageText", pluginProvider.processPlugin(plugin, getSessionInfoBean()));
		
		//return "playing/showPlugin.jsp";
		
	}



	@RequestMapping(value = { "getSessionInfo" }, method = RequestMethod.GET)
	public  @ResponseBody SessionInfoBean getSessionInfoGetRequest() {
		
		return this.getSessionInfoBean();
	}
	

	
	/**
	 * Called by a 'control' player to change the phase of the roleplay.
	 * 
	 * @param model
	 * @param phase
	 * @return
	 */
	@RequestMapping(value = {CMC.P_CHANGEPHASE}, method = RequestMethod.POST)
	public String changeChase(Model model, @ModelAttribute("phase") Phase phase) {

		System.out.println("changing phase");
		// SKIPTODO - make it reals
		this.getSessionInfoBean().setPhaseId(new Long(1));
		
		model.addAttribute("phasesForThisRoleplay", new Phase().getAllForRoleplay(getSessionInfoBean().getRoleplayId()));
		
		return "authoring/selectRoleplay.jsp";
		
	}
	
		
}
