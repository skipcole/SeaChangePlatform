package com.seachangesimulations.platform.controllers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.seachangesimulations.platform.domain.Organization;
import com.seachangesimulations.platform.domain.Person;
import com.seachangesimulations.platform.domain.assignment.PersonOrganizationAssignment;
import com.seachangesimulations.platform.html.SearchFormBuilder;
import com.seachangesimulations.platform.mvc.formbeans.admin.AdminCreateOrgFormBean;
import com.seachangesimulations.platform.mvc.formbeans.admin.AdminCreatePersonFormBean;
import com.seachangesimulations.platform.service.SessionInfoBean;


@Controller
@RequestMapping(CMC.ADMIN_BASE)
public class AdminController extends BaseController{

	static void loadLevelsIntoMap(Model model){
		model.addAttribute("ADMIN_LEVEL", new Long(PersonOrganizationAssignment.ADMIN_LEVEL));
		model.addAttribute("DEV_LEVEL", new Long(PersonOrganizationAssignment.DEV_LEVEL));
		model.addAttribute("AUTHOR_LEVEL", new Long(PersonOrganizationAssignment.AUTHOR_LEVEL));
		model.addAttribute("FACILITATOR_LEVEL", new Long(PersonOrganizationAssignment.FACILITATOR_LEVEL));
		model.addAttribute("PLAYER_LEVEL", new Long(PersonOrganizationAssignment.PLAYER_LEVEL));
	}
	
	@RequestMapping(value = { CMC.ADMIN_INDEX }, method = RequestMethod.GET)
	public String showAdminPage(Map<String, Object> model) {
		getSessionInfoBean().setPlatformZone(SessionInfoBean.ADMIN_ZONE);
		return "admin/adminIndex.jsp";
	}
	
	@RequestMapping(value = { "index/setZone" }, method = RequestMethod.GET)
	public String setAdminZone(Map<String, Object> model) {
		getSessionInfoBean().setPlatformZone(SessionInfoBean.ADMIN_ZONE);
		return "redirect:/admin/index";
	}

	// ----------------- Organization Methods ----------------------------------
	@RequestMapping(value = { "userAdmin" }, method = RequestMethod.GET)
	public String showUserAdminPage() {
		return "admin/users/userIndex.jsp";
	}
	
	@RequestMapping(value = { "userCreate" }, method = RequestMethod.GET)
	public String showCreateUserPage(Model model) {
		String msg = "admin/userCreate - get. Beginning showCreateUserPage(model) ";
		System.out.println(msg);
		model.addAttribute(new AdminCreatePersonFormBean());
		AdminController.loadLevelsIntoMap(model);
		return "admin/users/create.jsp";  // this form has a method=post (but no action=).
	}
	
	@RequestMapping(value = { "userCreate" }, method = RequestMethod.POST)
	public String processCreateUserPage(AdminCreatePersonFormBean formPerson, BindingResult bindingResult) {
		
		System.out.println("MJS Processing /admin/userCreate - POST ");
		System.out.println("username was " + formPerson.getUsername());
		
		new Person().create(formPerson.getUsername(), formPerson.getPassword(), formPerson.getInitialAuthLevel());		
		Person person = new Person().getByUsername(formPerson.getUsername());
		
		person.setFirstName(formPerson.getFirstName());
		person.setLastName(formPerson.getLastName());
		person.save();

		return "admin/users/createSuccess.jsp";
	}  // end processCreateUserPage

	// this is obsolete as List all goes straight to usersDisplayTable
	@RequestMapping(value = { "usersList" }, method = RequestMethod.GET)
	public String showListUsersPage(Model model) {
		// @PathVariable String nameStarts;
		String msg = "admin/usersList - get. Beginning showListUsersPage(model) ";
		System.out.println(msg);
		// model.addAttribute(new AdminListUsersFormBean());
		// AdminController.loadLevelsIntoMap(model);
		return "admin/users/listUsers.jsp";  
	}
	
	@RequestMapping(value = { "usersSelect" }, method = RequestMethod.GET)
	public String showSelectUsersPage(Model model) {
		String msg = "admin/usersSelect - get. Beginning showSelectUsersPage(model) ";
		System.out.println(msg);
		
		// 	<label> <input type = "checkbox" name="firstNameIgnoreCase"  value="firstNameIgnoreCase" checked> Ignore Case </label>
		// Add standard search options - IgnoreCase checkbox and Select (Matches, Begins With, ...) List
		SearchFormBuilder.addStandardStringSearchOptions(model, "firstName");
		SearchFormBuilder.addStandardStringSearchOptions(model, "lastName");
		SearchFormBuilder.addStandardStringSearchOptions(model, "username");
		return "admin/users/selectUsers.jsp";  
	}
	
	@RequestMapping(value = { "usersDisplayTable" }, method = RequestMethod.GET)
	public String showUsersDisplayTable(@RequestParam Map<String, String> params, 
									Model model) { 
				
		String msg = "admin/usersDisplayTable - get. Beginning showUsersDisplayTable(model) ";
		System.out.println(msg);	
		// cant do lambdas becuase source < JDK 1.8
		System.out.println("The params are: " + params);
		// request.getParameter("nameStarts"); // wont work here, only works in JSP

		// get back a resultSet or similar, not a Hibernate Object (since private fields are very hard to access). 
		List<Object[]> resultList = new Person().searchFor(params);
		List<String> dbColumnNames = new Person().getDBColumnNames();
 		
		// add results and column names so jsp can print them out
		model.addAttribute("dbColumnNames", dbColumnNames);
		model.addAttribute("resultList", resultList);	
		model.addAttribute("returnPage", "/admin/userAdmin");
		
		return "showTable/showListAsTable.jsp";  
	} // end showUsersDisplayTable

	// ----------------- Organization Methods ----------------------------------
	@RequestMapping(value = { "orgAdmin" }, method = RequestMethod.GET )
	public String showOrgAdminPage() {
		return "admin/orgs/orgIndex.jsp";
	}

	// create Org - Get
	@RequestMapping(value = ("orgCreate"), method = RequestMethod.GET )
	public String showOrgCreatePage(Model model) {
		String msg = "admin/orgCreate - get. Beginning showCreateOrgPage(model) ";
		System.out.println(msg);
		model.addAttribute(new AdminCreateOrgFormBean());
		// createOrg-orgCreate.jsp is a similar file, but seems to have synchronization issues.
		return "admin/orgs/organCreate.jsp";  // orgCreate.jsp file concurrency problem.
 	}
	
	// create Org - Post 
	@RequestMapping(value = ("orgCreate"), method = RequestMethod.POST )
	public String processCreateUOrgPage(AdminCreateOrgFormBean formOrg, BindingResult bindingResult) {
		String orgName = formOrg.getName();
		String msg = "admin/orgCreate " + orgName + " - Post. Saving, leading to createSucess ";
		System.out.println(msg);
		// create new organization, returns a long representing the id
		// this will NOT have values for the createdDate or LastUpdatedDate
		new Organization().create(formOrg.getName());  // returns a long
		// Most fields set on create, dont change most of them.  Need to set dates which are null.
		Organization org = new Organization().getByName(formOrg.getName());		
		org.save(); // invokes AOP timeStamped routines to save dates.	
		return "admin/orgs/createSuccess.jsp";  
 	}
	
	// list all organizations - add values to model and call generic showTables jsp
	@RequestMapping(value = ("orgsDisplayTable"), method = RequestMethod.GET )
	public String showOrgsDisplayTable(@RequestParam Map<String, String> params, Model model) {
		String msg = "admin/orgsDisplayTable - get. Beginning showOrgsDisplayTable(model) ";
		System.out.println(msg);
		
		// add Column Headers and Query Results to Model
		List<String> dbColumnNames = new Organization().getDBColumnNames();
		List<Object[]> resultList = new Organization().searchFor(params);  // returns array of cols, not org object
		model.addAttribute("dbColumnNames", dbColumnNames);
		model.addAttribute("resultList", resultList);
		model.addAttribute("returnPage", "/admin/orgAdmin");
		return "showTable/showListAsTable.jsp";  
	}
	
	// select
	@RequestMapping( value = ( "orgsSelect"), method = RequestMethod.GET) 
	public String showSelectOrgs (Model model) {
		// name is a poor identifier, but its the col nmae in the database.
		SearchFormBuilder.addStandardStringSearchOptions(model, "name");
		return "admin/orgs/selectOrgs.jsp"; 
	}
	
	// delete
	
	
} // end AdmindController class
