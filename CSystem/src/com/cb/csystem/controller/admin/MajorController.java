package com.cb.csystem.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cb.csystem.domain.CollegeDomain;
import com.cb.csystem.domain.MajorDomain;
import com.cb.csystem.service.ICollegeService;
import com.cb.csystem.service.IMajorService;
import com.cb.csystem.util.Consts;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

/**
 * 专业控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/admin/major")
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
		
		return "/adminView/major/majorList";
	}
	
	@RequestMapping("/majorSearchList")
	public String domajorSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String collegeId,String searchText)throws Exception{
		
		List<MajorDomain> majorList=majorService.doSearchmajorPageList(pageInfo,collegeId,searchText);
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("collegeId", collegeId);
		model.addAttribute("searchText", searchText);
		
		return "/adminView/major/majorList";
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
		
		return "/adminView/major/majorView";
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
		
		return "/adminView/major/majorAdd";
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
		
		return "/adminView/major/majorEdit";
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
	
	/**
	 * 根据学院查找专业
	 * @param model
	 * @param college_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getMajorByCollege")
	@ResponseBody
	public String dogetMajorByCollege(Model model,String college_id)throws Exception{
		
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(college_id);
		
		JSONArray jsonArray=JSONArray.fromObject(majorList);
		return jsonArray.toString();
		
	}
}
