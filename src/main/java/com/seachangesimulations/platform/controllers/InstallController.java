
package com.seachangesimulations.platform.controllers;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seachangesimulations.platform.domain.Organization;
import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.Plugin;
import com.seachangesimulations.platform.domain.PluginPointer;
import com.seachangesimulations.platform.domain.assignment.PersonOrganizationAssignment;
import com.seachangesimulations.platform.mvc.formbeans.admin.AdminInstallationFormBean;

@Controller
public class InstallController extends BaseController{
	
	private static final Logger LOGGER = Logger
            .getLogger(InstallController.class.getName());

	@RequestMapping(value = { "/install" }, method = RequestMethod.GET)
	public String showInstallPage(Map<String, Object> model) {
		LOGGER.debug("In the /install GET Request Mapping");
		return "install/index.jsp";
	}

	@RequestMapping(value = { "/install/installForm" }, method = RequestMethod.GET)
	public String showInstallForm(Model model) {
		LOGGER.debug("In the /install/installForm GET Request Mapping");
		model.addAttribute(new AdminInstallationFormBean());
		return "install/install.jsp";
	}

	/**
	 * Handles the creation of the first user (the admin user) on a platform, and creates the first organization.
	 * 
	 * @param installationObject
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = { "/install/installForm" }, method = RequestMethod.POST)
	public String processInstallForm(AdminInstallationFormBean aifb, BindingResult bindingResult) {

		try {
			Long orgId = new Long(1);
			
			Organization o = new Organization();
			o.setName(aifb.getOrganizationName());
			o.setId(orgId);
			o.save();
			
			new Person().create(aifb.getUsername(), aifb.getPassword(), PersonOrganizationAssignment.ADMIN_LEVEL);
			
			Person p = new Person().getByUsername(aifb.getUsername());
			p.setFirstName(aifb.getFirstName());
			p.setInitialOrgId(orgId);

			p.save();
			
			Plugin controlPlugin = new Plugin();
			controlPlugin.setSystemPlugin(true);
			controlPlugin.setPluginDirectory("playing/controlTab.jsp");
			controlPlugin.save();
			
			PluginPointer controlPointer = new PluginPointer();
			controlPointer.setSystemPluginPointer(true);
			controlPointer.setSystemPluginHandle(PluginPointer.SYSTEM_CONTROL);
			controlPointer.setPluginHeading("Control");
			controlPointer.setPluginId(controlPlugin.getId());
			controlPointer.save();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/install/installSuccess";
	}
	
	@RequestMapping(value = { "/install/installSuccess" }, method = RequestMethod.GET)
	public String showInstallSuccess(Model model) {

		return "install/installSuccess.jsp";
	}
	
}
