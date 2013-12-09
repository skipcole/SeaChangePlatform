package com.seachangesimulations.platform.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.seachangesimulations.platform.domain.Actor;
import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.Phase;
import com.seachangesimulations.platform.domain.RoleplayInMotion;
import com.seachangesimulations.platform.domain.Roleplay;
import com.seachangesimulations.platform.domain.assignment.InstructorRoleplayAssignment;
import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;
import com.seachangesimulations.platform.mvc.formbeans.facilitator.FacAssignPlayersFormBean;
import com.seachangesimulations.platform.mvc.formbeans.facilitator.FacCreateRPIMFormBean;
import com.seachangesimulations.platform.mvc.formbeans.facilitator.FacLaunchRoleplayFormBean;
import com.seachangesimulations.platform.service.SessionInfoBean;

@Controller
@RequestMapping("/facilitating")
public class FacilitatorController extends BaseController {

	@RequestMapping(value = { "index" }, method = RequestMethod.GET)
	public String showFacilitatorHome(Model model) {

		getSessionInfoBean().setPlatformZone(SessionInfoBean.FACILITATOR_ZONE);
		
		// Will move this elsewhere later, but for now

		List <Roleplay> roleplays = new Roleplay().getAll();
		model.addAttribute("roleplays", roleplays);

		return "/facilitating/index.jsp";
	}
	
	@RequestMapping(value = { "index/setZone" }, method = RequestMethod.GET)
	public String setFacilitatorZone(Model model) {

		getSessionInfoBean().setPlatformZone(SessionInfoBean.FACILITATOR_ZONE);
		return "redirect:/facilitating/index";
	}
	

	@RequestMapping(value = { "createRPIM/{rId}/{rpimId}" }, method = RequestMethod.GET)
	public String createRPIMGet(@PathVariable Long rId, @PathVariable Long rpimId, Model model) {

		Roleplay roleplay = new Roleplay().getById(rId);
		RoleplayInMotion rpim = new RoleplayInMotion().getModelObject(RoleplayInMotion.class, rpimId);

		// Create new form bean with default values.
		FacCreateRPIMFormBean facCreateRPIMFormBean = new FacCreateRPIMFormBean(roleplay);

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("rpim", rpim);
		model.addAttribute("all_rpims", new RoleplayInMotion().getAll());
		model.addAttribute("facCreateRPIMFormBean", facCreateRPIMFormBean);

		return "/facilitating/createRPIM.jsp";

	}

	@RequestMapping(value = { "createRPIM/{rId}/{rpimId}" }, method = RequestMethod.POST)
	public String createRPIMPost(
			@ModelAttribute("facCreateRPIMFormBean") @Valid FacCreateRPIMFormBean facCreateRPIMFormBean,
			BindingResult bindingResult, @PathVariable Long rId, @PathVariable Long rpimId, Model model,
			final HttpServletRequest request, Principal principal) {

		Roleplay roleplay = new Roleplay().getById(rId);
		RoleplayInMotion rpim = new RoleplayInMotion().getModelObject(RoleplayInMotion.class, rpimId);

		BeanUtils.copyProperties(facCreateRPIMFormBean, rpim);
		rpim.setRolePlayId(rId);
		rpim.setPhaseId(new Phase().getFirstForRoleplay(rId));
		rpim.save();

		InstructorRoleplayAssignment ira = new InstructorRoleplayAssignment();
		ira.setInstructorId(new Long(getSessionInfoBean().getPersonId()));
		ira.setRpimId(rpim.getId());
		ira.save();

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("rpim", rpim);

		return "redirect:/facilitating/createRPIM/" + roleplay.getId() + "/" + rpim.getId();
	}

	@RequestMapping(value = { "assignPlayers/{rpimId}" }, method = RequestMethod.GET)
	public String assignPlayersGet(@PathVariable Long rpimId, Model model) {

		RoleplayInMotion rpim = new RoleplayInMotion().getById(rpimId);
		Roleplay roleplay = new Roleplay().getById(rpim.getRolePlayId());

		model.addAttribute("actorsForThisRoleplay", new Actor().getAllForRoleplay(rpim.getRolePlayId()));

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("rpim", rpim);
		
		model.addAttribute("facAssignPlayersFormBean", new FacAssignPlayersFormBean());

		return "/facilitating/assignPlayerstoRPIM.jsp";
	}

	@RequestMapping(value = { "assignPlayers/{rpimId}/actorId/{aId}" }, method = RequestMethod.POST)
	public String assignPlayersPost(@PathVariable Long rpimId, @PathVariable Long aId,
			@ModelAttribute("facAssignPlayersFormBean") FacAssignPlayersFormBean facAssignPlayersFormBean,	Model model) {
		
		RoleplayInMotion rpim = new RoleplayInMotion().getById(rpimId);
		Roleplay roleplay = new Roleplay().getById(rpim.getRolePlayId());
		Actor actor = new Actor().getById(aId);

		Person person = new Person().getByUsername(facAssignPlayersFormBean.getUserName());

		if (person != null) {
			PersonRoleplayAssignment personRoleplayAssignment = new PersonRoleplayAssignment();
		
			personRoleplayAssignment.setPersonId(person.getId());
			personRoleplayAssignment.setRolePlayId(roleplay.getId());
			personRoleplayAssignment.setRpimId(rpimId);
			personRoleplayAssignment.setRoleplayName(roleplay.getRoleplayName());
			personRoleplayAssignment.setRpimName(rpim.getRoleplayInMotionName());
			personRoleplayAssignment.setActorId(aId);
			personRoleplayAssignment.setActorName(actor.getActorName());
			
			personRoleplayAssignment.save();
		
		}

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("rpim", rpim);

		return "redirect:/facilitating/assignPlayers/" + rpimId;
	}

	@RequestMapping(value = { "launchRPIM/{rpimId}" }, method = RequestMethod.GET)
	public String launchRPIMGet(@PathVariable Long rpimId, Model model) {

		RoleplayInMotion rpim = new RoleplayInMotion().getModelObject(RoleplayInMotion.class, rpimId);
		Roleplay roleplay = new Roleplay().getById(rpim.getRolePlayId());

		// Launch new form bean with default values.
		FacLaunchRoleplayFormBean facLaunchRPIMFormBean = new FacLaunchRoleplayFormBean();

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("rpim", rpim);
		model.addAttribute("facLaunchRPIMFormBean", facLaunchRPIMFormBean);

		return "/facilitating/launchRPIM.jsp";

	}

	@RequestMapping(value = { "launchRPIM/{rpimId}" }, method = RequestMethod.POST)
	public String launchRPIMPost(
			@ModelAttribute("facLaunchRPIMFormBean") @Valid FacLaunchRoleplayFormBean facLaunchRoleplayFormBean,
			BindingResult bindingResult, @PathVariable Long rpimId, Model model, final HttpServletRequest request,
			Principal principal) {

		RoleplayInMotion rpim = new RoleplayInMotion().getModelObject(RoleplayInMotion.class, rpimId);
		Roleplay roleplay = new Roleplay().getById(rpim.getRolePlayId());

		boolean saveChanges = false;

		if (facLaunchRoleplayFormBean.getLaunchButton() != null) {
			rpim.setActive(true);
			saveChanges = true;
		}

		if (facLaunchRoleplayFormBean.getDeactivateButton() != null) {
			rpim.setActive(false);
			saveChanges = true;
		}

		if (saveChanges) {
			rpim.save();
		}

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("rpim", rpim);

		return "redirect:/facilitating/launchRPIM/" + rpim.getId();
	}

}
