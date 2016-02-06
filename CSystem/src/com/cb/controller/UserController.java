package com.cb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cb.domain.UserDomain;
import com.cb.service.bean.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource private UserService userService;
	
	/**
	 * 账户列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userList")
	public String getAllUser(Model model) throws Exception{
		
		List<UserDomain> userList=userService.doGetUserList();
		model.addAttribute("userList", userList);
		
		return "/user/userList";
	}
	
	/**
	 * 用户详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userView/{id}")
	public String doUserView(Model model,@PathVariable String id) throws Exception{
		
		UserDomain userDomain=userService.doGetUserById(id);
		model.addAttribute("userDomain", userDomain);
		
		return "/user/userView";
	}
	
	/**
	 * 新增账户页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userAdd")
	public String douserAdd(Model model)throws Exception{
		
		return "/user/userAdd";
	}
	
	/**
	 * 修改用户
	 * @param model
	 * @param id 用户id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userEdit/{id}")
	public String doUserEdit(Model model,@PathVariable String id)throws Exception{
		
		UserDomain userDomain=userService.doGetUserById(id);
		model.addAttribute("userDomain", userDomain);
		
		return "/user/userEdit";
	}
	
	/**
	 * 保存
	 * @param domain
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String doSave(@Valid @ModelAttribute("domain") UserDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return "error";
		} else {
			if(userService.doSaveUser(domain)){
				return "success";
			}
		}
		return "error";
	}
	
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public String doDelete(@PathVariable String id)throws Exception{
		
		if(userService.doDeleteUserById(id)){
			return "success";
		}
		
		return "error";
	}
}
