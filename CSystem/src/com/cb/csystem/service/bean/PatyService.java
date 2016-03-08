package com.cb.csystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.IPatyDao;
import com.cb.csystem.domain.PatyDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.service.IPatyService;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.util.CodeBookConsts;
import com.cb.csystem.util.CodeBookConstsType;
import com.cb.system.util.PageInfo;
import com.cb.system.util.ValidateUtil;

/**
 * 党建基本服务类
 * @author Administrator
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class PatyService implements IPatyService{

	@Resource private IPatyDao patyDao;
	@Resource private IStudentService studentService;
	
	/**
	 * @see com.cb.csystem.service.IPatyService#doGetById(java.lang.String)
	 */
	@Override
	public PatyDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return patyDao.getById(id);
	}

	/**
	 * @see com.cb.csystem.service.IPatyService#doGetFilterList()
	 */
	@Override
	public List<PatyDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(PatyDomain.class);
		return patyDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.csystem.service.IPatyService#doGetPageList(com.cb.system.util.PageInfo)
	 */
	@Override
	public List<PatyDomain> doGetPageList(PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(PatyDomain.class);
		return patyDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.csystem.service.IPatyService#doSave(com.cb.csystem.domain.PatyDomain)
	 */
	@Override
	public boolean doSave(PatyDomain patyDomain) throws Exception {
		// TODO Auto-generated method stub
		
//		StudentDomain studentDomain=patyDomain.getStudent();
//		
//		if(patyDomain.getConfirmDate()!=null){
//			//转正，党员
//			studentDomain.setPoliticalStatus(Integer.valueOf(CodeBookConsts.POLITICALSTATUE_TYPE_A));
//		}else{
//			if(patyDomain.getJoinpatyDate()!=null){
//				//预备党员
//				studentDomain.setPoliticalStatus(Integer.valueOf(CodeBookConsts.POLITICALSTATUE_TYPE_B));
//			}else{
//				if(patyDomain.getActiveDate()!=null){
//					
//				}
//				
//			}
//			
//		}
		
		if(patyDomain.getId()==null){
			return patyDao.save(patyDomain);
		}else{
			return patyDao.update(patyDomain);
		}
	}

	/**
	 * @see com.cb.csystem.service.IPatyService#doDeleteById(java.lang.String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return patyDao.deleteById(id);
	}

	/**
	 * @see com.cb.csystem.service.IPatyService#doSearchPatyPageList(com.cb.system.util.PageInfo, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<PatyDomain> doSearchPatyPageList(PageInfo pageInfo,
			String gradeId, String collegeId, String majorId, String classId,
			String searchText, String sortMode, String sortValue)
			throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(PatyDomain.class);
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
		
		if(ValidateUtil.notEmpty(searchText)){
			//多条件过滤，此处名字，学号
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("qstu.name", searchText,MatchMode.ANYWHERE).ignoreCase());  
			disjunction.add(Restrictions.like("qstu.stuId", searchText,MatchMode.ANYWHERE).ignoreCase());  
			detachedCriteria.add(disjunction);
		}
		
		return patyDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.csystem.service.IPatyService#doSearchPatyList(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<PatyDomain> doSearchPatyList(String gradeId, String collegeId,
			String majorId, String classId) throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(PatyDomain.class);
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
		
		return patyDao.getFilterList(detachedCriteria);
	}

}
