package com.cb.csystem.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.cb.csystem.domain.ClassDomain;
import com.cb.csystem.domain.CodeBookDomain;
import com.cb.csystem.domain.CollegeDomain;
import com.cb.csystem.domain.GradeDomain;
import com.cb.csystem.domain.MajorDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.service.IClassService;
import com.cb.csystem.service.ICollegeService;
import com.cb.csystem.service.IGradeService;
import com.cb.csystem.service.IMajorService;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.util.CodeBookConstsType;
import com.cb.csystem.util.CodeBookHelper;
import com.cb.csystem.util.Consts;
import com.cb.csystem.util.DBToExcelUtil;
import com.cb.csystem.util.ExcelToDBUtil;
import com.cb.system.util.FileUtil;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

/**
 * 学生控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/admin/student")
public class StudentController {

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
	
	/**
	 * 学生列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentList")
	public String getstudentList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model)throws Exception{
		
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
		List<StudentDomain> studentList=studentService.doGetPageList(pageInfo);
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("studentList", studentList);
		
		return "/adminView/student/studentList";
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
	@RequestMapping("/studentSearchList")
	public String dostudentSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String collegeId,String majorId
			,String classId,String searchText,String sortMode,String sortValue)throws Exception{
		
		List<StudentDomain> studentList=studentService.doSearchstudentPageList(pageInfo,null,collegeId,majorId,classId,searchText,sortMode,sortValue);
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(collegeId);
		List<SelectItem> classList=classService.dogetClasssByMajorId(majorId);

		model.addAttribute("studentList", studentList);
		model.addAttribute("classList", classList);
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classId", classId);
		model.addAttribute("majorId", majorId);
		model.addAttribute("collegeId", collegeId);
		model.addAttribute("searchText", searchText);
		model.addAttribute("sortMode", sortMode);
		model.addAttribute("sortValue", sortValue);
	
		return "/adminView/student/studentList";
	}
	
	/**
	 * 学生详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentView/{id}")
	public String dostudentView(Model model,@PathVariable String id) throws Exception{
		
		//获取student信息
		StudentDomain studentDomain=studentService.doGetById(id);
		model.addAttribute("studentDomain", studentDomain);
		
		return "/adminView/student/studentView";
	}
	
	/**
	 * 新增学生页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentAdd")
	public String dostudentAdd(Model model)throws Exception{
		
		List<CodeBookDomain> politicalStatusList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.POLITICALSTATUE_TYPE);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		List<ClassDomain> classList=classService.doGetFilterList();
		List<MajorDomain> majorList=majorService.doGetFilterList();
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		
		model.addAttribute("politicalStatusList", politicalStatusList);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("classList", classList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("collegeList", collegeList);
		
		return "/adminView/student/studentAdd";
	}
	
	/**
	 * 修改学生
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentEdit/{id}")
	public String dostudentEdit(Model model,@PathVariable String id)throws Exception{
		
		//获取student信息
		StudentDomain studentDomain=studentService.doGetById(id);
		List<MajorDomain> majorList=majorService.doGetFilterList();
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<ClassDomain> classList=classService.doGetFilterList();
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		List<CodeBookDomain> politicalStatusList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.POLITICALSTATUE_TYPE);
		
		model.addAttribute("politicalStatusList", politicalStatusList);
		model.addAttribute("studentDomain", studentDomain);
		model.addAttribute("classList", classList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("gradeList", gradeList);
		
		return "/adminView/student/studentEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") StudentDomain domain,
			BindingResult result)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			System.out.println(result);
			return Consts.ERROR;
		} else {
			if(studentService.doSaveStuAndOthers(domain)){
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
		
		if(studentService.doDeleteById(id)){
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
	@RequestMapping("/deleteStudents")
	@ResponseBody
	public String doDeleteStudents(@RequestParam(value = "studentIds[]") String[] studentIds)throws Exception{
		
		if(studentService.doDeleteByIds(studentIds)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 从excel中导入学生信息页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentExcelView")
	public String dostudentExcelView(Model model)throws Exception{
		
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("gradeList", gradeList);
		
		return "/adminView/student/studentExcelView";
	}
	
	/**
	 * 导出数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentDBToExcelView")
	public String dostudentDBToExcelView(Model model)throws Exception{
		
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("gradeList", gradeList);
		
		return "/adminView/student/studentDBToExcelView";
	}
	
	/**
	 * 将excel文件中学生信息写入数据库
	 * @param request
	 * @return
	 */
	@RequestMapping("/studentExcelSave")
	@ResponseBody
	public String dostudentExcelSave(@RequestParam(value = "file", required = false) MultipartFile file,String classId)
	{
		try{
			ClassDomain classDomain=classService.doGetById(classId);
			ExcelToDBUtil.studentInfoexcelToDB(file,classDomain);
		}catch (Exception e) {
			return Consts.ERROR;
		}
		
		return Consts.SUCCESS;
	}
	
	/**
	 * 下载模板
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadStudentExcel")
	public void dodownloadStudentExcel(HttpServletResponse response)throws Exception{
		FileUtil.fileDownload(response, Consts.DOWNLOAD_PATH+Consts.STUDENT_EXCEL, Consts.STUDENT_EXCEL);
	}
	
	/**
	 * 学生信息导出文件
	 * @param gradeId
	 * @param classId
	 * @param majorId
	 * @param collegeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentDBToExcel")
	@ResponseBody
	public String dostudentDBToExcel(HttpServletResponse response,String gradeId,String collegeId,String majorId,String classId)throws Exception{
		
		List<StudentDomain> studentDomains=studentService.doSearchstudentList(gradeId,collegeId, majorId, classId);
		boolean b=DBToExcelUtil.studnetinfoDBToExcel(studentDomains, Consts.DBTOEXCEL_PATH+Consts.STUDENT_EXCEL);
		
		if(b){
			return Consts.SUCCESS;
		}else{
			return Consts.ERROR;
		}
		
	}
	
	/**
	 * 下载学生信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadStudentInfo")
	public void dodownloadStudentInfo(HttpServletResponse response)throws Exception{
		FileUtil.fileDownload(response, Consts.DBTOEXCEL_PATH+Consts.STUDENT_EXCEL, Consts.STUDENT_EXCEL);
	}
	
	/**
	 * 学生选择页面
	 * @param pageInfo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentChooseView")
	public String dostudentChooseView(@ModelAttribute("pagedialogInfo") PageInfo pagedialogInfo,Model model,
			String collegeId,String majorId,String classId,String searchText)throws Exception{
		
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(collegeId);
		List<SelectItem> classList=classService.dogetClasssByMajorId(majorId);
		List<StudentDomain> studentList=studentService.doSearchstudentPageList(pagedialogInfo, null, collegeId, majorId, classId, searchText, null, null);
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("studentList", studentList);
		model.addAttribute("classId", classId);
		model.addAttribute("majorId", majorId);
		model.addAttribute("collegeId", collegeId);
		model.addAttribute("searchText", searchText);
		
		return "/adminView/student/studentChooseView";
	}
	
}
