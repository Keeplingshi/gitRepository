package com.cb.csystem.service.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.IJobInfoDao;
import com.cb.csystem.domain.ClassDomain;
import com.cb.csystem.domain.CodeBookDomain;
import com.cb.csystem.domain.JobInfoDomain;
import com.cb.csystem.service.IClassService;
import com.cb.csystem.service.ICodeBookService;
import com.cb.csystem.service.IJobInfoService;
import com.cb.csystem.util.CodeBookConsts;
import com.cb.csystem.util.CodeBookConstsType;
import com.cb.csystem.util.Consts;
import com.cb.csystem.util.bean.JobInfoCountBean;
import com.cb.system.util.DateUtil;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;
import com.cb.system.util.ValidateUtil;

/**
 * 就业信息服务层
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class JobInfoService implements IJobInfoService{

	@Resource private IJobInfoDao jobInfoDao;
	@Resource private ICodeBookService codeBookService;
	@Resource private IClassService classService;
	
	/**
	 * @see com.cb.csystem.service.IJobInfoService#doGetById(java.lang.String)
	 */
	@Override
	public JobInfoDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return jobInfoDao.getById(id);
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doGetFilterList()
	 */
	@Override
	public List<JobInfoDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(JobInfoDomain.class);
		return jobInfoDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doGetPageList(com.cb.system.util.PageInfo)
	 */
	@Override
	public List<JobInfoDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(JobInfoDomain.class);
		return jobInfoDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doSave(com.cb.csystem.domain.JobInfoDomain)
	 */
	@Override
	public boolean doSave(JobInfoDomain jobInfoDomain) throws Exception {
		// TODO Auto-generated method stub
		if(jobInfoDomain.getId()==null){
			return jobInfoDao.save(jobInfoDomain);
		}else{
			return jobInfoDao.update(jobInfoDomain);
		}
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doDeleteById(java.lang.String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return jobInfoDao.deleteById(id);
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doSearchjobInfoPageList(com.cb.system.util.PageInfo, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<JobInfoDomain> doSearchjobInfoPageList(PageInfo pageInfo,String gradeId,String majorId
			,String classId,String searchText,String sortMode,String sortValue)throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(JobInfoDomain.class);
		detachedCriteria.createAlias("student", "qstu");
		detachedCriteria.createAlias("qstu.classDomain", "qclazz");
		//班级过滤
		if(ValidateUtil.notEmpty(classId)){
			detachedCriteria.add(Restrictions.eq("qclazz.id", classId));
		}else{
			if(ValidateUtil.notEmpty(majorId)){
				//专业过滤
				detachedCriteria.createAlias("qclazz.major", "qmajor");
				detachedCriteria.add(Restrictions.eq("qmajor.id", majorId));
			}
			if(ValidateUtil.notEmpty(gradeId)){
				//年级过滤
				detachedCriteria.createAlias("qclazz.grade", "qgrade");
				detachedCriteria.add(Restrictions.eq("qgrade.id", gradeId));
			}
		}
		
		if(ValidateUtil.notEmpty(searchText)){
			//多条件过滤，此处名字，学号，公司
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("qstu.name", "%"+searchText+"%",MatchMode.ANYWHERE).ignoreCase());  
			disjunction.add(Restrictions.like("qstu.stuId", "%"+searchText+"%",MatchMode.ANYWHERE).ignoreCase());  
			disjunction.add(Restrictions.like("company", "%"+searchText+"%",MatchMode.ANYWHERE).ignoreCase());  
			
			detachedCriteria.add(disjunction);
		}
		
		if(ValidateUtil.notEmpty(sortValue)){
			if(Consts.SORT_ASC.equals(sortMode)){
				detachedCriteria.addOrder(Order.asc(sortValue));
			}else{
				detachedCriteria.addOrder(Order.desc(sortValue));
			}
		}
		
		return jobInfoDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doDeleteByIds(java.lang.String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] jobInfoIds) throws Exception {
		// TODO Auto-generated method stub
		boolean b=false;
		for(String id:jobInfoIds){
			b=jobInfoDao.deleteById(id);
			if(!b){
				return false;
			}
		}
		
		return b;
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doGetProtocalState(java.lang.String)
	 */
	@Override
	public List<SelectItem> doGetProtocalState(String contractStatusValue)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<SelectItem> selectList=new ArrayList<>();
		//查询类型为 签约书状态
		List<CodeBookDomain> codeBookDomains=codeBookService.doGetCodeBookListByParent(contractStatusValue, CodeBookConstsType.CONTRACTSTATUS_TYPE, CodeBookConstsType.PROTOCALSTATE_TYPE);
		for(CodeBookDomain codeBookDomain:codeBookDomains){
			selectList.add(new SelectItem(codeBookDomain.getValue(),codeBookDomain.getName()));
		}
		
		return selectList;
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doSearchJobInfoList(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<JobInfoDomain> doSearchJobInfoList(String gradeId,
			String collegeId, String majorId, String classId) throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(JobInfoDomain.class);
		detachedCriteria.createAlias("student", "qstu");
		detachedCriteria.createAlias("qstu.classDomain", "qclazz");
		//班级过滤
		if(ValidateUtil.notEmpty(classId)){
			detachedCriteria.add(Restrictions.eq("qclazz.id", classId));
		}else{
			if(ValidateUtil.notEmpty(majorId)){
				//专业过滤
				detachedCriteria.createAlias("qclazz.major", "qmajor");
				detachedCriteria.add(Restrictions.eq("qmajor.id", majorId));
			}else{
				if(ValidateUtil.notEmpty(collegeId)){
					//学院过滤
					detachedCriteria.createAlias("qclazz.major", "qmajor");
					detachedCriteria.createAlias("qmajor.college", "qcollege");
					detachedCriteria.add(Restrictions.eq("qcollege.id", collegeId));
				}
			}
			if(ValidateUtil.notEmpty(gradeId)){
				//年级过滤
				detachedCriteria.createAlias("qclazz.grade", "qgrade");
				detachedCriteria.add(Restrictions.eq("qgrade.id", gradeId));
			}
		}
		
		return jobInfoDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doJobInfoCount(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<SelectItem> doJobInfoCount(String gradeId, String collegeId,
			String majorId, String classId) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(JobInfoDomain.class);
		DetachedCriteria detachedCriteria_A=DetachedCriteria.forClass(JobInfoDomain.class);
		DetachedCriteria detachedCriteria_B=DetachedCriteria.forClass(JobInfoDomain.class);
		DetachedCriteria detachedCriteria_C=DetachedCriteria.forClass(JobInfoDomain.class);
		DetachedCriteria detachedCriteria_D=DetachedCriteria.forClass(JobInfoDomain.class);
		DetachedCriteria detachedCriteria_E=DetachedCriteria.forClass(JobInfoDomain.class);
		DetachedCriteria detachedCriteria_F=DetachedCriteria.forClass(JobInfoDomain.class);
		
		detachedCriteria.createAlias("student", "qstu");
		detachedCriteria.createAlias("qstu.classDomain", "qclazz");
		detachedCriteria_A.createAlias("student", "qstu");
		detachedCriteria_A.createAlias("qstu.classDomain", "qclazz");
		detachedCriteria_B.createAlias("student", "qstu");
		detachedCriteria_B.createAlias("qstu.classDomain", "qclazz");
		detachedCriteria_C.createAlias("student", "qstu");
		detachedCriteria_C.createAlias("qstu.classDomain", "qclazz");
		detachedCriteria_D.createAlias("student", "qstu");
		detachedCriteria_D.createAlias("qstu.classDomain", "qclazz");
		detachedCriteria_E.createAlias("student", "qstu");
		detachedCriteria_E.createAlias("qstu.classDomain", "qclazz");
		detachedCriteria_F.createAlias("student", "qstu");
		detachedCriteria_F.createAlias("qstu.classDomain", "qclazz");
		//班级过滤
		if(ValidateUtil.notEmpty(classId)){
			detachedCriteria.add(Restrictions.eq("qclazz.id", classId));
			detachedCriteria_A.add(Restrictions.eq("qclazz.id", classId));
			detachedCriteria_B.add(Restrictions.eq("qclazz.id", classId));
			detachedCriteria_C.add(Restrictions.eq("qclazz.id", classId));
			detachedCriteria_D.add(Restrictions.eq("qclazz.id", classId));
			detachedCriteria_E.add(Restrictions.eq("qclazz.id", classId));
			detachedCriteria_F.add(Restrictions.eq("qclazz.id", classId));
		}else{
			if(ValidateUtil.notEmpty(majorId)){
				//专业过滤
				detachedCriteria.createAlias("qclazz.major", "qmajor");
				detachedCriteria.add(Restrictions.eq("qmajor.id", majorId));
				detachedCriteria_A.createAlias("qclazz.major", "qmajor");
				detachedCriteria_A.add(Restrictions.eq("qmajor.id", majorId));
				detachedCriteria_B.createAlias("qclazz.major", "qmajor");
				detachedCriteria_B.add(Restrictions.eq("qmajor.id", majorId));
				detachedCriteria_C.createAlias("qclazz.major", "qmajor");
				detachedCriteria_C.add(Restrictions.eq("qmajor.id", majorId));
				detachedCriteria_D.createAlias("qclazz.major", "qmajor");
				detachedCriteria_D.add(Restrictions.eq("qmajor.id", majorId));
				detachedCriteria_E.createAlias("qclazz.major", "qmajor");
				detachedCriteria_E.add(Restrictions.eq("qmajor.id", majorId));
				detachedCriteria_F.createAlias("qclazz.major", "qmajor");
				detachedCriteria_F.add(Restrictions.eq("qmajor.id", majorId));
			}else{
				if(ValidateUtil.notEmpty(collegeId)){
					//学院过滤
					detachedCriteria.createAlias("qclazz.major", "qmajor");
					detachedCriteria.createAlias("qmajor.college", "qcollege");
					detachedCriteria.add(Restrictions.eq("qcollege.id", collegeId));
					detachedCriteria_A.createAlias("qclazz.major", "qmajor");
					detachedCriteria_A.createAlias("qmajor.college", "qcollege");
					detachedCriteria_A.add(Restrictions.eq("qcollege.id", collegeId));
					detachedCriteria_B.createAlias("qclazz.major", "qmajor");
					detachedCriteria_B.createAlias("qmajor.college", "qcollege");
					detachedCriteria_B.add(Restrictions.eq("qcollege.id", collegeId));
					detachedCriteria_C.createAlias("qclazz.major", "qmajor");
					detachedCriteria_C.createAlias("qmajor.college", "qcollege");
					detachedCriteria_C.add(Restrictions.eq("qcollege.id", collegeId));
					detachedCriteria_D.createAlias("qclazz.major", "qmajor");
					detachedCriteria_D.createAlias("qmajor.college", "qcollege");
					detachedCriteria_D.add(Restrictions.eq("qcollege.id", collegeId));
					detachedCriteria_E.createAlias("qclazz.major", "qmajor");
					detachedCriteria_E.createAlias("qmajor.college", "qcollege");
					detachedCriteria_E.add(Restrictions.eq("qcollege.id", collegeId));
					detachedCriteria_F.createAlias("qclazz.major", "qmajor");
					detachedCriteria_F.createAlias("qmajor.college", "qcollege");
					detachedCriteria_F.add(Restrictions.eq("qcollege.id", collegeId));
				}
			}
			if(ValidateUtil.notEmpty(gradeId)){
				//年级过滤
				detachedCriteria.createAlias("qclazz.grade", "qgrade");
				detachedCriteria.add(Restrictions.eq("qgrade.id", gradeId));
				detachedCriteria_A.createAlias("qclazz.grade", "qgrade");
				detachedCriteria_A.add(Restrictions.eq("qgrade.id", gradeId));
				detachedCriteria_B.createAlias("qclazz.grade", "qgrade");
				detachedCriteria_B.add(Restrictions.eq("qgrade.id", gradeId));
				detachedCriteria_C.createAlias("qclazz.grade", "qgrade");
				detachedCriteria_C.add(Restrictions.eq("qgrade.id", gradeId));
				detachedCriteria_D.createAlias("qclazz.grade", "qgrade");
				detachedCriteria_D.add(Restrictions.eq("qgrade.id", gradeId));
				detachedCriteria_E.createAlias("qclazz.grade", "qgrade");
				detachedCriteria_E.add(Restrictions.eq("qgrade.id", gradeId));
				detachedCriteria_F.createAlias("qclazz.grade", "qgrade");
				detachedCriteria_F.add(Restrictions.eq("qgrade.id", gradeId));
			}
		}
		
		List<SelectItem> selectList=new ArrayList<>();
		selectList.add(new SelectItem("查询人数",String.valueOf(jobInfoDao.getTotalCount(detachedCriteria))));
		
		detachedCriteria_C.add(Restrictions.eq("contractStatus", Integer.parseInt(CodeBookConsts.CONTRACTSTATUS_TYPE_C)));
		selectList.add(new SelectItem("保研",String.valueOf(jobInfoDao.getTotalCount(detachedCriteria_C))));
		
		detachedCriteria_D.add(Restrictions.eq("contractStatus", Integer.parseInt(CodeBookConsts.CONTRACTSTATUS_TYPE_D)));
		selectList.add(new SelectItem("考研",String.valueOf(jobInfoDao.getTotalCount(detachedCriteria_D))));
		
		detachedCriteria_A.add(Restrictions.eq("contractStatus", Integer.parseInt(CodeBookConsts.CONTRACTSTATUS_TYPE_A)));
		selectList.add(new SelectItem("已签",String.valueOf(jobInfoDao.getTotalCount(detachedCriteria_A))));
		
		detachedCriteria_B.add(Restrictions.eq("contractStatus", Integer.parseInt(CodeBookConsts.CONTRACTSTATUS_TYPE_B)));
		selectList.add(new SelectItem("未签",String.valueOf(jobInfoDao.getTotalCount(detachedCriteria_B))));
		
		detachedCriteria_E.add(Restrictions.eq("contractStatus", Integer.parseInt(CodeBookConsts.CONTRACTSTATUS_TYPE_E)));
		selectList.add(new SelectItem("考公务员",String.valueOf(jobInfoDao.getTotalCount(detachedCriteria_E))));
		
		selectList.add(new SelectItem("已签约平均工资",String.valueOf(jobInfoDao.getAverageCount(detachedCriteria, "salary"))));
		
		detachedCriteria_F.add(Restrictions.eq("contractStatus", Integer.parseInt(CodeBookConsts.CONTRACTSTATUS_TYPE_F)));
		selectList.add(new SelectItem("出国",String.valueOf(jobInfoDao.getTotalCount(detachedCriteria_F))));
		
		selectList.add(new SelectItem("统计日期",DateUtil.getDay()));
		
		return selectList;
	}

	/**
	 * @see com.cb.csystem.service.IJobInfoService#doJobInfoCountByClassInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public List<JobInfoCountBean> doJobInfoCountByClassInfo(String gradeId,
			String collegeId) throws Exception {
		// TODO Auto-generated method stub
		
		List<JobInfoCountBean> jobInfoCountBeans=new ArrayList<>();
		List<ClassDomain> classDomains=classService.doGetClazzByGradeOrCollegeOrMajor(gradeId, collegeId, null);
		
		for(ClassDomain classDomain:classDomains){
			String classId=classDomain.getId();
			//班级过滤
			if(ValidateUtil.notEmpty(classId)){
				DetachedCriteria detachedCriteria=DetachedCriteria.forClass(JobInfoDomain.class);
				DetachedCriteria detachedCriteria_A=DetachedCriteria.forClass(JobInfoDomain.class);
				DetachedCriteria detachedCriteria_B=DetachedCriteria.forClass(JobInfoDomain.class);
				DetachedCriteria detachedCriteria_C=DetachedCriteria.forClass(JobInfoDomain.class);
				DetachedCriteria detachedCriteria_D=DetachedCriteria.forClass(JobInfoDomain.class);
				DetachedCriteria detachedCriteria_E=DetachedCriteria.forClass(JobInfoDomain.class);
				DetachedCriteria detachedCriteria_F=DetachedCriteria.forClass(JobInfoDomain.class);
				
				detachedCriteria.createAlias("student", "qstu");
				detachedCriteria.createAlias("qstu.classDomain", "qclazz");
				detachedCriteria_A.createAlias("student", "qstu");
				detachedCriteria_A.createAlias("qstu.classDomain", "qclazz");
				detachedCriteria_B.createAlias("student", "qstu");
				detachedCriteria_B.createAlias("qstu.classDomain", "qclazz");
				detachedCriteria_C.createAlias("student", "qstu");
				detachedCriteria_C.createAlias("qstu.classDomain", "qclazz");
				detachedCriteria_D.createAlias("student", "qstu");
				detachedCriteria_D.createAlias("qstu.classDomain", "qclazz");
				detachedCriteria_E.createAlias("student", "qstu");
				detachedCriteria_E.createAlias("qstu.classDomain", "qclazz");
				detachedCriteria_F.createAlias("student", "qstu");
				detachedCriteria_F.createAlias("qstu.classDomain", "qclazz");
				
				detachedCriteria.add(Restrictions.eq("qclazz.id", classId));
				detachedCriteria_A.add(Restrictions.eq("qclazz.id", classId));
				detachedCriteria_B.add(Restrictions.eq("qclazz.id", classId));
				detachedCriteria_C.add(Restrictions.eq("qclazz.id", classId));
				detachedCriteria_D.add(Restrictions.eq("qclazz.id", classId));
				detachedCriteria_E.add(Restrictions.eq("qclazz.id", classId));
				detachedCriteria_F.add(Restrictions.eq("qclazz.id", classId));
				
				JobInfoCountBean jobInfoCountBean=new JobInfoCountBean();
				jobInfoCountBean.setClassName(classDomain.getName());
				jobInfoCountBean.setClassMemberNum(jobInfoDao.getTotalCount(detachedCriteria));
				jobInfoCountBean.setContractState_A(jobInfoDao.getTotalCount(detachedCriteria_A));
				jobInfoCountBean.setContractState_B(jobInfoDao.getTotalCount(detachedCriteria_B));
				jobInfoCountBean.setContractState_C(jobInfoDao.getTotalCount(detachedCriteria_C));
				jobInfoCountBean.setContractState_D(jobInfoDao.getTotalCount(detachedCriteria_D));
				jobInfoCountBean.setContractState_E(jobInfoDao.getTotalCount(detachedCriteria_E));
				jobInfoCountBean.setContractState_F(jobInfoDao.getTotalCount(detachedCriteria_F));
				jobInfoCountBean.setAverageSalary(jobInfoDao.getAverageCount(detachedCriteria, "salary"));
				jobInfoCountBean.setCountDate(DateUtil.getDay());
				
				jobInfoCountBeans.add(jobInfoCountBean);
			}
		}
		
		return jobInfoCountBeans;
	}

}
