/**
 * 
 */
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

import com.cb.csystem.domain.DisciplineTypeDomain;
import com.cb.csystem.service.IDisciplineTypeService;
import com.cb.csystem.util.Consts;

/**
 * 违纪类型基础服务层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/disciplineType")
public class DisciplineTypeController {
	
	@Resource private IDisciplineTypeService disciplineTypeService;
	
	/**
	 * 违纪列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/disciplineTypeList")
	public String getdisciplineTypeList(Model model)throws Exception{
		
		List<DisciplineTypeDomain> disciplineTypeList=disciplineTypeService.doGetFilterList();
		model.addAttribute("disciplineTypeList", disciplineTypeList);
		
		return "/adminView/disciplineType/disciplineTypeList";
	}
	
	/**
	 * 违纪详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/disciplineTypeView/{id}")
	public String dodisciplineTypeView(Model model,@PathVariable String id) throws Exception{
		
		//获取disciplineType信息
		DisciplineTypeDomain disciplineTypeDomain=disciplineTypeService.doGetById(id);
		model.addAttribute("disciplineTypeDomain", disciplineTypeDomain);
		
		return "/adminView/disciplineType/disciplineTypeView";
	}
	
	/**
	 * 违纪修改页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/disciplineTypeEdit/{id}")
	public String dodisciplineTypeEdit(Model model,@PathVariable String id) throws Exception{
		
		//获取disciplineType信息
		DisciplineTypeDomain disciplineTypeDomain=disciplineTypeService.doGetById(id);
		model.addAttribute("disciplineTypeDomain", disciplineTypeDomain);
		
		return "/adminView/disciplineType/disciplineTypeEdit";
	}
	
	/**
	 * 新增违纪页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/disciplineTypeAdd")
	public String dodisciplineTypeAdd(Model model)throws Exception{
		
		return "/adminView/disciplineType/disciplineTypeAdd";
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
	public String doSave(@Valid @ModelAttribute("domain") DisciplineTypeDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return Consts.ERROR;
		} else {
			if(disciplineTypeService.doSave(domain)){
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
		
		if(disciplineTypeService.doDeleteById(id)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}

}
