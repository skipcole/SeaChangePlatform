package com.seachangesimulations.platform.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.seachangesimulations.platform.domain.PluginPointer;

@Controller
public class PluginPointerController extends BaseController{
	
	public List pluginPointers = new ArrayList<PluginPointer>();
	
	

	
	/**
	 * Puts the new PluginPointer onto the server.
	 * 
	 * @param pluginPointer
	 * @param bindingResult
	 * @param pluginPointerImage
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = { "/developing/pluginPointer/create" }, method = RequestMethod.POST)
	public String createPluginPointerPost( @Valid PluginPointer pluginPointer, BindingResult bindingResult,
		      @RequestParam(value="pluginPointerImage", required=false) MultipartFile pluginPointerImage, ModelAndView mav) {
		
		if (bindingResult.hasErrors()){
			return "/developing/pluginPointer/createPluginPointer.jsp";
		}
		
		
		pluginPointer.save();
		
		return "redirect:/developing/pluginPointer/createPluginPointerSuccess/" + pluginPointer.getId();
		
	}
	
	@RequestMapping(value = { "/developing/pluginPointer/createPluginPointerSuccess/{id}" }, method = RequestMethod.GET)
	public String createPluginPointerSuccess(@PathVariable Long id, Model model){
		
		model.addAttribute("pluginPointers", pluginPointers);
		return "/developing/pluginPointer/createPluginPointerSuccess.jsp";
	}
			
	
	/* ********************************* Read ********************************* */
	
	
	@RequestMapping(value = { "/developing/pluginPointer/edit/{id}" }, method = RequestMethod.POST)
	public String editPluginPointerPost( @Valid PluginPointer pluginPointer, BindingResult bindingResult,
		      @RequestParam(value="pluginPointerImage", required=false) MultipartFile pluginPointerImage, Model model) {
		
		return "developing/pluginPointer/createPluginPointer.jsp";
	}
	
	@RequestMapping(value = { "/playing/r/{rid}/a/{aid}/p/{pid}/i/{iid}" }, method = RequestMethod.GET)
	public String getPlayerTab(@PathVariable String rid, @PathVariable String aid, 
			@PathVariable String pid, @PathVariable String iid){
		System.out.println("Id of role play is :" + rid);
		System.out.println("Id of actor is :" + aid);
		System.out.println("Id of phase is :" + pid);
		System.out.println("Id of index is :" + iid);
		
		if (iid.equalsIgnoreCase("1")){
		return "playing/plugins/seachange_intro_plugin_v_0_0_0/index.jsp";
		} else if (iid.equalsIgnoreCase("2")) {
			return "playing/plugins/seachange_intro_plugin_v_0_0_0/index.htm";
		} else {
			return "playing/plugins/seachange_intro_plugin_v_0_0_0/index.txt";
		}
	}
	
}
