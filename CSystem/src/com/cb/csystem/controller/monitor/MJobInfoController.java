package com.cb.csystem.controller.monitor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

import com.cb.csystem.domain.CollegeDomain;
import com.cb.csystem.domain.GradeDomain;
import com.cb.csystem.domain.JobInfoDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.service.IClassService;
import com.cb.csystem.service.ICollegeService;
import com.cb.csystem.service.IGradeService;
import com.cb.csystem.service.IJobInfoService;
import com.cb.csystem.service.IMajorService;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.util.CodeBookConsts;
import com.cb.csystem.util.CodeBookConstsType;
import com.cb.csystem.util.CodeBookHelper;
import com.cb.csystem.util.Consts;
import com.cb.csystem.util.DBToExcelUtil;
import com.cb.csystem.util.bean.JobInfoCountBean;
import com.cb.system.util.DateUtil;
import com.cb.system.util.FileUtil;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

/**
 * 就业信息控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/monitor/jobInfo")
public class MJobInfoController {

	@Resource private IJobInfoService jobInfoService;
	@Resource private IStudentService studentService;
	@Resource private IMajorService majorService;
	@Resource private ICollegeService collegeService;
	@Resource private IClassService classService;
	@Resource private IGradeService gradeService;
	
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
	 * 就业列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoList")
	public String getjobInfoList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model)throws Exception{
		
		List<JobInfoDomain> jobInfoList=jobInfoService.doGetPageList(pageInfo);
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("jobInfoList", jobInfoList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("gradeList", gradeList);
		
		return "/monitorView/jobInfo/jobInfoList";
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
	@RequestMapping("/jobInfoSearchList")
	public String dojobInfoSearchList(@ModelAttribute("pageInfo") PageInfo pageInfo
			,BindingResult bindingResult,Model model,String gradeId,String majorId
			,String classId,String searchText,String sortMode,String sortValue)throws Exception{
	
		List<JobInfoDomain> jobInfoList=jobInfoService.doSearchjobInfoPageList(pageInfo,gradeId,majorId,classId,searchText, sortMode, sortValue);
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(majorId);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();

		model.addAttribute("jobInfoList", jobInfoList);
		model.addAttribute("classList", classList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("gradeList", gradeList);
		
		model.addAttribute("classId", classId);
		model.addAttribute("majorId", majorId);
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("searchText", searchText);
		model.addAttribute("sortMode", sortMode);
		model.addAttribute("sortValue", sortValue);
		
		return "/monitorView/jobInfo/jobInfoList";
	}
	
	/**
	 * 就业详情页面
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoView/{id}")
	public String dojobInfoView(Model model,@PathVariable String id) throws Exception{
		
		StudentDomain studentDomain=studentService.doGetById(id);
		model.addAttribute("studentDomain", studentDomain);
		
		JobInfoDomain jobInfoDomain=jobInfoService.doGetById(id);
		model.addAttribute("jobInfoDomain", jobInfoDomain);
		
		return "/monitorView/jobInfo/jobInfoView";
	}
	
	/**
	 * 新增就业页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoAdd")
	public String dojobInfoAdd(Model model)throws Exception{
		
		return "/monitorView/jobInfo/jobInfoAdd";
	}
	
	/**
	 * 修改就业
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoEdit/{id}")
	public String dojobInfoEdit(Model model,@PathVariable String id)throws Exception{
		
		JobInfoDomain jobInfoDomain=jobInfoService.doGetById(id);
		model.addAttribute("jobInfoDomain", jobInfoDomain);
		model.addAttribute("contractStatusList", CodeBookHelper.getCodeBookByType(CodeBookConstsType.CONTRACTSTATUS_TYPE));
		model.addAttribute("protocalStateList", CodeBookHelper.getCodeBookByType(CodeBookConstsType.PROTOCALSTATE_TYPE));
		model.addAttribute("nowStateList", CodeBookHelper.getCodeBookByType(CodeBookConstsType.NOWSTATE_TYPE));
		
		return "/monitorView/jobInfo/jobInfoEdit";
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
	public String doSave(@Valid @ModelAttribute("domain") JobInfoDomain domain,
			BindingResult result,HttpSession session)throws Exception{
		if (result.hasErrors()) {// 如果校验失败,则返回
			return Consts.ERROR;
		} else {
			//获取当前登录用户名
			String username=(String) session.getAttribute(Consts.CURRENT_USER);
			String modifyTime=DateUtil.getSdfMinute()+" "+username;
			domain.setModifyTime(modifyTime);
			if(jobInfoService.doSave(domain)){
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
		
		if(jobInfoService.doDeleteById(id)){
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
	@RequestMapping("/deleteJobInfos")
	@ResponseBody
	public String doDeleteJobInfos(@RequestParam(value = "jobInfoIds[]") String[] jobInfoIds)throws Exception{
		
		if(jobInfoService.doDeleteByIds(jobInfoIds)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 根据签约状态获取协议书状态
	 * @param model
	 * @param college_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getProtocalState")
	@ResponseBody
	public String doGetProtocalState(String contractStatusValue)throws Exception{
		
		List<SelectItem> protocalStateList=jobInfoService.doGetProtocalState(contractStatusValue);
		
		JSONArray jsonArray=JSONArray.fromObject(protocalStateList);
		return jsonArray.toString();
		
	}
	
	/**
	 * 标记积极不积极
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/markIsPositive/{id}")
	@ResponseBody
	public String doMarkIsPositive(@PathVariable String id)throws Exception{
		
		JobInfoDomain jobInfoDomain=jobInfoService.doGetById(id);
		if(jobInfoDomain==null){
			return Consts.ERROR;
		}
		//null或者1，设置成2
		//2，设置成1
		if(jobInfoDomain.getIsPositive()==null){
			jobInfoDomain.setIsPositive(new Integer(CodeBookConsts.ISPOSITIVE_TYPE_B));
		}else{
			if(CodeBookConsts.ISPOSITIVE_TYPE_A.equals(jobInfoDomain.getIsPositive().toString())){
				jobInfoDomain.setIsPositive(new Integer(CodeBookConsts.ISPOSITIVE_TYPE_B));
			}else{
				jobInfoDomain.setIsPositive(new Integer(CodeBookConsts.ISPOSITIVE_TYPE_A));
			}		
		}

		if(jobInfoService.doSave(jobInfoDomain)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 就业信息导出页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoDBToExcelView")
	public String doJobInfoDBToExcelView(Model model)throws Exception{
		
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(null);
		List<SelectItem> classList=classService.dogetClasssByMajorId(null);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("gradeList", gradeList);
		
		return "/monitorView/jobInfo/jobInfoDBToExcelView";
	}
	
	/**
	 * 导出就业信息
	 * @param gradeId
	 * @param collegeId
	 * @param majorId
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoDBToExcel")
	@ResponseBody
	public String dojobInfoDBToExcel(String gradeId,String collegeId,String majorId,String classId)throws Exception{
		
		List<JobInfoDomain> jobInfoDomains=jobInfoService.doSearchJobInfoList(gradeId,collegeId, majorId, classId);
		List<SelectItem> selectItems=jobInfoService.doJobInfoCount(gradeId, collegeId, majorId, classId);
		boolean b=DBToExcelUtil.jobInfoDBToExcel(jobInfoDomains, selectItems,Consts.DBTOEXCEL_PATH+Consts.JOBINFO_EXCEL);
		
		if(b){
			return Consts.SUCCESS;
		}else{
			return Consts.ERROR;
		}
	}
	
	/**
	 * 下载就业信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadJobInfo")
	public void dodownloadJobInfo(HttpServletResponse response)throws Exception{
		FileUtil.fileDownload(response, Consts.DBTOEXCEL_PATH+Consts.JOBINFO_EXCEL, Consts.JOBINFO_EXCEL);
	}
	
	/**
	 * 就业统计信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoCountView")
	public String dojobInfoCountView(Model model,String gradeId,String collegeId,String majorId,String classId)throws Exception{
		
		List<SelectItem> jobInfoCountList=jobInfoService.doJobInfoCount(gradeId, collegeId, majorId, classId);
		
		List<CollegeDomain> collegeList=collegeService.doGetFilterList();
		List<SelectItem> majorList=majorService.dogetMajorsByCollegeId(collegeId);
		List<SelectItem> classList=classService.dogetClasssByMajorId(majorId);
		List<GradeDomain> gradeList=gradeService.doGetFilterList();
		
		model.addAttribute("jobInfoCountList", jobInfoCountList);
		
		model.addAttribute("collegeList", collegeList);
		model.addAttribute("majorList", majorList);
		model.addAttribute("classList", classList);
		model.addAttribute("gradeList", gradeList);
		
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("collegeId", collegeId);
		model.addAttribute("classId", classId);
		model.addAttribute("majorId", majorId);
		
		return "/monitorView/jobInfo/jobInfoCountView";
	}
	
	/**
	 * 就业统计信息导出
	 * @param gradeId
	 * @param collegeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jobInfoCountDBToExcel")
	@ResponseBody
	public String dojobInfoCountDBToExcel(String gradeId,String collegeId)throws Exception{
		
		List<JobInfoCountBean> jobInfoCountBeans=jobInfoService.doJobInfoCountByClassInfo(gradeId, collegeId);
		if(DBToExcelUtil.jobInfoCountDBToExcel(jobInfoCountBeans, Consts.DBTOEXCEL_PATH+Consts.JOBCOUNT_EXCEL)){
			return Consts.SUCCESS;
		}
		
		return Consts.ERROR;
	}
	
	/**
	 * 下载就业统计信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadJobCount")
	public void dodownloadJobCount(HttpServletResponse response)throws Exception{
		FileUtil.fileDownload(response, Consts.DBTOEXCEL_PATH+Consts.JOBCOUNT_EXCEL, Consts.JOBCOUNT_EXCEL);
	}
}
