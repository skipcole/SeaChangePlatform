package com.seachangesimulations.platform.mvc;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.Phase;
import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.domain.PluginPointer;
import com.seachangesimulations.platform.domain.Roleplay;
import com.seachangesimulations.platform.domain.RoleplayInMotion;
import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;
import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;
import com.seachangesimulations.platform.service.SessionInfoBean;

@Controller
@RequestMapping("/playing")
public class PlayerController extends BaseController {

	@RequestMapping(value = {"index" }, method = RequestMethod.GET)
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
	
	@RequestMapping(value = { "pra/{praId}" }, method = RequestMethod.GET)
	public String showTheRoleplayPage(@PathVariable Long praId, Model model) {

		PersonRoleplayAssignment pra = new PersonRoleplayAssignment().getById(praId);
		
		Roleplay roleplay = new Roleplay().getById(pra.getRolePlayId());
		RoleplayInMotion rpim = new RoleplayInMotion().getById(pra.getRpimId());

		Phase phase = rpim.getMyPhase();
		
		getSessionInfoBean().setRoleplayId(pra.getRolePlayId());
		getSessionInfoBean().setRolePlayInMotionId(pra.getRpimId());
		getSessionInfoBean().setActorId(pra.getActorId());
		getSessionInfoBean().setPhaseId(phase.getId());
		
		// When entering show the plugin loaded at the first (most left) index
		getSessionInfoBean().setPluginIndex(new Long(0));
		
		model.addAttribute("personRoleplayAssignments", pra);
		model.addAttribute("pluginPointers", 
				new PluginPointer().getCurrentSet(pra.getRolePlayId(), pra.getActorId(), phase.getId()));
		
		return "playing/theRoleplay.jsp";
	}
	
	@RequestMapping(value = { "showPlugin/{pluginPointerId}" }, method = RequestMethod.GET)
	public String showPlugin(@PathVariable Long pluginPointerId, Model model) {
		
		PluginPointer pluginPointer = new PluginPointer().getById(pluginPointerId);
		
		Plugin plugin = new Plugin().getById(pluginPointer.getPluginId());
		
		System.out.println(plugin.generatePluginDirectory() + "/1.htm");
		
		//return (" will send contents from redirect:/resources/plugins/" + plugin.generatePluginDirectory() + "/1.htm");
		return ("redirect:/resources/plugins/" + plugin.generatePluginDirectory() + "/1.htm");
	}
	
	
	@RequestMapping(value = { "getSessionInfo" }, method = RequestMethod.GET)
	public  @ResponseBody SessionInfoBean getSessionInfoGetRequest() {
		
		return this.getSessionInfoBean();
	}
	
	@RequestMapping(value = { "getObject/objectIndex/{objectIndex}.json" }, method = RequestMethod.GET)
	public  @ResponseBody PluginObjectDocument getObjectInJSON(@PathVariable Long objectIndex) {
		
		PluginPointer pp = new PluginPointer().getByPlayerValues(
				getSessionInfoBean().getRoleplayId(),
				getSessionInfoBean().getActorId(),
				getSessionInfoBean().getPhaseId(),
				getSessionInfoBean().getPluginIndex()
				);
		
		// objectIndex is used to get the correct object from the plugin.
		
		PluginObjectDocument pod = new PluginObjectDocument();
		pod.setDocumentName("documentName");
		pod.setDocumentText("doc text");
		return pod;
	}
	
		
}
