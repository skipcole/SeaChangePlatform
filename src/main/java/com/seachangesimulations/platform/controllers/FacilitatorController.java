package com.seachangesimulations.platform.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seachangesimulations.platform.domain.Actor;
import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.Phase;
import com.seachangesimulations.platform.domain.Roleplay;
import com.seachangesimulations.platform.domain.RoleplayInMotion;
import com.seachangesimulations.platform.domain.assignment.InstructorRoleplayAssignment;
import com.seachangesimulations.platform.domain.assignment.PersonRoleplayAssignment;
import com.seachangesimulations.platform.mvc.formbeans.facilitator.FacCreateRPIMFormBean;
import com.seachangesimulations.platform.mvc.formbeans.facilitator.FacLaunchRoleplayFormBean;
import com.seachangesimulations.platform.service.SessionInfoBean;
import com.seachangesimulations.platform.utilities.Util;

@Controller
@RequestMapping(CMC.FACILITATING_BASE)
public class FacilitatorController extends BaseController {

	/**
	 * Creates objects on the facilitator's home page.
	 * 
	 * @param model	Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.INDEX }, method = RequestMethod.GET)
	public String showFacilitatorHome(Model model) {

		getSessionInfoBean().setPlatformZone(SessionInfoBean.FACILITATOR_ZONE);

		// Will move this elsewhere later, but for now

		List<Roleplay> roleplays = new Roleplay().getAll();
		model.addAttribute("roleplays", roleplays);

		return "/facilitating/index.jsp";
	}

	/**
	 * 
	 * @param roleplayId Roleplay Id.
	 * @param rpimId Roleplay in Motion Id.
	 * @param model	Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.F_CREATE_RPIM_GET }, method = RequestMethod.GET)
	public String createRPIMGet(@PathVariable Long roleplayId,
			@PathVariable Long rpimId, Model model) {

		Roleplay roleplay = new Roleplay().getById(roleplayId);
		RoleplayInMotion rpim = new RoleplayInMotion().getModelObject(
				RoleplayInMotion.class, rpimId);

		// Create new form bean with default values.
		FacCreateRPIMFormBean facCreateRPIMFormBean = new FacCreateRPIMFormBean(
				roleplay);

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("rpim", rpim);
		model.addAttribute("all_rpims", new RoleplayInMotion().getAll());
		model.addAttribute("facCreateRPIMFormBean", facCreateRPIMFormBean);

		return "/facilitating/createRPIM.jsp";

	}

	/**
	 * Creates a roleplay in motion.
	 * 
	 * @param facCreateRPIMFormBean
	 * @param roleplayId Roleplay Id
	 * @param rpimId Roleplay in Motion Id
	 * @param model	Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.F_CREATE_RPIM_POST }, method = RequestMethod.POST)
	public String createRPIMPost(
			@ModelAttribute("facCreateRPIMFormBean") @Valid FacCreateRPIMFormBean facCreateRPIMFormBean,
			@PathVariable Long roleplayId,
			@PathVariable Long rpimId, Model model) {

		Roleplay roleplay = new Roleplay().getById(roleplayId);
		RoleplayInMotion rpim = new RoleplayInMotion().getModelObject(
				RoleplayInMotion.class, rpimId);

		BeanUtils.copyProperties(facCreateRPIMFormBean, rpim);
		rpim.setRolePlayId(roleplayId);
		rpim.setPhaseId(new Phase().getFirstForRoleplay(roleplayId));
		rpim.save();

		// Create object plugin associations for this RPIM based off of the RP
		// poas
		roleplay.createCopyofObjectsAndAssociationsForRpim(rpim.getId());

		InstructorRoleplayAssignment ira = new InstructorRoleplayAssignment();
		ira.setInstructorId(new Long(getSessionInfoBean().getPersonId()));
		ira.setRpimId(rpim.getId());
		ira.save();

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("rpim", rpim);

		return "redirect:/facilitating/createRPIM/" + roleplay.getId() + "/"
				+ rpim.getId();
	}

	/**
	 * 
	 * @param roleplay Roleplay.
	 * @param rpim Roleplay in Motion.
	 * @param model	Model to hold objects for the view.
	 */
	private void addAssignmentEssentials(Roleplay roleplay,
			RoleplayInMotion rpim, Model model) {
		// Add the PersonRolePlayAssignments to the model
		List<PersonRoleplayAssignment> pras = new PersonRoleplayAssignment()
				.getRolePlayAssignments(rpim.getRolePlayId());
		model.addAttribute("pras", pras);

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("rpim", rpim);
		assignRoleTypeConstants(model);

	}

	/**
	 * Creates the page that allows the addition/subraction of players to roles
	 * in a roleplay.
	 * 
	 * @param rpimId  Roleplay in Motion Id.
	 * @param model	Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.F_ASSIGN_PLAYER_GET }, method = RequestMethod.GET)
	public String assignPlayersGet(@PathVariable Long rpimId, Model model) {

		RoleplayInMotion rpim = new RoleplayInMotion().getById(rpimId);
		Roleplay roleplay = new Roleplay().getById(rpim.getRolePlayId());

		addAssignmentEssentials(roleplay, rpim, model);

		return "/facilitating/assignPlayerstoRPIM.jsp";
	}

	/**
	 * 
	 * @param rpimId  Roleplay in Motion Id.
	 * @param actorId Actor Id.
	 * @param praId Person Roleplay Assignment Id.
	 * @param request HttpServletRequest (holding userName to be assigned).
	 * @param model	Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.F_ASSIGNPLAYER_RP_A_PRA }, method = RequestMethod.POST)
	public String assignPlayersPost(@PathVariable Long rpimId,
			@PathVariable Long actorId, @PathVariable Long praId,
			HttpServletRequest request, Model model) {

		RoleplayInMotion rpim = new RoleplayInMotion().getById(rpimId);
		Roleplay roleplay = new Roleplay().getById(rpim.getRolePlayId());
		Actor actor = new Actor().getById(actorId);

		PersonRoleplayAssignment personRoleplayAssignment = new PersonRoleplayAssignment()
				.getModelObject(PersonRoleplayAssignment.class, praId);

		// Two values from form (userName and roleType)
		Person person = new Person().getByUsername(request
				.getParameter("userName"));
		int thisRoleType = Util.string2Int(request.getParameter("roleType"));

		if (person != null) {
			personRoleplayAssignment.setPersonId(person.getId());
			personRoleplayAssignment.setRoleplayId(roleplay.getId());
			personRoleplayAssignment.setRpimId(rpimId);
			personRoleplayAssignment
					.setRoleplayName(roleplay.getRoleplayName());
			personRoleplayAssignment
					.setRpimName(rpim.getRoleplayInMotionName());
			personRoleplayAssignment.setActorId(actorId);
			personRoleplayAssignment.setActorName(actor.getActorName());
			personRoleplayAssignment.setRoleType(thisRoleType);
			personRoleplayAssignment.save();

		}

		addAssignmentEssentials(roleplay, rpim, model);

		return "redirect:/facilitating/assignPlayers/" + rpimId;
	}

	/**
	 * 
	 * @param rpimId Roleplay in Motion Id.
	 * @param model	Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.F_LAUNCH_RPIM_GET }, method = RequestMethod.GET)
	public String launchRPIMGet(@PathVariable Long rpimId, Model model) {

		RoleplayInMotion rpim = new RoleplayInMotion().getModelObject(
				RoleplayInMotion.class, rpimId);
		Roleplay roleplay = new Roleplay().getById(rpim.getRolePlayId());

		// Launch new form bean with default values.
		FacLaunchRoleplayFormBean facLaunchRPIMFormBean = new FacLaunchRoleplayFormBean();

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("rpim", rpim);
		model.addAttribute("facLaunchRPIMFormBean", facLaunchRPIMFormBean);

		return "/facilitating/launchRPIM.jsp";

	}

	/**
	 * 
	 * @param facLaunchRoleplayFormBean Facilitator Launch Roleplay Form Bean.
	 * @param rpimId Roleplay in Motion Id.
	 * @param model	Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.F_LAUNCH_RPIM_POST }, method = RequestMethod.POST)
	public String launchRPIMPost(
			@ModelAttribute("facLaunchRPIMFormBean") @Valid FacLaunchRoleplayFormBean facLaunchRoleplayFormBean,
			@PathVariable Long rpimId,
			Model model) {

		RoleplayInMotion rpim = new RoleplayInMotion().getModelObject(
				RoleplayInMotion.class, rpimId);
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
