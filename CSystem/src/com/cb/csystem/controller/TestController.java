package com.cb.csystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试界面用
 * @author chen
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/test")
	public String getUserList(Model model) throws Exception{
		
		return "/test/testView";
	}
	
	
}
