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
import com.cb.csystem.domain.GradeDomain;
import com.cb.csystem.domain.PatyDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.service.IClassService;
import com.cb.csystem.service.ICollegeService;
import com.cb.csystem.service.IGradeService;
import com.cb.csystem.service.IMajorService;
import com.cb.csystem.service.IPatyService;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.util.CodeBookConsts;
import com.cb.csystem.util.Consts;
import com.cb.csystem.util.DBToExcelUtil;
import com.cb.system.util.DateUtil;
import com.cb.system.util.FileUtil;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

/**
 * 党建控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/paty")
public class PatyController {

	@Resource private IPatyService patyService;
	@Resource private IMajorService majorService;
	@Resource private ICollegeService collegeService;
	@Resource private IClassService classService;
	@Resource private IGradeService gradeService;
	
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
	 * 党建列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/patyList")
	public String getpatyList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model)throws Exception{
		
		List<PatyDomain> patyList=patyService.doGetPageList(pageInfo);
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("patyList", patyList);
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("gradeList", gradeList);
		
		return "/adminView/paty/patyList";
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
	@RequestMapping("/patySearchList")
	public String dopatySearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String gradeId,String collegeId,String majorId,String classId
			,String searchText,String sortMode,String sortValue)throws Exception{
	
		List<PatyDomain> patyList=patyService.doSearchPatyPageList(pageInfo, gradeId, collegeId, majorId, classId, searchText, sortMode, sortValue);
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(collegeId);
		List<SelectItem> classList=classService.dogetClasssByMajorId(majorId);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("patyList", patyList);
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("classList", classList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("gradeList", gradeList);
		
		model.addAttribute("classId", classId);
		model.addAttribute("majorId", majorId);
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("collegeId", collegeId);
		model.addAttribute("searchText", searchText);
		model.addAttribute("sortMode", sortMode);
		model.addAttribute("sortValue", sortValue);
		
		return "/adminView/paty/patyList";
	}
	
	/**
	 * 党建详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/patyView/{id}")
	public String dopatyView(Model model,@PathVariable String id) throws Exception{
		
		PatyDomain patyDomain=patyService.doGetById(id);
		model.addAttribute("patyDomain", patyDomain);
		
		return "/adminView/paty/patyView";
	}
	
	/**
	 * 新增党建页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/patyAdd")
	public String dopatyAdd(Model model)throws Exception{
		
		return "/adminView/paty/patyAdd";
	}
	
	/**
	 * 修改党建
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/patyEdit/{id}")
	public String dopatyEdit(Model model,@PathVariable String id)throws Exception{
		
		PatyDomain patyDomain=patyService.doGetById(id);
		model.addAttribute("patyDomain", patyDomain);
		
		return "/adminView/paty/patyEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") PatyDomain domain,
			BindingResult result,HttpSession session)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return Consts.ERROR;
		} else {
			if(patyService.doSave(domain)){
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
		
		if(patyService.doDeleteById(id)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 党建上传文件页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/patyExcelToDBView")
	public String dopatyExcelToDBView()throws Exception{
		
		return "/adminView/paty/patyExcelToDBView";
	}
	
	/**
	 * 党建导出信息文件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/patyDBToExcelView")
	public String dopatyDBToExcelView(Model model)throws Exception{
		
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("gradeList", gradeList);
		
		return "/adminView/paty/patyDBToExcelView";
	}
	
	/**
	 * 导出党建信息
	 * @param session
	 * @param gradeId
	 * @param collegeId
	 * @param majorId
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/patyExcelSave")
	@ResponseBody
	public String dopatyExcelSave(HttpSession session,String gradeId
			,String collegeId,String majorId,String classId)throws Exception{
		
		String username=(String)session.getAttribute(Consts.CURRENT_USER);
		String filename=username+"_"+System.currentTimeMillis()+".xls";
		
		List<PatyDomain> patyDomains=patyService.doSearchPatyList(gradeId, collegeId, majorId, classId);
		
		String title="党建信息";
		String fileOutputName=DBToExcelUtil.patyDBToExcel(patyDomains, Consts.DBTOEXCEL_PATH+filename, filename,title);
		if(fileOutputName.equals(filename)){
			return fileOutputName;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 下载党建报表
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/{fileOutputName}/downloadPatyExcel")
	public void dodownloadPatyExcel(HttpServletResponse response,@PathVariable String fileOutputName)throws Exception{
		FileUtil.fileDownload(response, Consts.DBTOEXCEL_PATH+fileOutputName, Consts.PATYOUT_EXCEL);
		FileUtil.delFile(Consts.DBTOEXCEL_PATH+fileOutputName);
	}
	
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/test")
	@ResponseBody
	public String doTest()throws Exception{
		
		List<StudentDomain> studentDomains=studentService.doGetFilterList();
		for(StudentDomain studentDomain:studentDomains){
			PatyDomain patyDomain=studentDomain.getPaty();
			if(patyDomain.getConfirmDate()!=null){
				studentDomain.setPoliticalStatus(Integer.valueOf(CodeBookConsts.POLITICALSTATUE_TYPE_A));
			}else if(patyDomain.getJoinpatyDate()!=null){
				studentDomain.setPoliticalStatus(Integer.valueOf(CodeBookConsts.POLITICALSTATUE_TYPE_B));
			}else if(patyDomain.getActiveDate()!=null||patyDomain.getDevelopDate()!=null){
				studentDomain.setPoliticalStatus(Integer.valueOf(CodeBookConsts.POLITICALSTATUE_TYPE_C));
			}else{
				studentDomain.setPoliticalStatus(Integer.valueOf(CodeBookConsts.POLITICALSTATUE_TYPE_D));
			}
			studentService.doSave(studentDomain);
		}
		
		return Consts.SUCCESS;
	}
}
