package com.cb.csystem.controller.instructor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cb.csystem.domain.ClassDomain;
import com.cb.csystem.domain.MajorDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.domain.UserDomain;
import com.cb.csystem.service.IClassService;
import com.cb.csystem.service.IMajorService;
import com.cb.csystem.service.IUserService;
import com.cb.csystem.util.Consts;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;
import com.cb.system.util.ValidateUtil;

/**
 * 班级控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/instructor/class")
public class IClassController {

	//service服务
	@Resource private IUserService userService;
	@Resource private IMajorService majorService;
	@Resource private IClassService classService;
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
	 * 班级列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/classList")
	public String getclassList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,HttpSession session)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(userDomain.getCollege().getId());
				model.addAttribute("majorList", majorList);
				List<ClassDomain> classList=classService.doSearchclassPageList(pageInfo, userDomain.getGrade().getId(),userDomain.getCollege().getId(), null, null);
				model.addAttribute("classList", classList);
			}
		}
		
		return "/instructorView/classinfo/classList";
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
	@RequestMapping("/classSearchList")
	public String doclassSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,HttpSession session,String majorId,String searchText)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(userDomain.getCollege().getId());
				List<ClassDomain> classList=new ArrayList<ClassDomain>();
				if(ValidateUtil.isEmpty(majorId)){
					classList=classService.doSearchclassPageList(pageInfo,userDomain.getGrade().getId(), userDomain.getCollege().getId(), null, searchText);
				}else{
					classList=classService.doSearchclassPageList(pageInfo,userDomain.getGrade().getId(), null, majorId, searchText);
				}
				
				model.addAttribute("majorList", majorList);
				model.addAttribute("classList", classList);
				model.addAttribute("majorId", majorId);
				model.addAttribute("searchText", searchText);
			}
		}
	
		return "/instructorView/classinfo/classList";
	}
	
	/**
	 * 班级详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/classView/{id}")
	public String doclassView(Model model,@PathVariable String id) throws Exception{
		
		//获取class信息
		ClassDomain classDomain=classService.doGetById(id);
		model.addAttribute("classDomain", classDomain);
		StudentDomain monitorDomain=classService.doGetMonitor(classDomain);
		if(monitorDomain!=null){
			model.addAttribute("monitorName", monitorDomain.getName());
		}
		
		return "/instructorView/classinfo/classView";
	}
	
	/**
	 * 新增班级页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/classAdd")
	public String doclassAdd(Model model,HttpSession session)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(userDomain.getCollege().getId());
				model.addAttribute("majorList", majorList);
				model.addAttribute("userDomain", userDomain);
			}
		}
		
		return "/instructorView/classinfo/classAdd";
	}
	
	/**
	 * 修改班级
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/classEdit/{id}")
	public String doclassEdit(Model model,@PathVariable String id)throws Exception{
		
		//获取class信息
		ClassDomain classDomain=classService.doGetById(id);
		List<MajorDomain> majorList=majorService.doGetFilterList();
		
		model.addAttribute("classDomain", classDomain);
		model.addAttribute("majorList", majorList);
		StudentDomain monitorDomain=classService.doGetMonitor(classDomain);
		if(monitorDomain!=null){
			model.addAttribute("monitorName", monitorDomain.getName());
		}
		return "/instructorView/classinfo/classEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") ClassDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return Consts.ERROR;
		} else {
			if(classService.doSave(domain)){
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
		
		if(classService.doDeleteById(id)){
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
	@RequestMapping("/deleteClasses")
	@ResponseBody
	public String doDeleteClasses(@RequestParam(value = "classIds[]") String[] classIds)throws Exception{
		
		if(classService.doDeleteByIds(classIds)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 根据专业查找相应班级
	 * @param model
	 * @param major_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getClassByMajor")
	@ResponseBody
	public String dogetClassByMajor(Model model,String major_id,HttpSession session)throws Exception{
		
		List<SelectItem> classList=new ArrayList<SelectItem>();
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				classList=classService.doGetClazzSelectItem(userDomain.getGrade().getId(), userDomain.getCollege().getId(), major_id);
			}
		}
		
		JSONArray jsonArray=JSONArray.fromObject(classList);
		return jsonArray.toString();
		
	}
	
	/**
	 * 设置班长界面
	 * @param model
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/setmonitorView/{classId}")
	public String docsetmonitor(Model model,@PathVariable String classId)throws Exception{
		
		StudentDomain monitorDomain=classService.doGetMonitor(classService.doGetById(classId));
		if(monitorDomain!=null){
			model.addAttribute("monitorId", monitorDomain.getStuId());
		}
		model.addAttribute("classId", classId);
		
		return "/instructorView/classinfo/setMonitorView";
	}
	
	/**
	 * 设置班长
	 * @param classId
	 * @param monitorId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/setmonitor")
	@ResponseBody
	public String doSetmonitor(String classId,String monitorId)throws Exception{
		
		if(classService.doSetMonitor(classId,monitorId)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
}
