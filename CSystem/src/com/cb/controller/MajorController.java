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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cb.domain.CollegeDomain;
import com.cb.domain.MajorDomain;
import com.cb.domain.RoleDomain;
import com.cb.domain.UserDomain;
import com.cb.service.ICollegeService;
import com.cb.service.IMajorService;
import com.cb.util.Consts;
import com.system.util.PageInfo;

/**
 * 专业控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/major")
public class MajorController {

	@Resource private IMajorService majorService;
	@Resource private ICollegeService collegeService;
	
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
			,BindingResult bindingResult,Model model)throws Exception{
		
		List<MajorDomain> majorList=majorService.doGetPageList(pageInfo);
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		
		return "/major/majorList";
	}
	
	@RequestMapping("/majorSearchList")
	public String doUserSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String collegeId,String searchText)throws Exception{
		
		List<MajorDomain> majorList=majorService.doSearchmajorPageList(pageInfo,collegeId,searchText);
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("collegeId", collegeId);
		model.addAttribute("searchText", searchText);
		
		return "/major/majorList";
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
		
		return "/major/majorView";
	}
	
	/**
	 * 新增专业页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/majorAdd")
	public String domajorAdd(Model model)throws Exception{
		
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		model.addAttribute("collegeList", collegeList);
		
		return "/major/majorAdd";
	}
	
	/**
	 * 修改专业
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/majorEdit/{id}")
	public String domajorEdit(Model model,@PathVariable String id)throws Exception{
		
		//获取major信息
		MajorDomain majorDomain=majorService.doGetById(id);
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		
		model.addAttribute("majorDomain", majorDomain);
		model.addAttribute("collegeList", collegeList);
		
		return "/major/majorEdit";
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