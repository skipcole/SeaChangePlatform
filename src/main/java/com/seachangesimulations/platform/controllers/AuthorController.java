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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	 * @return
	 */
	@RequestMapping(value = {CMC.INDEX }, method = RequestMethod.GET)
	public String showAuthoringPage(Map<String, Object> model) {

		getSessionInfoBean().setPlatformZone(SessionInfoBean.AUTHOR_ZONE);
		return "authoring/index.jsp";
	}
	
	@RequestMapping(value = {"index/setZone" }, method = RequestMethod.GET)
	public String setAuthorZone(Map<String, Object> model) {

		getSessionInfoBean().setPlatformZone(SessionInfoBean.AUTHOR_ZONE);
		return "redirect:/authoring/index";
	}

	/**
	 * Gets the create page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {CMC.A_ROLEPLAY_CREATE}, method = RequestMethod.GET)
	public String showCreateRolePlayPage(@PathVariable Long id, Model model) {

		Roleplay roleplay = new Roleplay().getModelObject(Roleplay.class, id);

		// Create new form bean with default values.
		AuthorCreateRoleplayFormBean authorCreateRoleplayFormBean = new AuthorCreateRoleplayFormBean(roleplay);

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("authorCreateRoleplayFormBean", authorCreateRoleplayFormBean);

		return "authoring/roleplay/createRoleplay.jsp";
	}

	/**
	 * Puts the new roleplay up to the server.
	 * 
	 * @param roleplay
	 * @param bindingResult
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = {CMC.A_ROLEPLAY_CREATE}, method = RequestMethod.POST)
	public String handleCreateRolePlay(
			@ModelAttribute("authorCreateRoleplayFormBean") @Valid AuthorCreateRoleplayFormBean authorCreateRoleplayFormBean,
			@PathVariable Long id, BindingResult bindingResult, Principal principal, Model model) {

		Roleplay roleplay = new Roleplay();

		if (!((id == null) || (id.intValue() == 0))) {
			roleplay = roleplay.getById(id);
		}

		BeanUtils.copyProperties(authorCreateRoleplayFormBean, roleplay);

		roleplay.createWithStarterObjects(getSessionInfoBean());

		getSessionInfoBean().setRoleplayId(roleplay.getId());
		getSessionInfoBean().setRoleplayName(roleplay.getRoleplayName());

		Person.saveLastRolePlayEdited((String) principal.getName(), roleplay.getId());

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("authorCreateRoleplayFormBean", authorCreateRoleplayFormBean);

		return "redirect:/authoring/roleplay/create/" + roleplay.getId();

	}
	
	@RequestMapping(value = { "selectRoleplay" }, method = RequestMethod.GET)
	public String selectRoleplay(Model model) {

		model.addAttribute("allRoleplays", new Roleplay().getAll());
		return "authoring/selectRoleplay.jsp";
	}
	
	@RequestMapping(value = { "changeRoleplay/{id}" }, method = RequestMethod.GET)
	public String changeRoleplay(@PathVariable Long id, Model model) {

		this.getSessionInfoBean().setRoleplayId(id);
		this.getSessionInfoBean().setActorId(null);
		this.getSessionInfoBean().setPhaseId(new Phase().getFirstForRoleplay(id));
		
		model.addAttribute("allRoleplays", new Roleplay().getAll());
		return "authoring/selectRoleplay.jsp";
		
	}

	/**
	 * Gets the publish page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {CMC.A_ROLEPLAY_PUBLISH}, method = RequestMethod.GET)
	public String showPublishRolePlayPage(@PathVariable Long id, Model model) {

		Roleplay roleplay = new Roleplay().getModelObject(Roleplay.class, id);

		// Publish new form bean with default values.
		AuthorPublishRoleplayFormBean authorPublishRoleplayFormBean = new AuthorPublishRoleplayFormBean(roleplay);

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("authorPublishRoleplayFormBean", authorPublishRoleplayFormBean);

		return "authoring/roleplay/publishRoleplay.jsp";
	}

	/**
	 * Puts the new roleplay up to the server.
	 * 
	 * @param roleplay
	 * @param bindingResult
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = {CMC.A_ROLEPLAY_PUBLISH}, method = RequestMethod.POST)
	public String publishRolePlay(
			@ModelAttribute("authorPublishRoleplayFormBean") @Valid AuthorPublishRoleplayFormBean authorPublishRoleplayFormBean,
			@PathVariable Long id, BindingResult bindingResult, Principal principal, Model model) {

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

			Person.saveLastRolePlayEdited((String) principal.getName(), roleplay.getId());
		}

		model.addAttribute("roleplay", roleplay);
		model.addAttribute("authorPublishRoleplayFormBean", authorPublishRoleplayFormBean);

		return "redirect:/authoring/roleplay/publish/" + roleplay.getId();

	}

	/*
	 * ********************************* Update
	 * *********************************
	 */

	/** Gets the Enter Objectives Page */
	@RequestMapping(value = { "rolePlay/enterObjectives/{id}" }, method = RequestMethod.GET)
	public ModelAndView getEnterObjectivesPage(@PathVariable String id, ModelAndView mav) {
		Roleplay roleplay = new Roleplay().getById(new Long(id));
		mav.getModelMap().addAttribute("roleplay", roleplay);
		mav.setViewName("authoring/roleplay/enterObjectives.jsp");

		return mav;

	}

	/** Handles post and cancel on the Enter Objectives Page */
	@RequestMapping(value = { "rolePlay/enterObjectives/{id}" }, method = RequestMethod.POST)
	public String enterObjectivesPost(HttpServletRequest request, @PathVariable String id,
			@ModelAttribute("roleplay") Roleplay roleplay, BindingResult bindingResult) {

		System.out.println("enterSave was " + request.getParameter("enterSave"));

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
		Roleplay roleplay2 = new Roleplay().getById(new Long(id));
		roleplay2.setObjectives(roleplay.getObjectives());
		roleplay2.save();
		return "authoring/roleplay/enterObjectives.jsp";
	}

	/** Gets the Enter Audience Page */
	@RequestMapping(value = { "rolePlay/enterAudience/{id}" }, method = RequestMethod.GET)
	public ModelAndView getEnterAudiencePage(@PathVariable String id, ModelAndView mav) {
		Roleplay roleplay = new Roleplay().getById(new Long(id));
		mav.getModelMap().addAttribute("roleplay", roleplay);
		mav.setViewName("authoring/roleplay/enterAudience.jsp");

		return mav;

	}

	/** Handles post and cancel on the Enter Audience Page */
	@RequestMapping(value = { "rolePlay/enterAudience/{id}" }, method = RequestMethod.POST)
	public String enterAudiencePost(HttpServletRequest request, @PathVariable String id,
			@ModelAttribute("roleplay") Roleplay roleplay, BindingResult bindingResult) {

		System.out.println("enterSave was " + request.getParameter("enterSave"));

		if (request.getParameter("cancel") != null) {
			return "authoring/index.jsp";
		}

		Roleplay roleplay2 = new Roleplay().getById(new Long(id));
		roleplay2.setAudience(roleplay.getAudience());
		roleplay2.save();
		return "authoring/roleplay/enterAudience.jsp";
	}

	/**
	 * Gets the create/edit Actor page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "rpId/{rpId}/actor/create/{id}" }, method = RequestMethod.GET)
	public String createActorGet(@PathVariable Long rpId, @PathVariable Long id, Model model) {

		Actor actor = new Actor().getModelObject(Actor.class, id);

		model.addAttribute("actor", actor);
		model.addAttribute("authorCreateActorFormBean", new AuthorCreateActorFormBean(actor));
		model.addAttribute("actorsForThisRoleplay", getActorsForRolePlay(rpId));
		
		assignRoleTypeConstants(model);

		return "authoring/actor/createActor.jsp";
	}
	
	public List<Actor> getActorsForRolePlay(Long rpId){
		return  new Actor().getAllForRoleplay(rpId);
	}

	/**
	 * Puts the new Actor onto the server.
	 * 
	 * @param authorCreateActorFormBean
	 * @param bindingResult
	 * @param actorImage
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = { "rpId/{rpId}/actor/create/{id}" }, method = RequestMethod.POST)
	public String createActorPost(@PathVariable Long rpId, @PathVariable Long id, Model model,
			@ModelAttribute("authorCreateActorFormBean") @Valid AuthorCreateActorFormBean authorCreateActorFormBean,
			BindingResult bindingResult, @RequestParam(value = "actorImage", required = false) MultipartFile actorImage) {

		if (bindingResult.hasErrors()) {
			return "/authoring/actor/createActor.jsp";
		}

		Actor actor = new Actor();

		if (!((id == null) || (id.intValue() == 0))) {
			actor = actor.getById(id);
		}

		authorCreateActorFormBean.setRoleplayId(rpId);

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
				System.out.println("problem with setting content on mo. " + e.getMessage());
			}

		}

		model.addAttribute("actorsForThisRoleplay", getActorsForRolePlay(rpId));
		assignRoleTypeConstants(model);

		return "redirect:/authoring/rpId/" + rpId + "/actor/create/" + actor.getId();

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

	@RequestMapping(value = { "actor/edit/{id}" }, method = RequestMethod.POST)
	public String editActorPost(@Valid Actor actor, BindingResult bindingResult,
			@RequestParam(value = "actorImage", required = false) MultipartFile actorImage, Model model) {

		return "authoring/actor/createActor.jsp";
	}

	/**
	 * Gets the create Phase page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "rpId/{rpId}/phase/create/{id}" }, method = RequestMethod.GET)
	public String createPhaseGet(@PathVariable Long rpId, @PathVariable Long id, Model model) {

		Phase phase = new Phase().getModelObject(Phase.class, id);

		model.addAttribute("phase", phase);
		model.addAttribute("authorCreatePhaseFormBean", new AuthorCreatePhaseFormBean(phase));
		model.addAttribute("phasesForThisRoleplay", new Phase().getAllForRoleplay(rpId));

		return "authoring/phase/createPhase.jsp";
	}

	/**
	 * Puts the new Phase onto the server.
	 * 
	 * @param phase
	 * @param bindingResult
	 * @param phaseImage
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = { "rpId/{rpId}/phase/create/{id}" }, method = RequestMethod.POST)
	public String createPhasePost(@PathVariable Long rpId, @PathVariable Long id, Model model,
			@ModelAttribute("authorCreatePhaseFormBean") @Valid AuthorCreatePhaseFormBean authorCreatePhaseFormBean,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/authoring/phase/createPhase.jsp";
		}

		Phase phase = new Phase();

		if (!((id == null) || (id.intValue() == 0))) {
			phase = phase.getById(id);
		}

		authorCreatePhaseFormBean.setRoleplayId(rpId);

		BeanUtils.copyProperties(authorCreatePhaseFormBean, phase);

		phase.save();

		model.addAttribute("phasesForThisRoleplay", new Phase().getAllForRoleplay(rpId));

		// Return them to the clean page (no phase selected) in case they want to create a new phase.
		return "redirect:/authoring/rpId/" + rpId + "/phase/create/0";

	}

	@RequestMapping(value = { "phase/createPhaseSuccess/{id}" }, method = RequestMethod.GET)
	public String createPhaseSuccess(@PathVariable Long id, Model model) {

		List<Phase> thePhases = new Phase().getAllForRoleplay(getSessionInfoBean().getRoleplayId());

		for (Phase act : thePhases) {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa:      " + act.getPhaseName());
		}
		model.addAttribute("phases", thePhases);

		/*
		 * if (id != null){ model.addAttribute("phase", new Phase().getById(new
		 * Long(id))); } else { model.addAttribute("phase", new Phase()); }
		 */
		return "/authoring/phase/createPhaseSuccess.jsp";
	}

	@RequestMapping(value = { "phase/edit/{id}" }, method = RequestMethod.POST)
	public String editPhasePost(@Valid Phase phase, BindingResult bindingResult,
			@RequestParam(value = "phaseImage", required = false) MultipartFile phaseImage, Model model) {

		return "authoring/phase/createPhase.jsp";
	}

	@RequestMapping(value = { "rpId/{rpId}/pluginPlacement" }, method = RequestMethod.GET)
	public String placePluginGetUniversal(@PathVariable("rpId") Long rpId, Model model) {

		System.out.println("id was " + rpId);

		//addModelObjectsOnPlacePluginsPage(model, rpId);

		return "redirect:/authoring/rpId/" + rpId + "/a/0/ph/0/pluginPlacement";
	}

	@RequestMapping(value = { "rpId/{rpId}/a/{aId}/ph/{phId}/pluginPlacement" }, method = RequestMethod.GET)
	public String placePluginSpecificGet(@PathVariable("rpId") Long rpId, @PathVariable("aId") Long aId,
			@PathVariable("phId") Long phId, Model model) {

		System.out.println("id was " + rpId);

		

		// Setting id to 0 to indicate that this is for all actors, likewise for phase
		Actor actor = new Actor().getModelObject(Actor.class, aId);
		Phase phase = new Phase().getModelObject(Phase.class, phId);

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
	 * @return
	 */
	@RequestMapping(value = { "pluginPlacement/addPlugin" }, method = RequestMethod.POST)
	public String addPlugin(AuthorAddPluginFormBean authorAddPluginFormBean, Model model) {

		Long rpId = getSessionInfoBean().getRoleplayId();
		Long aId = getSessionInfoBean().getActorId();
		Long phId = getSessionInfoBean().getPhaseId();
		
		Plugin plugin = Plugin.getPluginCopyForRoleplay(rpId, authorAddPluginFormBean.getRawPluginId());

		// If Actor Id was 0, we want this applied to all actors
		if ((new Long(0).compareTo(aId)) == 0) {
			List <Actor> allActors = new Actor().getAllForRoleplay(rpId);
			for (Actor actor : allActors) {
				PluginPointer pp = new PluginPointer(rpId, actor.getId(), phId,
						authorAddPluginFormBean.getPluginHeading(), plugin.getId());
				
				pp.setAddedAsUniversal(true);
				pp.save();
				
			}
			
			// Create a pointer to indicate this was added as universal
			PluginPointer pp = new PluginPointer(rpId, phId, authorAddPluginFormBean.getPluginHeading(),
					plugin.getId(), true);
		} else {
			// Create pluginPointer for the specifc actor on hand.
			PluginPointer pp = new PluginPointer(rpId, aId, phId, authorAddPluginFormBean.getPluginHeading(),
					plugin.getId());
		}

		return "redirect:/authoring/rpId/" + rpId + "/pluginPlacement";
	}

	/*
	 * A centralized place to add model objects to the complex plugin placement
	 * page.
	 */
	/**
	 * 
	 * @param model
	 * @param rpId
	 */
	public void addModelObjectsOnPlacePluginsPage(Model model) {

		Long rpId = getSessionInfoBean().getRoleplayId();
		Long aId = getSessionInfoBean().getActorId();
		Long phId = getSessionInfoBean().getPhaseId();

		model.addAttribute("pluginPointers", new PluginPointer().getCurrentSet(rpId, aId, phId));
		model.addAttribute("actorsForThisRoleplay", getActorsForRolePlay(rpId));
		
		model.addAttribute("phasesForThisRoleplay", new Phase().getAllForRoleplay(rpId));
		model.addAttribute("authorAddPluginFormBean", new AuthorAddPluginFormBean());
		
		List<Plugin> thePlugins = new Plugin().getAllUncustomized();
		model.addAttribute("plugins", thePlugins);
	}

	/**
	 * Changes the actor on the scratch pad, and returns user to the same page.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "pluginPlacement/changeActor" }, method = RequestMethod.POST)
	public String placePluginChangeActor(HttpServletRequest request, Model model,
			@ModelAttribute("actor") Actor actor ) {

		getSessionInfoBean().setActorId(actor.getId());
		
		addModelObjectsOnPlacePluginsPage(model);

		return "redirect:/authoring/rpId/" + getSessionInfoBean().getRoleplayId() + "/a/" + 
			actor.getId() + "/ph/" + getSessionInfoBean().getPhaseId() + "/pluginPlacement";
	}

	/**
	 * Changes the phase on the scratch pad, and returns user to the same page.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "pluginPlacement/changePhase" }, method = RequestMethod.POST)
	public String placePluginChangePhase(HttpServletRequest request, Model model,
			@ModelAttribute("phase") Phase phase) {

		getSessionInfoBean().setPhaseId(phase.getId());

		addModelObjectsOnPlacePluginsPage(model);

		return "redirect:/authoring/rpId/" + getSessionInfoBean().getRoleplayId() + "/a/" + getSessionInfoBean().getActorId() + "/ph/" + phase.getId() + "/pluginPlacement";
	}

	@RequestMapping(value = { "pluginPlacement/customizePlugin/plugin/{pId}" }, method = RequestMethod.GET)
	public String showCustomizePluginPage(@PathVariable("pId") Long pId, Model model) {

		System.out.println("pId was " + pId);

		Plugin plugin = new Plugin().getById(pId);

		model.addAttribute("plugin", plugin);
		
		// Check to see if it has any documents associated with it.
		List <PluginObjectDocument> pluginObjectDocuments = new PluginObjectDocument().getAllForPlugin(plugin.getId());
		
		model.addAttribute("pluginObjectDocuments", pluginObjectDocuments);
		
		for (PluginObjectDocument pod : pluginObjectDocuments){
			model.addAttribute("pod_" + pod.getId(), pod);
			AuthCustomizePluginDocumentFormBean acpdfb = new AuthCustomizePluginDocumentFormBean(pod);
			model.addAttribute("acpdfb_" + pod.getId(), pod);
		}
		
		model.addAttribute("allDocumentsForThisRoleplay", new PluginObjectDocument().getAllForRoleplay(getSessionInfoBean().getRoleplayId()));
		
		return "/authoring/pluginPlacement/customizePlugin.jsp";
	}
	
	@RequestMapping(value = { "plugin/{pId}/customizePluginDocument/{podId}" }, method = RequestMethod.POST)
	public String modifyDocumentPost(@PathVariable("pId") Long pId, @PathVariable("podId") Long podId, 
			AuthCustomizePluginDocumentFormBean authCustomizePluginDocumentFormBean,
			Model model) {
		PluginObjectDocument pod = new PluginObjectDocument().getById(podId);

		BeanUtils.copyProperties(authCustomizePluginDocumentFormBean, pod);
		
		pod.save();
		
		return "redirect:/authoring/pluginPlacement/customizePlugin/plugin/" + pId;
	}

}
