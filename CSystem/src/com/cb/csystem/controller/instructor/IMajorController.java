package com.cb.csystem.controller.instructor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cb.csystem.domain.MajorDomain;
import com.cb.csystem.domain.UserDomain;
import com.cb.csystem.service.IMajorService;
import com.cb.csystem.service.IUserService;
import com.cb.csystem.util.Consts;
import com.cb.system.util.PageInfo;

/**
 * 专业控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/instructor/major")
public class IMajorController {

	@Resource private IMajorService majorService;
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
	 * 专业列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/majorList")
	public String getmajorList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,HttpSession session)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				List<MajorDomain> majorList=majorService.doSearchmajorPageList(pageInfo,userDomain.getCollege().getId(),null);
				model.addAttribute("majorList", majorList);
			}
		}
		
		return "/instructorView/major/majorList";
	}
	
	/**
	 * 搜索
	 * @param pageInfo
	 * @param bindingResult
	 * @param model
	 * @param session
	 * @param searchText
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/majorSearchList")
	public String domajorSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,HttpSession session,String searchText)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				List<MajorDomain> majorList=majorService.doSearchmajorPageList(pageInfo,userDomain.getCollege().getId(),searchText);
				model.addAttribute("majorList", majorList);
				model.addAttribute("searchText", searchText);
			}
		}
		
		return "/instructorView/major/majorList";
	}
	
	/**
	 * 专业详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/majorView/{id}")
	public String domajorView(Model model,@PathVariable String id) throws Exception{
		
		//获取major信息
		MajorDomain majorDomain=majorService.doGetById(id);
		model.addAttribute("majorDomain", majorDomain);
		
		return "/instructorView/major/majorView";
	}
	
	/**
	 * 新增专业页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/majorAdd")
	public String domajorAdd(Model model,HttpSession session)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				model.addAttribute("collegeDomain", userDomain.getCollege());
			}
		}
		
		return "/instructorView/major/majorAdd";
	}
	
	/**
	 * 修改专业
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/majorEdit/{id}")
	public String domajorEdit(Model model,@PathVariable String id,HttpSession session)throws Exception{
		
		//获取major信息
		MajorDomain majorDomain=majorService.doGetById(id);
		model.addAttribute("majorDomain", majorDomain);
		
		return "/instructorView/major/majorEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") MajorDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return Consts.ERROR;
		} else {
			if(majorService.doSave(domain)){
				return Consts.SUCCESS;
			}
		}
		return Consts.ERROR;
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
		
		if(majorService.doDeleteById(id)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}

}
