package com.seachangesimulations.platform.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestObjectRetriever extends BaseController{


	@RequestMapping(value = {"/testRest"}, method = RequestMethod.GET)
	public String testRest(){
		return "developing/testRest.jsp";
	}
	
}
