package com.seachangesimulations.platform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.assignment.PersonOrganizationAssignment;
import com.seachangesimulations.platform.mvc.formbeans.admin.AdminCreatePersonFormBean;


@Controller
@RequestMapping(CMC.ADMIN_BASE)
public class AdminController {

	static void loadLevelsIntoMap(Model model){
		model.addAttribute("ADMIN_LEVEL", new Long(PersonOrganizationAssignment.ADMIN_LEVEL));
		model.addAttribute("DEV_LEVEL", new Long(PersonOrganizationAssignment.DEV_LEVEL));
		model.addAttribute("AUTHOR_LEVEL", new Long(PersonOrganizationAssignment.AUTHOR_LEVEL));
		model.addAttribute("FACILITATOR_LEVEL", new Long(PersonOrganizationAssignment.FACILITATOR_LEVEL));
		model.addAttribute("PLAYER_LEVEL", new Long(PersonOrganizationAssignment.PLAYER_LEVEL));
	}

	@RequestMapping(value = { "userAdmin" }, method = RequestMethod.GET)
	public String showUserAdminPage() {
		return "admin/users/index.jsp";
	}
	
	@RequestMapping(value = { "userCreate" }, method = RequestMethod.GET)
	public String showCreateUserPage(Model model) {
		model.addAttribute(new AdminCreatePersonFormBean());
		AdminController.loadLevelsIntoMap(model);
		return "admin/users/create.jsp";
	}
	
	@RequestMapping(value = { "userCreate" }, method = RequestMethod.POST)
	public String processCreateUserPage(AdminCreatePersonFormBean formPerson, BindingResult bindingResult) {
		
		System.out.println("processing create");
		System.out.println("username was " + formPerson.getUsername());
		
		new Person().create(formPerson.getUsername(), formPerson.getPassword(), formPerson.getInitialAuthLevel());
		
		Person person = new Person().getByUsername(formPerson.getUsername());
		
		person.setFirstName(formPerson.getFirstName());
		person.setLastName(formPerson.getLastName());
		person.save();
				
		return "admin/users/createSuccess.jsp";
	}
}
