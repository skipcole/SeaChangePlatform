
package com.seachangesimulations.platform.controllers;

import java.security.Principal;
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
import com.seachangesimulations.platform.utilities.ObjectPackager;
import com.seachangesimulations.platform.utilities.PlatformProperties;

@Controller
@RequestMapping(CMC.INSTALL)
public class InstallController extends BaseController{
	
	private static final Logger LOGGER = Logger
            .getLogger(InstallController.class.getName());

	@RequestMapping(value = { CMC.INDEX }, method = RequestMethod.GET)
	public String showInstallPage(Map<String, Object> model) {
		LOGGER.debug("In the /install GET Request Mapping");
		return "install/index.jsp";
	}

	@RequestMapping(value = { CMC.I_INSTALLFORM }, method = RequestMethod.GET)
	public String showInstallForm(Model model) {
		LOGGER.debug("In the /install/installForm GET Request Mapping");
		model.addAttribute(new AdminInstallationFormBean());
		return "install/install.jsp";
	}

	/**
	 * Handles the creation of the first user (the admin user) on a platform, and creates the first organization.
	 * 
	 * @param aifb AdminInstallationFormBean
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = { CMC.I_INSTALLFORM_POST }, method = RequestMethod.POST)
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
			
			readLoadedPlugins();
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/install/installSuccess";
	}
	
	private void readLoadedPlugins() {
		ObjectPackager.loadPluginsFromDisk(PlatformProperties.getValue("pluginSourceDirectory"));		
	}

	
	@RequestMapping(value = { CMC.I_INSTALL_SUCCESS }, method = RequestMethod.GET)
	public String showInstallSuccess(Model model, Principal principal) {

		// Prevent Spring Security from throwing spurious error by setting this to null
		principal = null;
		
		return "install/installSuccess.jsp";
	}
	
}
