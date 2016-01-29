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
	
	/**
	 * 表格
	 * @return
	 */
	@RequestMapping("/table")
	public String doTable(){
		
		return "/csystem/table";
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping("/login")
	public String doLogin(){
		
		return "/csystem/login";
	}
}
