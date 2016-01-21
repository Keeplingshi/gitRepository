package com.cb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cb.domain.UserDomain;
import com.cb.entity.User;
import com.cb.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name="userService")
	private IUserService userService ;

	@RequestMapping("/getAllUser")
	public String getAllUser(Model model){
		
		List<UserDomain> userList=userService.getAllUser();
		model.addAttribute("userList", userList);
		
		return "/jobUser/index";
	}
	
	@RequestMapping("/getUser")
	public String getUser(String id,HttpServletRequest request){
		
		UserDomain userDomain=userService.getUser(id);
		request.setAttribute("user", userDomain);
	
		return "/jobUser/editUser";
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		return "/jobUser/addUser";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user,HttpServletRequest request){
		
		userService.addUser(user);
		
		return "redirect:/user/getAllUser";
	}
	
	@RequestMapping("/delUser")
	public void delUser(String id,HttpServletResponse response){
		
		String result = "{\"result\":\"error\"}";
		
		if(userService.delUser(id)){
			result = "{\"result\":\"success\"}";
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@RequestMapping("/updateUser")
//	public String updateUser(UserDomain user,HttpServletRequest request){
//		
//		if(userService.updateUser(user)){
//			user = userService.getUser(user.getId());
//			request.setAttribute("user", user);
//			return "redirect:/user/getAllUser";
//		}else{
//			return "/jobUser/error";
//		}
//	}
}
