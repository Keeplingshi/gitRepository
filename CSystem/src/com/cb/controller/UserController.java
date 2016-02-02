package com.cb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cb.entity.User;
import com.cb.service.bean.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource private UserService userService;
	
	@RequestMapping("/userList")
	public String getAllUser(Model model){
		
		List<User> userList=userService.doGetFilterList();
		model.addAttribute("userList", userList);
		
		return "/user/userList";
	}
}
