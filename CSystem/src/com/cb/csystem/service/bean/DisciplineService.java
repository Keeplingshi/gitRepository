package com.cb.csystem.service.bean;

import java.util.Date;
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

import com.cb.csystem.dao.IDisciplineDao;
import com.cb.csystem.domain.DisciplineDomain;
import com.cb.csystem.service.IDisciplineService;
import com.cb.csystem.util.Consts;
import com.cb.system.util.PageInfo;
import com.cb.system.util.ValidateUtil;

/**
 * 违纪表基本服务类
 * @author Administrator
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class DisciplineService implements IDisciplineService {

	@Resource private IDisciplineDao disciplineDao;
	
	/**
	 * @see com.cb.csystem.service.IDisciplineService#doGetById(java.lang.String)
	 */
	@Override
	public DisciplineDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return disciplineDao.getById(id);
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineService#doGetFilterList()
	 */
	@Override
	public List<DisciplineDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(DisciplineDomain.class);
		return disciplineDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineService#doGetPageList(com.cb.system.util.PageInfo)
	 */
	@Override
	public List<DisciplineDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(DisciplineDomain.class);
		return disciplineDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineService#doSave(com.cb.csystem.domain.DisciplineDomain)
	 */
	@Override
	public boolean doSave(DisciplineDomain disciplineDomain) throws Exception {
		// TODO Auto-generated method stub
		if(disciplineDomain.getId()==null){
			return disciplineDao.save(disciplineDomain);
		}else{
			return disciplineDao.update(disciplineDomain);
		}
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineService#doDeleteById(java.lang.String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return disciplineDao.deleteById(id);
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineService#doSearchPageList(com.cb.system.util.PageInfo, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<DisciplineDomain> doSearchPageList(PageInfo pageInfo,
			String gradeId, String collegeId, String majorId, String classId,
			String disciplineTypeId, Date beginTime, Date endTime,
			String searchText, String sortMode, String sortValue)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(DisciplineDomain.class);
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
			//多条件过滤，此处名字，学号，公司
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("qstu.name", searchText,MatchMode.ANYWHERE).ignoreCase());  
			disjunction.add(Restrictions.like("qstu.stuId", searchText,MatchMode.ANYWHERE).ignoreCase());  
			detachedCriteria.add(disjunction);
		}
		//违纪类型
		if(ValidateUtil.notEmpty(disciplineTypeId)){
			detachedCriteria.add(Restrictions.eq("disciplineType.id", disciplineTypeId));
		}
		
		//时间
		if(beginTime!=null){
			//大于等于
			detachedCriteria.add(Restrictions.ge("time", beginTime));
		}
		if(endTime!=null){
			//小于等于
			detachedCriteria.add(Restrictions.le("time", endTime));
		}
		
		//排序
		if(ValidateUtil.notEmpty(sortValue)){
			if(Consts.SORT_ASC.equals(sortMode)){
				detachedCriteria.addOrder(Order.asc(sortValue));
			}else{
				detachedCriteria.addOrder(Order.desc(sortValue));
			}
		}
		
		return disciplineDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineService#doDeleteByIds(java.lang.String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] disciplineIds) throws Exception {
		// TODO Auto-generated method stub
		boolean b=false;
		for(String id:disciplineIds){
			b=disciplineDao.deleteById(id);
			if(!b){
				return false;
			}
		}
		return b;
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineService#doSearchList(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<DisciplineDomain> doSearchList(String gradeId,
			String collegeId, String majorId, String classId,
			String disciplineTypeId, Date beginTime, Date endTime)
			throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(DisciplineDomain.class);
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

		//违纪类型
		if(ValidateUtil.notEmpty(disciplineTypeId)){
			detachedCriteria.add(Restrictions.eq("disciplineType.id", disciplineTypeId));
		}
		
		//时间
		if(beginTime!=null){
			//大于等于
			detachedCriteria.add(Restrictions.ge("time", beginTime));
		}
		if(endTime!=null){
			//小于等于
			detachedCriteria.add(Restrictions.le("time", endTime));
		}
		
		//排序
		detachedCriteria.addOrder(Order.asc("time"));
		
		return disciplineDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineService#doSearchByStudent(java.lang.String)
	 */
	@Override
	public List<DisciplineDomain> doSearchByStudent(String studentId)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(DisciplineDomain.class);
		detachedCriteria.add(Restrictions.eq("student.id", studentId));
		
		return disciplineDao.getFilterList(detachedCriteria);
	}

}
