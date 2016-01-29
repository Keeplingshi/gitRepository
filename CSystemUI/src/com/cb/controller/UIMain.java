package com.cb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/UIMain")
public class UIMain {

	@RequestMapping("/index")
	public String doIndex(){
		
		return "/csystem/index";
	} 
	
	@RequestMapping("/table")
	public String doTable(){
		
		return "/csystem/table";
	}
	
}
