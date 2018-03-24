package com.seachangesimulations.platform.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.seachangesimulations.platform.domain.Actor;
import com.seachangesimulations.platform.domain.MediaObject;
import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.Phase;
import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.domain.PluginPointer;
import com.seachangesimulations.platform.domain.Roleplay;
import com.seachangesimulations.platform.mvc.formbeans.author.AuthCustomizePluginDocumentFormBean;
import com.seachangesimulations.platform.mvc.formbeans.author.AuthorAddPluginFormBean;
import com.seachangesimulations.platform.mvc.formbeans.author.AuthorCreateActorFormBean;
import com.seachangesimulations.platform.mvc.formbeans.author.AuthorCreatePhaseFormBean;
import com.seachangesimulations.platform.mvc.formbeans.author.AuthorCreateRoleplayFormBean;
import com.seachangesimulations.platform.mvc.formbeans.author.AuthorPublishRoleplayFormBean;
import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;
import com.seachangesimulations.platform.service.SessionInfoBean;
import com.seachangesimulations.platform.utilities.Util;

@Controller
@RequestMapping(CMC.AUTHORING_BASE)
public class AuthorController extends BaseController {

	/**
	 * Shows the authoring control section.
	 * 
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.INDEX }, method = RequestMethod.GET)
	public String showAuthoringPage(Map<String, Object> model) {

		getSessionInfoBean().setPlatformZone(SessionInfoBean.AUTHOR_ZONE);
		return "authoring/index.jsp";
	}

	/**
	 * Gets the create page.
	 * 
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_CREATE }, method = RequestMethod.GET)
	public String showCreateRolePlayPage(@PathVariable Long roleplayId, Model model) {

		Roleplay roleplay = new Roleplay().getModelObject(Roleplay.class, roleplayId);

		// Create new form bean with default values.
		AuthorCreateRoleplayFormBean authorCreateRoleplayFormBean = new AuthorCreateRoleplayFormBean(
				roleplay);

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("authorCreateRoleplayFormBean",
				authorCreateRoleplayFormBean);

		return "authoring/roleplay/createRoleplay.jsp";
	}
	

	/*
	 * Puts the new roleplay up to the server.
	 * 
	 * @param authorCreateRoleplayFormBean
	 * @param roleplayId
	 * @param bindingResult
	 * @param principal
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_CREATE }, method = RequestMethod.POST)
	public String handleCreateRolePlay(Principal principal,
			@ModelAttribute("authorCreateRoleplayFormBean") @Valid AuthorCreateRoleplayFormBean authorCreateRoleplayFormBean,
			Model model) {

		Long roleplayId = new Long(0);
		
		System.out.println("******777777777777777*****8");
		Roleplay roleplay = new Roleplay();

		if (!((roleplayId == null) || (roleplayId.intValue() == 0))) {
			roleplay = roleplay.getById(roleplayId);
		}

		//roleplay.setRoleplayName("test");

		roleplay.createWithStarterObjects(getSessionInfoBean());

		getSessionInfoBean().setRoleplayId(roleplay.getId());
		getSessionInfoBean().setRoleplayName(roleplay.getRoleplayName());

		Person.saveLastRolePlayEdited((String) principal.getName(),
				roleplay.getId());

		model.addAttribute("roleplay", roleplay);
		//model.addAttribute("authorCreateRoleplayFormBean",
		//		authorCreateRoleplayFormBean);

		return "authoring/index.jsp";
		//return "redirect:/authoring/roleplay/create/" + roleplay.getId();

	}

	/**
	 * 
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_SELECT_GET }, method = RequestMethod.GET)
	public String selectRoleplay(Model model) {

		model.addAttribute("allRoleplays", new Roleplay().getAll());
		return "authoring/selectRoleplay.jsp";
	}

	/**
	 * 
	 * @param roleplayId
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_CHANGE }, method = RequestMethod.GET)
	public String changeRoleplay(@PathVariable Long roleplayId, Model model) {

		this.getSessionInfoBean().setRoleplayId(roleplayId);
		this.getSessionInfoBean().setActorId(null);
		this.getSessionInfoBean().setPhaseId(
				new Phase().getFirstForRoleplay(roleplayId));

		model.addAttribute("allRoleplays", new Roleplay().getAll());
		return "authoring/selectRoleplay.jsp";

	}

	/**
	 * Gets the publish page.
	 * 
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PUBLISH }, method = RequestMethod.GET)
	public String showPublishRolePlayPage(@PathVariable Long id, Model model) {

		Roleplay roleplay = new Roleplay().getModelObject(Roleplay.class, id);

		// Publish new form bean with default values.
		AuthorPublishRoleplayFormBean authorPublishRoleplayFormBean = new AuthorPublishRoleplayFormBean(
				roleplay);

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("authorPublishRoleplayFormBean",
				authorPublishRoleplayFormBean);

		return "authoring/roleplay/publishRoleplay.jsp";
	}

	/**
	 * Puts the new roleplay up to the server.
	 * 
	 * @param roleplay
	 * @param bindingResult
	 * @param principal
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PUBLISH }, method = RequestMethod.POST)
	public String publishRolePlay(
			@ModelAttribute("authorPublishRoleplayFormBean") @Valid AuthorPublishRoleplayFormBean authorPublishRoleplayFormBean,
			@PathVariable Long id, BindingResult bindingResult,
			Principal principal, Model model) {

		Roleplay roleplay = new Roleplay();

		if (!((id == null) || (id.intValue() == 0))) {
			roleplay = roleplay.getById(id);
		}

		boolean saveChanges = false;

		if (authorPublishRoleplayFormBean.getMakeTestableButton() != null) {
			roleplay.setPublishedState(Roleplay.READY_FOR_TESTING);
			roleplay.setMadeTestableDate(new Date());
			saveChanges = true;
		}

		if (authorPublishRoleplayFormBean.getPublishButton() != null) {
			roleplay.setPublishedState(Roleplay.PUBLISHED);
			roleplay.setPublishedDate(new Date());
			saveChanges = true;
		}

		if (saveChanges) {
			roleplay.save();
			getSessionInfoBean().setRoleplayId(roleplay.getId());
			getSessionInfoBean().setRoleplayName(roleplay.getRoleplayName());

			Person.saveLastRolePlayEdited((String) principal.getName(),
					roleplay.getId());
		}

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("authorPublishRoleplayFormBean",
				authorPublishRoleplayFormBean);

		return "redirect:/authoring/roleplay/publish/" + roleplay.getId();

	}

	/**
	 * Gets the Enter Objectives Page
	 * 
	 * @param roleplayId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_OBJECTIVES_GET }, method = RequestMethod.GET)
	public String getEnterObjectivesPage(@PathVariable Long roleplayId,
			Model model) {
		
		Roleplay roleplay = new Roleplay();
		
		if ((roleplayId != null) && (new Long(roleplayId).intValue() > 0)){
			roleplay = new Roleplay().getById(roleplayId);
			getSessionInfoBean().setRoleplayId(roleplay.getId());
		}

		model.addAttribute("roleplay", roleplay);
		
		return "authoring/roleplay/enterObjectives.jsp";

	}

	/**
	 * Handles post and cancel on the Enter Objectives Page
	 * 
	 * @param request
	 * @param roleplayId
	 * @param roleplay
	 * @param bindingResult
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_OBJECTIVES_POST }, method = RequestMethod.POST)
	public String enterObjectivesPost(HttpServletRequest request,
			@PathVariable String roleplayId,
			@ModelAttribute("roleplay") Roleplay roleplay,
			BindingResult bindingResult) {

		System.out
				.println("enterSave was " + request.getParameter("enterSave"));

		if (request.getParameter("cancel") != null) {
			System.out.println("cancel was " + request.getParameter("cancel"));
			return "authoring/index.jsp";
		}

		// Personally, I think its kind of lame that I have to retrieve this
		// before I save it, but I
		// can't get it to work any other way. The 'roleplay' we have is not
		// associated with hibernate, and even
		// overriding its equals method does not help.
		System.out.println("Save called ");
		Roleplay roleplay2 = new Roleplay().getById(new Long(roleplayId));
		roleplay2.setObjectives(roleplay.getObjectives());
		roleplay2.save();
		return "authoring/roleplay/enterObjectives.jsp";
	}

	/**
	 * Gets the Enter Audience Page
	 * 
	 * @param roleplayId
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_AUDIENCE_GET }, method = RequestMethod.GET)
	public String getEnterAudiencePage(@PathVariable String roleplayId,
			Model  model) {
		Roleplay roleplay = new Roleplay();
		
		if ((roleplayId != null) && (new Long(roleplayId).intValue() > 0)){
			roleplay = new Roleplay().getById(new Long(roleplayId));
		}
		
		model.addAttribute("roleplay", roleplay);

		return "authoring/roleplay/enterAudience.jsp";

	}

	/**
	 * Handles post and cancel on the Enter Audience Page
	 * 
	 * @param request
	 * @param roleplayId
	 * @param roleplay
	 * @param bindingResult
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_AUDIENCE_POST }, method = RequestMethod.POST)
	public String enterAudiencePost(HttpServletRequest request,
			@PathVariable String roleplayId,
			@ModelAttribute("roleplay") Roleplay roleplay,
			BindingResult bindingResult) {

		if (request.getParameter("cancel") != null) {
			return "authoring/index.jsp";
		}

		Roleplay roleplay2 = new Roleplay().getById(new Long(roleplayId));
		roleplay2.setAudience(roleplay.getAudience());
		roleplay2.save();
		return "authoring/roleplay/enterAudience.jsp";
	}


	/**
	 * Gets the create/edit Actor page.
	 * 
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_ACTOR_CREATE }, method = RequestMethod.GET)
	public String createActorGet(@PathVariable Long roleplayId,
			@PathVariable Long actorId, Model model) {
		// public static final String A_ROLEPLAY_ACTOR_CREATE = "roleplay/{roleplayId}/actor/create/{actorId}"
		Actor actor = new Actor().getModelObject(Actor.class, actorId);

		model.addAttribute("actor", actor);
		model.addAttribute("authorCreateActorFormBean",
				new AuthorCreateActorFormBean(actor));
		model.addAttribute("actorsForThisRoleplay", getActorsForRolePlay(roleplayId));

		assignRoleTypeConstants(model);

		return "authoring/actor/createActor.jsp";
	}

	/**
	 * 
	 * @param roleplayId
	 * @return List of actors for the Roleplay
	 */
	public List<Actor> getActorsForRolePlay(Long roleplayId) {
		return new Actor().getAllForRoleplay(roleplayId);
	}

	/**
	 * Puts the new Actor onto the server.
	 * 
	 * @param roleplayId
	 * @param actorId
	 * @param model
	 *            Model to hold objects for the view.
	 * @param authorCreateActorFormBean
	 * @param bindingResult
	 * @param actorImage
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_ACTOR_CREATE }, method = RequestMethod.POST)
	public String createActorPost(
			@PathVariable Long roleplayId,
			@PathVariable Long actorId,
			Model model,
			@ModelAttribute("authorCreateActorFormBean") @Valid AuthorCreateActorFormBean authorCreateActorFormBean,
			BindingResult bindingResult,
			@RequestParam(value = "actorImage", required = false) MultipartFile actorImage) {

		if (bindingResult.hasErrors()) {
			return "/authoring/actor/createActor.jsp";
		}

		Actor actor = new Actor();

		if (!((actorId == null) || (actorId.intValue() == 0))) {
			actor = actor.getById(actorId);
		}

		authorCreateActorFormBean.setRoleplayId(roleplayId);

		BeanUtils.copyProperties(authorCreateActorFormBean, actor);

		actor.save();

		if (!actorImage.isEmpty()) {
			actor.setActorImageName(actorImage.getOriginalFilename());
			MediaObject mo = new MediaObject();

			mo.setActorId(actor.getId());
			mo.save();
			try {
				mo.saveContent(actorImage.getBytes());
			} catch (Exception e) {
				System.out.println("problem with setting content on mo. "
						+ e.getMessage());
			}

		}

		model.addAttribute("actorsForThisRoleplay", getActorsForRolePlay(roleplayId));
		assignRoleTypeConstants(model);

		return "redirect:/authoring/roleplay/" + roleplayId + "/actor/create/"
				+ actor.getId();

	}

	/**
	 * 
	 * @param actor
	 * @param bindingResult
	 * @param actorImage
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_ACTOR_EDIT }, method = RequestMethod.POST)
	public String editActorPost(
			@Valid Actor actor,
			BindingResult bindingResult,
			@RequestParam(value = "actorImage", required = false) MultipartFile actorImage,
			Model model) {

		return "authoring/actor/createActor.jsp";
	}

	/**
	 * Gets the create Phase page.
	 * 
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PHASE_CREATE }, method = RequestMethod.GET)
	public String createPhaseGet(@PathVariable Long roleplayId,
			@PathVariable Long phaseId, Model model) {

		Phase phase = new Phase().getModelObject(Phase.class, phaseId);

		model.addAttribute("phase", phase);
		model.addAttribute("authorCreatePhaseFormBean",
				new AuthorCreatePhaseFormBean(phase));
		model.addAttribute("phasesForThisRoleplay",
				new Phase().getAllForRoleplay(roleplayId));

		return "authoring/phase/createPhase.jsp";
	}

	/**
	 * Puts the new Phase onto the server.
	 * 
	 * @param phase
	 * @param bindingResult
	 * @param phaseImage
	 * @param mav
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PHASE_CREATE }, method = RequestMethod.POST)
	public String createPhasePost(
			@PathVariable Long roleplayId,
			@PathVariable Long phaseId,
			Model model,
			@ModelAttribute("authorCreatePhaseFormBean") @Valid AuthorCreatePhaseFormBean authorCreatePhaseFormBean,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/authoring/phase/createPhase.jsp";
		}

		Phase phase = new Phase();

		if (!((phaseId == null) || (phaseId.intValue() == 0))) {
			phase = phase.getById(phaseId);
		}

		authorCreatePhaseFormBean.setRoleplayId(roleplayId);

		BeanUtils.copyProperties(authorCreatePhaseFormBean, phase);

		phase.save();

		model.addAttribute("phasesForThisRoleplay",
				new Phase().getAllForRoleplay(roleplayId));

		// Return them to the clean page (no phase selected) in case they want
		// to create a new phase.
		return "redirect:/authoring/roleplay/" + roleplayId + "/phase/create/0";

	}

	/**
	 * 
	 * @param phaseId
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PHASE_CREATE_SUCCESS }, method = RequestMethod.GET)
	public String createPhaseSuccess(@PathVariable Long phaseId, Model model) {

		List<Phase> thePhases = new Phase()
				.getAllForRoleplay(getSessionInfoBean().getRoleplayId());

		for (Phase act : thePhases) {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa:      "
					+ act.getPhaseName());
		}
		model.addAttribute("phases", thePhases);

		/*
		 * if (id != null){ model.addAttribute("phase", new Phase().getById(new
		 * Long(id))); } else { model.addAttribute("phase", new Phase()); }
		 */
		return "/authoring/phase/createPhaseSuccess.jsp";
	}

	/**
	 * 
	 * @param phase
	 * @param bindingResult
	 * @param phaseImage
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PHASE_EDIT }, method = RequestMethod.POST)
	public String editPhasePost(
			@Valid Phase phase,
			BindingResult bindingResult,
			@RequestParam(value = "phaseImage", required = false) MultipartFile phaseImage,
			Model model) {

		return "authoring/phase/createPhase.jsp";
	}

	/**
	 * 
	 * @param roleplayId
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PP_UNIVERSAL }, method = RequestMethod.GET)
	public String placePluginGetUniversal(@PathVariable("roleplayId") Long roleplayId) {

		System.out.println("Redirection to Specific. Roleplay id was " + roleplayId);

		return "redirect:/authoring/roleplay/" + roleplayId + "/actor/0/phase/0/pluginPlacement";
	}

	/**
	 * 
	 * @param roleplayId
	 * @param aId
	 * @param phId
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PP_SPECIFIC }, method = RequestMethod.GET)
	public String placePluginSpecificGet(@PathVariable("roleplayId") Long roleplayId,
			@PathVariable("actorId") Long actorId, @PathVariable("phaseId") Long phaseId,
			Model model) {

		System.out.println("id was " + roleplayId);

		// Setting id to 0 to indicate that this is for all actors, likewise for
		// phase
		Actor actor = new Actor().getModelObject(Actor.class, actorId);
		Phase phase = new Phase().getModelObject(Phase.class, phaseId);

		model.addAttribute("actor", actor);
		model.addAttribute("actorId", actor.getId());
		model.addAttribute("phase", phase);

		addModelObjectsOnPlacePluginsPage(model);

		return "authoring/pluginPlacement/placePlugin.jsp";
	}

	/**
	 * Adds a plugin at/to the phase and actor that the author was looking at.
	 * 
	 * @param authorAddPluginFormBean
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PLUGIN_CREATE }, method = RequestMethod.POST)
	public String addPlugin(AuthorAddPluginFormBean authorAddPluginFormBean,
			Model model) {

		Long roleplayId = getSessionInfoBean().getRoleplayId();
		Long aId = getSessionInfoBean().getActorId();
		Long phId = getSessionInfoBean().getPhaseId();

		Plugin plugin = Plugin.getPluginCopyForRoleplay(roleplayId,
				authorAddPluginFormBean.getRawPluginId());

		// If Actor Id was 0, we want this applied to all actors
		if ((new Long(0).compareTo(aId)) == 0) {
			List<Actor> allActors = new Actor().getAllForRoleplay(roleplayId);
			for (Actor actor : allActors) {
				PluginPointer pp = new PluginPointer(roleplayId, actor.getId(), phId,
						authorAddPluginFormBean.getPluginHeading(),
						plugin.getId());

				pp.setAddedAsUniversal(true);
				pp.save();

			}

			// Create a pointer to indicate this was added as universal
			@SuppressWarnings("unused")
			PluginPointer pluginPointer = new PluginPointer(roleplayId, phId,
					authorAddPluginFormBean.getPluginHeading(), plugin.getId(),
					true);
		} else {
			// Create pluginPointer for the specifc actor on hand.
			@SuppressWarnings("unused")
			PluginPointer pluginPointer = new PluginPointer(roleplayId, aId, phId,
					authorAddPluginFormBean.getPluginHeading(), plugin.getId());
		}

		return "redirect:/authoring/roleplay/" + roleplayId + "/pluginPlacement";
	}

	/**
	 * A centralized place to add model objects to the complex plugin placement
	 * page.
	 * 
	 * @param model
	 *            Model to hold objects for the view. Model to hold objects for
	 *            the view.
	 */
	public void addModelObjectsOnPlacePluginsPage(Model model) {

		Long roleplayId = getSessionInfoBean().getRoleplayId();
		Long aId = getSessionInfoBean().getActorId();
		Long phId = getSessionInfoBean().getPhaseId();

		System.out.println(getSessionInfoBean().toString());

		if (roleplayId == null) {
			roleplayId = new Long(0);
			System.out.println("rpids null");
		}
		if (aId == null) {
			aId = new Long(0);
			System.out.println("a ids null");
		}
		if (phId == null) {
			phId = new Long(0);
			System.out.println("phids null");
		}

		model.addAttribute("pluginPointers",
				new PluginPointer().getCurrentSet(roleplayId, aId, phId));
		model.addAttribute("actorsForThisRoleplay", getActorsForRolePlay(roleplayId));

		model.addAttribute("phasesForThisRoleplay",
				new Phase().getAllForRoleplay(roleplayId));
		model.addAttribute("authorAddPluginFormBean",
				new AuthorAddPluginFormBean());

		List<Plugin> thePlugins = new Plugin().getAllUncustomized();
		model.addAttribute("plugins", thePlugins);
	}

	/**
	 * Changes the actor on the scratch pad, and returns user to the same page.
	 * 
	 * @param request
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PP_CHANGE_ACTOR }, method = RequestMethod.GET)
	public String placePluginChangeActor(HttpServletRequest request,
			Model model, @PathVariable("actorId") Long actorId) {

		getSessionInfoBean().setActorId(actorId);

		addModelObjectsOnPlacePluginsPage(model);

		return "redirect:/authoring/roleplay/"
				+ getSessionInfoBean().getRoleplayId() + "/actor/" + actorId + "/phase/"
				+ getSessionInfoBean().getPhaseId() + "/pluginPlacement";
	}

	/**
	 * Changes the phase on the scratch pad, and returns user to the same page.
	 * 
	 * @param request
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PP_CHANGE_PHASE }, method = RequestMethod.GET)
	public String placePluginChangePhase(HttpServletRequest request,
			Model model, @PathVariable("pId") Long pId) {

		getSessionInfoBean().setPhaseId(pId);

		addModelObjectsOnPlacePluginsPage(model);

		return "redirect:/authoring/roleplay/"
				+ getSessionInfoBean().getRoleplayId() + "/actor/"
				+ getSessionInfoBean().getActorId() + "/phase/" + pId
				+ "/pluginPlacement";
	}

	/**
	 * 
	 * @param pluginId
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_CUSTOMIZE_PLUGIN_GET }, method = RequestMethod.GET)
	public String showCustomizePluginPage(
			@PathVariable("pluginId") Long pluginId, Model model) {

		Plugin plugin = new Plugin().getById(pluginId);

		model.addAttribute("plugin", plugin);

		// Check to see if it has any documents associated with it.
		List<PluginObjectDocument> pluginObjectDocuments = new PluginObjectDocument()
				.getAllForPlugin(plugin.getId());

		model.addAttribute("pluginObjectDocuments", pluginObjectDocuments);

		for (PluginObjectDocument pod : pluginObjectDocuments) {
			model.addAttribute("pod_" + pod.getId(), pod);
			AuthCustomizePluginDocumentFormBean acpdfb = new AuthCustomizePluginDocumentFormBean(
					pod);
			model.addAttribute("acpdfb_" + pod.getId(), pod);
		}

		model.addAttribute(
				"allDocumentsForThisRoleplay",
				new PluginObjectDocument()
						.getAllForRoleplay(getSessionInfoBean().getRoleplayId()));

		return "/authoring/pluginPlacement/customizePlugin.jsp";
	}

	/**
	 * 
	 * @param pluginId
	 * @param podId
	 * @param authCustomizePluginDocumentFormBean
	 * @param model
	 *            Model to hold objects for the view.
	 * @return path to JSP.
	 */
	@RequestMapping(value = { CMC.A_ROLEPLAY_PLUGIN_CUST_DOC }, method = RequestMethod.POST)
	public String modifyDocumentPost(
			@PathVariable("pluginId") Long pluginId,
			@PathVariable("podId") Long podId,
			AuthCustomizePluginDocumentFormBean authCustomizePluginDocumentFormBean,
			Model model) {
		PluginObjectDocument pod = new PluginObjectDocument().getById(podId);

		BeanUtils.copyProperties(authCustomizePluginDocumentFormBean, pod);

		pod.save();

		return "redirect:/authoring/pluginPlacement/customizePlugin/plugin/"
				+ pluginId;
	}

}
