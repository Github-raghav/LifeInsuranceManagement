package com.Lims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/normal")
public class NormalController {
	
	@RequestMapping("/index")
	public String Normal() {
		
		return "normal/userdashboard";
	}
}
