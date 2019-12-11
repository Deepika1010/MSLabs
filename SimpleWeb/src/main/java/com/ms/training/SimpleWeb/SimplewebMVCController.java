package com.ms.training.SimpleWeb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimplewebMVCController {

	@RequestMapping("/home")
	public String getDefaultView(Model mv) {
		
		mv.addAttribute("name", "http://ibm.webex.com/join/kaprain1");
		return "home";
	}
	

}
