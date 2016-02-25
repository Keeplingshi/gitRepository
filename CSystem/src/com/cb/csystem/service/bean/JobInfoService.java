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
import com.cb.csystem.domain.CodeBookDomain;
import com.cb.csystem.domain.JobInfoDomain;
import com.cb.csystem.service.ICodeBookService;
import com.cb.csystem.service.IJobInfoService;
import com.cb.csystem.util.CodeBookConstsType;
import com.cb.csystem.util.Consts;
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

}
