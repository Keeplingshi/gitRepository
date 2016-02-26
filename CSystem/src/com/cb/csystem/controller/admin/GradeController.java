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

import com.cb.csystem.domain.GradeDomain;
import com.cb.csystem.service.IGradeService;
import com.cb.csystem.util.Consts;

/**
 * 年级控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/admin/grade")
public class GradeController {

	@Resource private IGradeService gradeService;
	
	/**
	 * 年级列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gradeList")
	public String getgradeList(Model model)throws Exception{
		
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		model.addAttribute("gradeList", gradeList);
		
		return "/adminView/grade/gradeList";
	}
	
	/**
	 * 年级详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gradeView/{id}")
	public String dogradeView(Model model,@PathVariable String id) throws Exception{
		
		//获取grade信息
		GradeDomain gradeDomain=gradeService.doGetById(id);
		model.addAttribute("gradeDomain", gradeDomain);
		
		return "/adminView/grade/gradeView";
	}
	
	/**
	 * 新增年级页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gradeAdd")
	public String dogradeAdd(Model model)throws Exception{
		
		return "/adminView/grade/gradeAdd";
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
	public String doSave(@Valid @ModelAttribute("domain") GradeDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return Consts.ERROR;
		} else {
			if(gradeService.doSave(domain)){
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
		
		if(gradeService.doDeleteById(id)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
}
