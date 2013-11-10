package com.seachangesimulations.platform.mvc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.domain.PluginFile;
import com.seachangesimulations.platform.mvc.formbeans.developer.DevAddObjectsToPluginFormBean;
import com.seachangesimulations.platform.mvc.formbeans.developer.DevDefinePluginFormBean;
import com.seachangesimulations.platform.mvc.formbeans.developer.DevModifyPluginDocumentFormBean;
import com.seachangesimulations.platform.mvc.formbeans.developer.DevUploadPluginFilesFormBean;
import com.seachangesimulations.platform.pluginobjects.PluginObjectAssociation;
import com.seachangesimulations.platform.pluginobjects.PluginObjectDocument;
import com.seachangesimulations.platform.service.SessionInfoBean;


/**
 * This controller handles developer operations.
 * 
 * 
 */
@Controller
@RequestMapping("/developing")
public class DeveloperController extends BaseController {

	/**
	 * Shows the developer control section.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"index" }, method = RequestMethod.GET)
	public String showDevelopingPage(Model model) {

		getSessionInfoBean().setPlatformZone(SessionInfoBean.DEVELOPER_ZONE);
		
		List plugins = new Plugin().getAllUncustomized();
		model.addAttribute("plugins", plugins);

		model.addAttribute("pluginSelectionId", "0");
		model.addAttribute("pluginAction", "1");
		return "developing/index.jsp";
	}
	
	@RequestMapping(value = {"index/setZone" }, method = RequestMethod.GET)
	public String setDeveloperZone(Map<String, Object> model) {

		getSessionInfoBean().setPlatformZone(SessionInfoBean.DEVELOPER_ZONE);
		return "redirect:/developing/index";
	}

	/*
	 * ********************************* Choose Action
	 * *********************************
	 */

	/**
	 * Gets the page to selection action
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "choose" }, method = RequestMethod.POST)
	public String chooseDevelopingTask(Model model, HttpServletRequest request) {

		String pluginId = request.getParameter("pluginSelectionId");

		String choiceOfAction = request.getParameter("pluginAction");

		Plugin plugin = new Plugin();
		DevDefinePluginFormBean devDefinePluginFormBean = new DevDefinePluginFormBean();

		if (pluginId != null) {
			Long longPluginId = new Long(pluginId);
			if (longPluginId.intValue() > 0) {
				plugin = new Plugin().getById(new Long(pluginId));
				devDefinePluginFormBean = new DevDefinePluginFormBean(plugin);
			}
		} else {
			pluginId = "0";
		}

		List plugins = new Plugin().getAllUncustomized();
		model.addAttribute("plugins", plugins);
		model.addAttribute("plugin", plugin);
		model.addAttribute("devDefinePluginFormBean", devDefinePluginFormBean);

		if (choiceOfAction.equalsIgnoreCase("1")) {
			return "redirect:/developing/definePlugin/" + pluginId;
		} else if (choiceOfAction.equalsIgnoreCase("2")) {
			return "redirect:/developing/uploadPluginFiles/" + pluginId;
		} else if (choiceOfAction.equalsIgnoreCase("3")) {
			return "redirect:/developing/addObjectsToPlugin/" + pluginId;
		} else if (choiceOfAction.equalsIgnoreCase("4")) {
			return "redirect:/developing/publishPlugin/" + pluginId;
		}

		System.out.println("warning. should not get here.");
		return "developing/index.jsp";
	}

	/**
	 * This REST method takes the developer to the page to define the plugin, so
	 * they can view and make edits (if necessary) to the plugin definition.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "definePlugin/{id}" }, method = RequestMethod.GET)
	public String definePluginGet(@PathVariable Long id, Model model) {

		Plugin plugin = new Plugin().getModelObject(Plugin.class, id);

		// Create new form bean with default values.
		DevDefinePluginFormBean devDefinePluginFormBean = new DevDefinePluginFormBean(plugin);

		model.addAttribute("plugin", plugin);
		model.addAttribute("devDefinePluginFormBean", devDefinePluginFormBean);

		return "/developing/definePlugin.jsp";

	}

	/**
	 * This REST method uploads the developer's changes to the plugin's
	 * definitions.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "definePlugin/{id}" }, method = RequestMethod.POST)
	public String definePluginPost(
			@ModelAttribute("devDefinePluginFormBean") @Valid DevDefinePluginFormBean devDefinePluginFormBean,
			BindingResult bindingResult, @PathVariable Long id, Model model) {

		if (bindingResult.hasErrors()) {
			return "/developing/definePlugin.jsp";
		}

		Plugin plugin = new Plugin();

		if (!((id == null) || (id.intValue() == 0))) {
			plugin = plugin.getById(id);
		}

		// Copy in properties, set customized to false, since developers make
		// uncustomized plugins for the authors to customize.
		BeanUtils.copyProperties(devDefinePluginFormBean, plugin);
		plugin.setCustomized(false);

		plugin.save();

		model.addAttribute("plugin", plugin);
		model.addAttribute("devDefinePluginFormBean", devDefinePluginFormBean);

		return "redirect:/developing/definePlugin/" + plugin.getId();

	}

	/**
	 * This REST method takes the developer to the page to upload files for the
	 * plugin.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "uploadPluginFiles/{id}" }, method = RequestMethod.GET)
	public String uploadPluginFilesGet(@PathVariable Long id, Model model) {

		Plugin plugin = new Plugin().getModelObject(Plugin.class, id);

		DevUploadPluginFilesFormBean devUploadPluginFilesFormBean = new DevUploadPluginFilesFormBean();

		model.addAttribute("plugin", plugin);
		model.addAttribute("pluginFiles", new PluginFile().getAllForPlugin(id));
		model.addAttribute("devUploadPluginFilesFormBean", devUploadPluginFilesFormBean);

		return "/developing/uploadPluginFiles.jsp";

	}

	/**
	 * This REST method uploads the developer's files to the plugin's
	 * definitions.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "uploadPluginFiles/{id}" }, method = RequestMethod.POST)
	public String uploadPluginFilesPost(@PathVariable Long id,
			@RequestParam(value = "pluginFile", required = false) MultipartFile pluginFile, Model model) {

		/*
		 * if (bindingResult.hasErrors()) { return
		 * "/developing/definePlugin.jsp"; }
		 */

		PluginFile pluginFileObject = new PluginFile();

		pluginFileObject.setPluginId(id);

		DevUploadPluginFilesFormBean devUploadPluginFilesFormBean = new DevUploadPluginFilesFormBean(pluginFileObject);

		model.addAttribute("pluginFiles", new PluginFile().getAllForPlugin(id));
		model.addAttribute("devUploadPluginFilesFormBean", devUploadPluginFilesFormBean);

		boolean fileSaved = pluginProvider.savePluginToFile(pluginFile, pluginFileObject);

		if (fileSaved) {
			return "redirect:/developing/uploadPluginFiles/" + id;
		} else {
			// bindingResult.rejectValue("file", "not uploaded");
			return "/developing/uploadPluginFiles.jsp";
		}

	}

	//

	/**
	 * This REST method takes the developer to the page to define the plugin, so
	 * they can view and make edits (if necessary) to the plugin definition.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "addObjectsToPlugin/{id}" }, method = RequestMethod.GET)
	public String addObjectsToPluginGet(@PathVariable Long id, Model model) {

		Plugin plugin = new Plugin().getModelObject(Plugin.class, id);

		// Create new form bean with default values.
		DevAddObjectsToPluginFormBean devAddObjectsToPluginFormBean = new DevAddObjectsToPluginFormBean(plugin);

		model.addAttribute("plugin", plugin);
		model.addAttribute("devAddObjectsToPluginFormBean", devAddObjectsToPluginFormBean);
		model.addAttribute("pluginObjectAssociations", new PluginObjectAssociation().getAllForPlugin(id));

		return "/developing/addObjectsToPlugin.jsp";

	}

	/**
	 * This method will add an object to a plugin.
	 * 
	 * @param id
	 * @param devAddObjectsToPluginFormBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "addObjectsToPlugin/{id}" }, method = RequestMethod.POST)
	public String addObjectsToPluginPost(
			@PathVariable Long id,
			@ModelAttribute("devAddObjectsToPluginFormBean") @Valid DevAddObjectsToPluginFormBean devAddObjectsToPluginFormBean,
			Model model) {

		Plugin plugin = new Plugin().getModelObject(Plugin.class, id);
		
		System.out.println(devAddObjectsToPluginFormBean.getObjectType());
		
		PluginObjectAssociation pluginObjectAssociation = new PluginObjectAssociation();
		BeanUtils.copyProperties(devAddObjectsToPluginFormBean, pluginObjectAssociation);
		
		if ("PluginObjectDocument".equalsIgnoreCase(devAddObjectsToPluginFormBean.getObjectType())) { 
			PluginObjectDocument pod = new PluginObjectDocument();
			pod.save();
			pluginObjectAssociation.setObjectId(pod.getId());
			pluginObjectAssociation.setObjectType(PluginObjectDocument.class.getCanonicalName());
			
			pluginObjectAssociation.setObjectIndex(pluginObjectAssociation.getNextObjectIndex(plugin.getId()));

		}
		
		pluginObjectAssociation.setPluginId(plugin.getId());
		pluginObjectAssociation.setAssociationType(PluginObjectAssociation.BASE_PLUGIN_ASSOCIATION);
		
		pluginObjectAssociation.save();

		model.addAttribute("plugin", plugin);
		
		// Clear the values stored in this form.
		model.addAttribute("devAddObjectsToPluginFormBean", new DevAddObjectsToPluginFormBean());
		
		model.addAttribute("pluginObjectAssociations", new PluginObjectAssociation().getAllForPlugin(id));

		return "/developing/addObjectsToPlugin.jsp";

	}
	
	/** Directs the user to the modify document page. */
	@RequestMapping(value = { "modifyPluginObject/plugin/{pId}/document/{dId}" }, method = RequestMethod.GET)
	public String addObjectsToPluginGet(@PathVariable Long pId, @PathVariable Long dId, Model model) {

		Plugin plugin = new Plugin().getModelObject(Plugin.class, pId);
		PluginObjectDocument pod = new PluginObjectDocument().getById(dId);
		
		model.addAttribute("plugin", plugin);
		model.addAttribute("pod", pod);
		
		DevModifyPluginDocumentFormBean devModifyPluginDocumentFormBean = new DevModifyPluginDocumentFormBean(pod);
		model.addAttribute("devModifyPluginDocumentFormBean", devModifyPluginDocumentFormBean);
		
		return "/developing/modifyPluginDocument.jsp";
	
	}
	
	/** Returns mappings in page for developer to edit plugin document. */
	@RequestMapping(value = { "plugin/{pId}/modifyPluginDocument/{dId}" }, method = RequestMethod.POST)
	public String modifyPluginObjectDocument(
			@PathVariable Long pId, @PathVariable Long dId,
			@ModelAttribute("devModifyPluginDocumentFormBean") @Valid DevModifyPluginDocumentFormBean devModifyPluginDocumentFormBean,
			Model model) {
		
		Plugin plugin = new Plugin().getModelObject(Plugin.class, pId);
		PluginObjectDocument pod = new PluginObjectDocument().getById(dId);
		
		BeanUtils.copyProperties(devModifyPluginDocumentFormBean, pod);
		pod.save();
		
		model.addAttribute("plugin", plugin);
		model.addAttribute("pod", pod);
		
		return "/developing/modifyPluginDocument.jsp";
	}

	/**
	 * This REST method uploads the developer's changes to set of objects that a
	 * plugin has.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "OLD_addObjectsToPlugin/{id}" }, method = RequestMethod.POST)
	public String definePluginPost(
			@ModelAttribute("devAddObjectsToPluginFormBean") @Valid DevAddObjectsToPluginFormBean devAddObjectsToPluginFormBean,
			BindingResult bindingResult, @PathVariable Long id, Model model) {

		if (bindingResult.hasErrors()) {
			return "/developing/addObjectsToPlugin.jsp";
		}

		Plugin plugin = new Plugin();

		if (!((id == null) || (id.intValue() == 0))) {
			plugin = plugin.getById(id);
		}

		// Copy in properties, set customized to false, since developers make
		// uncustomized plugins for the authors to customize.
		BeanUtils.copyProperties(devAddObjectsToPluginFormBean, plugin);
		plugin.setCustomized(false);

		plugin.save();

		model.addAttribute("plugin", plugin);
		model.addAttribute("devAddObjectsToPluginFormBean", devAddObjectsToPluginFormBean);

		return "redirect:/developing/addObjectsToPlugin/" + plugin.getId();

	}

	/**
	 * Returns a plugin based on its id, unless the id is '0', in which case it
	 * returns a new plugin. This dance is done to allow an id of 0 to indicate
	 * that we want a new object created, so we can use the same form for create
	 * and edit.
	 * 
	 * @param id
	 * @return
	 * 
	 *         private Plugin getModelPlugin(Long id) { Plugin plugin = new
	 *         Plugin();
	 * 
	 *         if (id.intValue() > 0) { plugin = new Plugin().getById(id); }
	 *         else { plugin.setId(new Long(0)); }
	 * 
	 *         return plugin; }
	 */

}
