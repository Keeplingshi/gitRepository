package com.cb.csystem.controller.monitor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import com.cb.csystem.domain.UserDomain;
import com.cb.csystem.service.IClassService;
import com.cb.csystem.service.ICollegeService;
import com.cb.csystem.service.IGradeService;
import com.cb.csystem.service.IMajorService;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.service.IUserService;
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
@RequestMapping("/monitor/student")
public class MStudentController {

	@Resource private IUserService userService;
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
	
	/**
	 * 学生列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentList")
	public String getstudentList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,HttpSession session)throws Exception{
		
		//获取当前登录用户名
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getClassDomain()!=null){
				List<StudentDomain> studentList=studentService.doSearchstudentPageList(pageInfo,null,null,userDomain.getClassDomain().getId(),null,null,null);
				model.addAttribute("studentList", studentList);
			}
		}
		
		return "/monitorView/student/studentList";
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
			,BindingResult bindingResult,Model model,String searchText,String sortMode,String sortValue,HttpSession session)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getClassDomain()!=null){
				List<StudentDomain> studentList=studentService.doSearchstudentPageList(pageInfo,null,null,userDomain.getClassDomain().getId(),searchText,sortMode,sortValue);
				model.addAttribute("studentList", studentList);
				
				model.addAttribute("searchText", searchText);
				model.addAttribute("sortMode", sortMode);
				model.addAttribute("sortValue", sortValue);
			}
		}
	
		return "/monitorView/student/studentList";
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
		
		return "/monitorView/student/studentView";
	}
	
	/**
	 * 新增学生页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentAdd")
	public String dostudentAdd(Model model,HttpSession session)throws Exception{
		
		List<CodeBookDomain> politicalStatusList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.POLITICALSTATUE_TYPE);
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getClassDomain()!=null){
				model.addAttribute("userDomain", userDomain);
			}
		}
		model.addAttribute("politicalStatusList", politicalStatusList);
		
		return "/monitorView/student/studentAdd";
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
		List<CodeBookDomain> politicalStatusList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.POLITICALSTATUE_TYPE);
		
		model.addAttribute("politicalStatusList", politicalStatusList);
		model.addAttribute("studentDomain", studentDomain);
		
		return "/monitorView/student/studentEdit";
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
			return Consts.ERROR;
		} else {
			if(studentService.doSave(domain)){
				return Consts.SUCCESS;
			}
		}
		return Consts.ERROR;
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
		
		return "/monitorView/student/studentDBToExcelView";
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
	public String dostudentDBToExcel(HttpServletResponse response,HttpSession session)throws Exception{
		
		boolean b=false;
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getClassDomain()!=null){
				List<StudentDomain> studentList=studentService.doSearchstudentList(null, null, null, userDomain.getClassDomain().getId());
				b=DBToExcelUtil.studnetinfoDBToExcel(studentList, Consts.DBTOEXCEL_PATH+Consts.STUDENT_EXCEL);
			}
		}
		
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
	
//	/**
//	 * 删除单条数据
//	 * @param id
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/delete/{id}")
//	@ResponseBody
//	public String doDelete(@PathVariable String id)throws Exception{
//		
//		if(studentService.doDeleteById(id)){
//			return Consts.SUCCESS;
//		}
//		
//		return Consts.ERROR;
//	}
	
//	/**
//	 * 批量删除
//	 * @param userIds
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/deleteStudents")
//	@ResponseBody
//	public String doDeleteStudents(@RequestParam(value = "studentIds[]") String[] studentIds)throws Exception{
//		
//		if(studentService.doDeleteByIds(studentIds)){
//			return Consts.SUCCESS;
//		}
//		
//		return Consts.ERROR;
//	}
	
//	/**
//	 * 从excel中导入学生信息页面
//	 * @param model
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/studentExcelView")
//	public String dostudentExcelView(Model model)throws Exception{
//		
//		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
//		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
//		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
//		List<GradeDomain> gradeList=gradeService.doGetFilterList();
//		
//		model.addAttribute("collegeList", collegeList);
//		model.addAttribute("majorList", majorList);
//		model.addAttribute("classList", classList);
//		model.addAttribute("gradeList", gradeList);
//		
//		return "/monitorView/student/studentExcelView";
//	};
	

	
//	/**
//	 * 将excel文件中学生信息写入数据库
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/studentExcelSave")
//	@ResponseBody
//	public String dostudentExcelSave(@RequestParam(value = "file", required = false) MultipartFile file,String classId)
//	{
//		try{
//			ClassDomain classDomain=classService.doGetById(classId);
//			ExcelToDBUtil.studentInfoexcelToDB(file,classDomain);
//		}catch (Exception e) {
//			return Consts.ERROR;
//		}
//		
//		return Consts.SUCCESS;
//	}
	
//	/**
//	 * 下载模板
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping("/downloadStudentExcel")
//	public void dodownloadStudentExcel(HttpServletResponse response)throws Exception{
//		FileUtil.fileDownload(response, Consts.DOWNLOAD_PATH+Consts.STUDENT_EXCEL, Consts.STUDENT_EXCEL);
//	}

}
