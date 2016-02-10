package com.cb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cb.domain.UserDomain;
import com.cb.service.IUserService;
import com.cb.util.Consts;
import com.system.util.PageInfo;

/**
 * 用户控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	//userService服务层
	@Resource private IUserService userService;
	
	/**
	 * 过滤起前台pageInfo
	 * 使@ModelAttribute("pageInfo") PageInfo pageInfo在前台使用name="pageInfo.currentPageNo"来进行传参数
	 * @param binder
	 */
	@InitBinder("pageInfo")  
	public void initPageInfoBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("pageInfo.");  
	}
	
	/**
	 * 账户列表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userList")
	public String getUserList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model) throws Exception{

		//采用分页方式获取
		List<UserDomain> userList=userService.doGetUserPageList(pageInfo);
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
		
		//获取UserDomain信息
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
	public String doUserAdd(Model model)throws Exception{
		
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

		//获取单条Domain信息
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
			return Consts.ERROR;
		} else {
			if(userService.doSaveUser(domain)){
				return Consts.SUCCESS;
			}
		}
		return "error";
	}
	
	/**
	 * 删除单条数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public String doDelete(@PathVariable String id)throws Exception{
		
		if(userService.doDeleteUserById(id)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 批量删除
	 * @param userIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteUsers")
	@ResponseBody
	public String doDeleteUsers(@RequestParam(value = "userIds[]") String[] userIds)throws Exception{
		
		if(userService.doDeleteUsersByIds(userIds)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
}
