package com.cb.csystem.controller;

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

import com.cb.csystem.domain.JobInfoDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.service.IJobInfoService;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.util.Consts;
import com.cb.system.util.PageInfo;

/**
 * 就业信息控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/jobInfo")
public class JobInfoController {

	@Resource private IJobInfoService jobInfoService;
	@Resource private IStudentService studentService;
	
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
	 * 就业列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoList")
	public String getjobInfoList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model)throws Exception{
		
//		List<JobInfoDomain> jobInfoList=jobInfoService.doGetPageList(pageInfo);
//		model.addAttribute("jobInfoList", jobInfoList);
		List<StudentDomain> studentList=studentService.doGetPageList(pageInfo);
		model.addAttribute("studentList", studentList);
		
		return "/jobInfo/jobInfoList";
	}
	
	/**
	 * 搜索
	 * @param pageInfo
	 * @param bindingResult
	 * @param model
	 * @param collegeId
	 * @param majorId
	 * @param searchText
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoSearchList")
	public String dojobInfoSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String searchText,String sortMode,String sortValue)throws Exception{
	
		List<StudentDomain> studentList=studentService.doGetPageList(pageInfo);
		model.addAttribute("studentList", studentList);
		
		return "/jobInfo/jobInfoList";
	}
	
	/**
	 * 就业详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoView/{id}")
	public String dojobInfoView(Model model,@PathVariable String id) throws Exception{
		
		StudentDomain studentDomain=studentService.doGetById(id);
		model.addAttribute("studentDomain", studentDomain);
		
		return "/jobInfo/jobInfoView";
	}
	
	/**
	 * 新增就业页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoAdd")
	public String dojobInfoAdd(Model model)throws Exception{
		
		return "/jobInfo/jobInfoAdd";
	}
	
	/**
	 * 修改就业
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoEdit/{id}")
	public String dojobInfoEdit(Model model,@PathVariable String id)throws Exception{
		
		StudentDomain studentDomain=studentService.doGetById(id);
		model.addAttribute("studentDomain", studentDomain);
		
		return "/jobInfo/jobInfoEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") JobInfoDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			System.out.println(result);
			return Consts.ERROR;
		} else {
			if(jobInfoService.doSave(domain)){
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
		
		if(jobInfoService.doDeleteById(id)){
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
	@RequestMapping("/deleteJobInfos")
	@ResponseBody
	public String doDeleteJobInfos(@RequestParam(value = "jobInfoIds[]") String[] jobInfoIds)throws Exception{
		
		if(jobInfoService.doDeleteByIds(jobInfoIds)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
}
