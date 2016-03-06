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
import org.springframework.web.bind.annotation.ResponseBody;
import com.cb.csystem.domain.CodeBookDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.domain.UserDomain;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.service.IUserService;
import com.cb.csystem.util.CodeBookConstsType;
import com.cb.csystem.util.CodeBookHelper;
import com.cb.csystem.util.Consts;
import com.cb.csystem.util.DBToExcelUtil;
import com.cb.system.util.FileUtil;
import com.cb.system.util.PageInfo;

/**
 * 学生控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/monitor/student")
public class MStudentController {

	@Resource private IUserService userService;
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
				List<StudentDomain> studentList=studentService.doSearchstudentPageList(pageInfo,null,null,null,userDomain.getClassDomain().getId(),null,null,null);
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
				List<StudentDomain> studentList=studentService.doSearchstudentPageList(pageInfo,null,null,null,userDomain.getClassDomain().getId(),searchText,sortMode,sortValue);
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
			if(studentService.doSaveStuAndOthers(domain)){
				return Consts.SUCCESS;
			}
		}
		return Consts.ERROR;
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

}
