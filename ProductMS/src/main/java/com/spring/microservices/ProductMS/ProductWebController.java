package com.spring.microservices.ProductMS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RestController
public class ProductWebController {
	private static Logger log = LoggerFactory.getLogger(ProductWebController.class);

	@RequestMapping("/minion/{minionname}")
	public String getMinion(Model model, @PathVariable(name = "minionname") String minionname) {
		log.debug("web-request :" + minionname);
		model.addAttribute("minion", minionname);
		return "minion";
	}

}
