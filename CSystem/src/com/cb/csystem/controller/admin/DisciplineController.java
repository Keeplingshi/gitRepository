package com.cb.csystem.controller.admin;

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

import com.cb.csystem.domain.DisciplineDomain;
import com.cb.csystem.service.IDisciplineService;
import com.cb.csystem.util.Consts;
import com.cb.system.util.PageInfo;

/**
 * 违纪控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/discipline")
public class DisciplineController {

	@Resource private IDisciplineService disciplineService;
	
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
	@RequestMapping("/disciplineList")
	public String getdisciplineList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model)throws Exception{
		
		List<DisciplineDomain> disciplineList=disciplineService.doGetPageList(pageInfo);
		
		model.addAttribute("disciplineList", disciplineList);
		
		return "/adminView/discipline/disciplineList";
	}
	
	@RequestMapping("/disciplineSearchList")
	public String dodisciplineSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String collegeId,String searchText)throws Exception{
		
		
		return "/adminView/discipline/disciplineList";
	}
	
	/**
	 * 专业详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/disciplineView/{id}")
	public String dodisciplineView(Model model,@PathVariable String id) throws Exception{
		
		//获取discipline信息
		DisciplineDomain disciplineDomain=disciplineService.doGetById(id);
		model.addAttribute("disciplineDomain", disciplineDomain);
		
		return "/adminView/discipline/disciplineView";
	}
	
	/**
	 * 新增专业页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/disciplineAdd")
	public String dodisciplineAdd(Model model)throws Exception{
		
		return "/adminView/discipline/disciplineAdd";
	}
	
	/**
	 * 修改专业
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/disciplineEdit/{id}")
	public String dodisciplineEdit(Model model,@PathVariable String id)throws Exception{
		
		//获取discipline信息
		DisciplineDomain disciplineDomain=disciplineService.doGetById(id);
		
		model.addAttribute("disciplineDomain", disciplineDomain);
		
		return "/adminView/discipline/disciplineEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") DisciplineDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return Consts.ERROR;
		} else {
			if(disciplineService.doSave(domain)){
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
		
		if(disciplineService.doDeleteById(id)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
}
