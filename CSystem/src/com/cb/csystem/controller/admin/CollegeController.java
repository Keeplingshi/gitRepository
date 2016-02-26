package com.cb.csystem.controller.admin;

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

import com.cb.csystem.domain.CollegeDomain;
import com.cb.csystem.service.ICollegeService;
import com.cb.csystem.util.Consts;

/**
 * 学院控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/admin/college")
public class CollegeController {

	@Resource private ICollegeService collegeService;
	
	/**
	 * 学院列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/collegeList")
	public String getcollegeList(Model model)throws Exception{
		
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		model.addAttribute("collegeList", collegeList);
		
		return "/adminView/college/collegeList";
	}
	
	/**
	 * 学院详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/collegeView/{id}")
	public String docollegeView(Model model,@PathVariable String id) throws Exception{
		
		//获取College信息
		CollegeDomain collegeDomain=collegeService.doGetById(id);
		model.addAttribute("collegeDomain", collegeDomain);
		
		return "/adminView/college/collegeView";
	}
	
	/**
	 * 新增学院页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/collegeAdd")
	public String docollegeAdd(Model model)throws Exception{
		
		return "/adminView/college/collegeAdd";
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
	public String doSave(@Valid @ModelAttribute("domain") CollegeDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return Consts.ERROR;
		} else {
			if(collegeService.doSave(domain)){
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
		
		if(collegeService.doDeleteById(id)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
}
