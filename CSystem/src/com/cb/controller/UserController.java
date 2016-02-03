package com.cb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cb.domain.UserDomain;
import com.cb.service.bean.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource private UserService userService;
	
	@RequestMapping("/userList")
	public String getAllUser(Model model) throws Exception{
		
		List<UserDomain> userList=userService.doGetUserList();
		model.addAttribute("userList", userList);
		
		return "/user/userList";
	}
	
	@RequestMapping("/userAdd")
	public String douserAdd(Model model)throws Exception{
		
		return "/user/userAdd";
	}
}
