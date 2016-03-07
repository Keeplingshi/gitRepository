package com.cb.csystem.controller.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
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

import com.cb.csystem.domain.CollegeDomain;
import com.cb.csystem.domain.DisciplineDomain;
import com.cb.csystem.domain.DisciplineTypeDomain;
import com.cb.csystem.domain.GradeDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.service.IClassService;
import com.cb.csystem.service.ICollegeService;
import com.cb.csystem.service.IDisciplineService;
import com.cb.csystem.service.IDisciplineTypeService;
import com.cb.csystem.service.IGradeService;
import com.cb.csystem.service.IMajorService;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.util.Consts;
import com.cb.csystem.util.DBToExcelUtil;
import com.cb.system.util.DateUtil;
import com.cb.system.util.FileUtil;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

/**
 * 违纪控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/discipline")
public class DisciplineController {

	@Resource private IDisciplineService disciplineService;
	@Resource private IDisciplineTypeService disciplineTypeService;
	@Resource private IGradeService gradeService;
	@Resource private IMajorService majorService;
	@Resource private ICollegeService collegeService;
	@Resource private IClassService classService;
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
	
	@InitBinder("pagedialogInfo")  
	public void initPageDialogInfoBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("pagedialogInfo.");
	}
	
	@RequestMapping("/disciplineList")
	public String dodisciplineList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model)throws Exception{
		
		List<DisciplineDomain> disciplineList=disciplineService.doGetPageList(pageInfo);
		List<DisciplineTypeDomain> disciplineTypeList=disciplineTypeService.doGetFilterList();
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("disciplineList", disciplineList);
		model.addAttribute("disciplineTypeList", disciplineTypeList);
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("gradeList", gradeList);
		
		return "/adminView/discipline/disciplineList";
	}
	
	/**
	 * 专业列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/disciplineSearchList")
	public String getdisciplineSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo,Model model
			,String gradeId,String collegeId,String majorId,String classId,String disciplineTypeId
			,@RequestParam(value ="beginTime") @DateTimeFormat(pattern="yyyy-MM-dd") Date beginTime
			,@RequestParam(value ="endTime") @DateTimeFormat(pattern="yyyy-MM-dd") Date endTime
			,String searchText,String sortMode,String sortValue)throws Exception{
		
		//List<DisciplineDomain> disciplineList=disciplineService.doGetPageList(pageInfo);
		List<DisciplineDomain> disciplineList=disciplineService.doSearchPageList(pageInfo,gradeId
				,collegeId,majorId,classId,disciplineTypeId,beginTime,endTime,searchText,sortMode,sortValue);
		List<DisciplineTypeDomain> disciplineTypeList=disciplineTypeService.doGetFilterList();
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("disciplineList", disciplineList);
		model.addAttribute("disciplineTypeList", disciplineTypeList);
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("gradeList", gradeList);
		
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("collegeId", collegeId);
		model.addAttribute("majorId", majorId);
		model.addAttribute("classId", classId);
		model.addAttribute("disciplineTypeId", disciplineTypeId);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("searchText", searchText);
		model.addAttribute("sortMode", sortMode);
		model.addAttribute("sortValue", sortValue);
		
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
		
		List<DisciplineTypeDomain> disciplineTypeList=disciplineTypeService.doGetFilterList();
		model.addAttribute("disciplineTypeList", disciplineTypeList);
		
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
		List<DisciplineTypeDomain> disciplineTypeList=disciplineTypeService.doGetFilterList();
		model.addAttribute("disciplineTypeList", disciplineTypeList);
		
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
	
	/**
	 * 批量删除
	 * @param userIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteDisciplines")
	@ResponseBody
	public String dodeleteDisciplines(@RequestParam(value = "disciplineIds[]") String[] disciplineIds)throws Exception{
		
		if(disciplineService.doDeleteByIds(disciplineIds)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 违纪统计报表页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/disciplineCountView")
	public String dodisciplineCountView(Model model)throws Exception{
		
		List<DisciplineTypeDomain> disciplineTypeList=disciplineTypeService.doGetFilterList();
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("disciplineTypeList", disciplineTypeList);
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("gradeList", gradeList);
		
		return "/adminView/discipline/disciplineCountView";
	}
	
	/**
	 * 导出excel报表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/disciplineExcel")
	@ResponseBody
	public String dodisciplineExcel(Model model,HttpServletResponse response,HttpSession session
			,String gradeId,String collegeId,String majorId,String classId,String disciplineTypeId
			,@RequestParam(value ="countView_beginTime") @DateTimeFormat(pattern="yyyy-MM-dd") Date countView_beginTime
			,@RequestParam(value ="countView_endTime") @DateTimeFormat(pattern="yyyy-MM-dd") Date countView_endTime)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		String filename=username+"_"+System.currentTimeMillis()+".xls";
		/*
		if(countView_beginTime==null&&countView_endTime==null){
			countView_beginTime=DateUtil.getTimesWeekMonday();
			countView_endTime=DateUtil.getTimesWeekSunday();
		}*/
		
		List<DisciplineDomain> disciplineDomains=disciplineService.doSearchList(gradeId, collegeId, majorId, classId, disciplineTypeId, countView_beginTime, countView_endTime);
		
		String title="违纪信息（"+DateUtil.getDayFormat(countView_beginTime)+"至"+DateUtil.getDayFormat(countView_endTime)+"）";
		String fileOutputName=DBToExcelUtil.disciplineCountDBToExcel(disciplineDomains, Consts.DBTOEXCEL_PATH+filename, filename,title);
		if(fileOutputName.equals(filename)){
			return fileOutputName;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 下载违纪报表
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/{fileOutputName}/downloadDisciplineInfo")
	public void dodownloadDisciplineInfo(HttpServletResponse response,@PathVariable String fileOutputName)throws Exception{
		FileUtil.fileDownload(response, Consts.DBTOEXCEL_PATH+fileOutputName, Consts.DISCIPLINE_EXCEL);
		FileUtil.delFile(Consts.DBTOEXCEL_PATH+fileOutputName);
	}
	
	/**
	 * 学生违纪查询
	 * @param pagedialogInfo
	 * @param model
	 * @param gradeId
	 * @param collegeId
	 * @param majorId
	 * @param classId
	 * @param searchText
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentDiscipline")
	public String dostudentDiscipline(@ModelAttribute("pagedialogInfo") PageInfo pagedialogInfo,Model model,
			String gradeId,String collegeId,String majorId,String classId,String searchText)throws Exception{
		
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(collegeId);
		List<SelectItem> classList=classService.dogetClasssByMajorId(majorId);
		List<StudentDomain> studentList=studentService.doSearchstudentPageList(pagedialogInfo, gradeId, collegeId, majorId, classId, searchText, null, null);
		
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("studentList", studentList);
		model.addAttribute("classId", classId);
		model.addAttribute("majorId", majorId);
		model.addAttribute("collegeId", collegeId);
		model.addAttribute("searchText", searchText);
		
		return "/adminView/discipline/studentDiscipline";
	}
	
	/**
	 * 导出excel报表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentDisciplineExcel/{studentId}")
	@ResponseBody
	public String dostudentDisciplineExcel(Model model,HttpServletResponse response,HttpSession session,@PathVariable String studentId)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		String filename=username+"_"+System.currentTimeMillis()+".xls";
		
		List<DisciplineDomain> disciplineDomains=disciplineService.doSearchByStudent(studentId);
		String studnetname=studentService.doGetById(studentId).getName();
		
		String title=studnetname+"违纪信息";
		String fileOutputName=DBToExcelUtil.disciplineCountDBToExcel(disciplineDomains, Consts.DBTOEXCEL_PATH+filename, filename,title);
		if(fileOutputName.equals(filename)){
			return fileOutputName;
		}
		
		return Consts.ERROR;
	}
	
}
