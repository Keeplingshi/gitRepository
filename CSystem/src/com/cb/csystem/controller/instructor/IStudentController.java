package com.cb.csystem.controller.instructor;

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
import com.cb.csystem.domain.CodeBookDomain;
import com.cb.csystem.domain.CollegeDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.domain.UserDomain;
import com.cb.csystem.service.IClassService;
import com.cb.csystem.service.IMajorService;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.service.IUserService;
import com.cb.csystem.util.CodeBookConstsType;
import com.cb.csystem.util.CodeBookHelper;
import com.cb.csystem.util.Consts;
import com.cb.csystem.util.DBToExcelUtil;
import com.cb.system.util.FileUtil;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

/**
 * 学生控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/instructor/student")
public class IStudentController {

	@Resource private IUserService userService;
	@Resource private IMajorService majorService;
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
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				
				List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(userDomain.getCollege().getId());
				List<SelectItem> classList=classService.doGetClazzSelectItem(userDomain.getGrade().getId(), userDomain.getCollege().getId(), null);
				List<StudentDomain> studentList=studentService.doSearchstudentPageList(pageInfo, userDomain.getGrade().getId() 
						,userDomain.getCollege().getId(), null, null, null, null, null);
				
				model.addAttribute("majorList", majorList);
				model.addAttribute("classList", classList);
				model.addAttribute("studentList", studentList);
			}
		}
		
		return "/instructorView/student/studentList";
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
			,BindingResult bindingResult,Model model,HttpSession session,String majorId
			,String classId,String searchText,String sortMode,String sortValue)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				
				List<StudentDomain> studentList=studentService.doSearchstudentPageList(pageInfo, userDomain.getGrade().getId() 
						,userDomain.getCollege().getId(), majorId, classId, searchText, sortMode, sortValue);
				
				//查询班级专业
				List<SelectItem> classList=classService.doGetClazzSelectItem(userDomain.getGrade().getId(), userDomain.getCollege().getId(), majorId);
				List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(userDomain.getCollege().getId());
				
				model.addAttribute("majorList", majorList);
				model.addAttribute("classList", classList);
				model.addAttribute("studentList", studentList);
				
				model.addAttribute("classId", classId);
				model.addAttribute("majorId", majorId);
				model.addAttribute("searchText", searchText);
				model.addAttribute("sortMode", sortMode);
				model.addAttribute("sortValue", sortValue);
			}
		}
	
		return "/instructorView/student/studentList";
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
		
		return "/instructorView/student/studentView";
	}
	
	/**
	 * 新增学生页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentAdd")
	public String dostudentAdd(Model model,HttpSession session)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				
				List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(userDomain.getCollege().getId());
				List<SelectItem> classList=classService.doGetClazzSelectItem(userDomain.getGrade().getId(), userDomain.getCollege().getId(), null);
				List<CodeBookDomain> politicalStatusList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.POLITICALSTATUE_TYPE);
				
				model.addAttribute("userDomain", userDomain);
				model.addAttribute("majorList", majorList);
				model.addAttribute("classList", classList);
				model.addAttribute("politicalStatusList", politicalStatusList);
			}
		}
		
		return "/instructorView/student/studentAdd";
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
		String collegeId=studentDomain.getClassDomain().getMajor().getCollege().getId();
		String gradeId=studentDomain.getClassDomain().getGrade().getId();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(collegeId);
		List<SelectItem> classList=classService.doGetClazzSelectItem(gradeId, collegeId, null);
		List<CodeBookDomain> politicalStatusList=CodeBookHelper.getCodeBookByType(CodeBookConstsType.POLITICALSTATUE_TYPE);
		
		model.addAttribute("politicalStatusList", politicalStatusList);
		model.addAttribute("studentDomain", studentDomain);
		model.addAttribute("classList", classList);
		model.addAttribute("majorList", majorList);
		
		return "/instructorView/student/studentEdit";
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
	public String dostudentExcelView(Model model,HttpSession session)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				
				List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(userDomain.getCollege().getId());
				List<SelectItem> classList=classService.doGetClazzSelectItem(userDomain.getGrade().getId(), userDomain.getCollege().getId(), null);
				
				model.addAttribute("majorList", majorList);
				model.addAttribute("classList", classList);
			}
		}
		
		return "/instructorView/student/studentExcelView";
	};
	
	/**
	 * 导出数据
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentDBToExcelView")
	public String dostudentDBToExcelView(Model model,HttpSession session)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				
				List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(userDomain.getCollege().getId());
				List<SelectItem> classList=classService.doGetClazzSelectItem(userDomain.getGrade().getId(), userDomain.getCollege().getId(), null);
				
				model.addAttribute("majorList", majorList);
				model.addAttribute("classList", classList);
			}
		}
		
		return "/instructorView/student/studentDBToExcelView";
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
	public String dostudentDBToExcel(HttpServletResponse response,HttpSession session,String majorId,String classId)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		boolean b=false;
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				String gradeId=userDomain.getGrade().getId();
				String collegeId=userDomain.getCollege().getId();
				List<StudentDomain> studentDomains=studentService.doSearchstudentList(gradeId,collegeId, majorId, classId);
				b=DBToExcelUtil.studnetinfoDBToExcel(studentDomains, Consts.DBTOEXCEL_PATH+Consts.STUDENT_EXCEL);
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
	
	/**
	 * 学生选择页面
	 * @param pageInfo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/studentChooseView")
	public String dostudentChooseView(@ModelAttribute("pagedialogInfo") PageInfo pagedialogInfo,Model model,
			HttpSession session,String majorId,String classId,String searchText)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(userDomain.getCollege()!=null&&userDomain.getGrade()!=null){
				
				String collegeId=userDomain.getCollege().getId();
				String gradeId=userDomain.getGrade().getId();
				
				List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(collegeId);
				List<SelectItem> classList=classService.doGetClazzSelectItem(gradeId, collegeId, majorId);
				List<StudentDomain> studentList=studentService.doSearchstudentPageList(pagedialogInfo, gradeId, collegeId, majorId, classId, searchText, null, null);
				
				model.addAttribute("majorList", majorList);
				model.addAttribute("classList", classList);
				model.addAttribute("studentList", studentList);
				model.addAttribute("classId", classId);
				model.addAttribute("majorId", majorId);
				model.addAttribute("searchText", searchText);
			}
		}
		
		return "/instructorView/student/studentChooseView";
	}
}
