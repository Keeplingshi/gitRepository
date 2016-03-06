package com.cb.csystem.service.bean;

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

import com.cb.csystem.dao.IStudentDao;
import com.cb.csystem.domain.JobInfoDomain;
import com.cb.csystem.domain.PatyDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.service.IJobInfoService;
import com.cb.csystem.service.IPatyService;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.util.Consts;
import com.cb.system.util.PageInfo;
import com.cb.system.util.ValidateUtil;

/**
 * 学生基础服务类
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class StudentService implements IStudentService{

	private @Resource IStudentDao studentDao;
	private @Resource IJobInfoService jobInfoService;
	private @Resource IPatyService patyService;
	
	/**
	 * @see com.cb.csystem.service.IStudentService#doGetById(java.lang.String)
	 */
	@Override
	public StudentDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.getById(id);
	}

	/**
	 * @see com.cb.csystem.service.IStudentService#doGetFilterList()
	 */
	@Override
	public List<StudentDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(StudentDomain.class);
		
		return studentDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.csystem.service.IStudentService#doGetPageList(com.cb.system.util.PageInfo)
	 */
	@Override
	public List<StudentDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(StudentDomain.class);
		
		return studentDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.csystem.service.IStudentService#doSaveStuAndOthers(com.cb.csystem.domain.StudentDomain)
	 */
	@Override
	public boolean doSaveStuAndOthers(StudentDomain studentDomain) throws Exception {
		// TODO Auto-generated method stub
		if(studentDomain.getId()==null){
			JobInfoDomain jobInfoDomain=new JobInfoDomain();
			jobInfoDomain.setStudent(studentDomain);
			jobInfoService.doSave(jobInfoDomain);
			PatyDomain patyDomain=new PatyDomain();
			patyDomain.setStudent(studentDomain);
			patyService.doSave(patyDomain);
			return studentDao.save(studentDomain);
		}else{
			return studentDao.update(studentDomain);
		}
	}

	/**
	 * @see com.cb.csystem.service.IStudentService#doDeleteById(java.lang.String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.deleteById(id);
	}

	/**
	 * @see com.cb.csystem.service.IStudentService#doDeleteByIds(java.lang.String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] studentIds) throws Exception {
		// TODO Auto-generated method stub
		boolean b=false;
		for(String id:studentIds){
			b=studentDao.deleteById(id);
			if(!b){
				return false;
			}
		}
		
		return b;
	}

	/**
	 * @see com.cb.csystem.service.IStudentService#doSearchstudentPageList(com.cb.system.util.PageInfo, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<StudentDomain> doSearchstudentPageList(PageInfo pageInfo,String gradeId,
			String collegeId, String majorId, String classId, String searchText,String sortMode,String sortValue)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(StudentDomain.class);
		
		//班级过滤
		if(ValidateUtil.notEmpty(classId)){
			detachedCriteria.add(Restrictions.eq("classDomain.id", classId));
		}else{
			detachedCriteria.createAlias("classDomain", "clazz");
			if(ValidateUtil.notEmpty(majorId)){
				//专业过滤
				detachedCriteria.createAlias("clazz.major", "qmajor");
				detachedCriteria.add(Restrictions.eq("qmajor.id", majorId));
			}else{
				if(ValidateUtil.notEmpty(collegeId)){
					//学院过滤
					detachedCriteria.createAlias("clazz.major", "qmajor");
					detachedCriteria.createAlias("qmajor.college", "qcollege");
					detachedCriteria.add(Restrictions.eq("qcollege.id", collegeId));
				}
			}
			if(ValidateUtil.notEmpty(gradeId)){
				detachedCriteria.createAlias("clazz.grade", "qgrade");
				detachedCriteria.add(Restrictions.eq("qgrade.id", gradeId));
			}
		}
		
		if(ValidateUtil.notEmpty(searchText)){
			//多条件过滤，此处名字，宿舍，籍贯
			Disjunction disjunction = Restrictions.disjunction();  
			disjunction.add(Restrictions.like("stuId", searchText,MatchMode.ANYWHERE).ignoreCase());  
			disjunction.add(Restrictions.like("name", searchText,MatchMode.ANYWHERE).ignoreCase());  
			disjunction.add(Restrictions.like("teachClass", searchText,MatchMode.ANYWHERE).ignoreCase());  
			disjunction.add(Restrictions.like("dormitory", searchText,MatchMode.ANYWHERE).ignoreCase());  
			disjunction.add(Restrictions.like("nativePlace", searchText,MatchMode.ANYWHERE).ignoreCase());  
	              
			detachedCriteria.add(disjunction);  
		}
		
		if(ValidateUtil.notEmpty(sortValue)){
			if(Consts.SORT_ASC.equals(sortMode)){
				detachedCriteria.addOrder(Order.asc(sortValue));
			}else{
				detachedCriteria.addOrder(Order.desc(sortValue));
			}
		}
		
		return studentDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.csystem.service.IStudentService#doGetByStudentId(java.lang.String)
	 */
	@Override
	public StudentDomain doGetByStudentId(String stuId) {
		// TODO Auto-generated method stub
		if(ValidateUtil.isEmpty(stuId)){
			return null;
		}
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(StudentDomain.class);
		detachedCriteria.add(Restrictions.eq("stuId", stuId));
		List<StudentDomain> studentDomains=studentDao.getFilterList(detachedCriteria);
		if(studentDomains.size()==1){
			return studentDomains.get(0);
		}
		return null;
	}

	/**
	 * @see com.cb.csystem.service.IStudentService#doGetIdbystuId(java.lang.String)
	 */
	@Override
	public String doGetIdbystuId(String stuId) throws Exception {
		// TODO Auto-generated method stub
		StudentDomain studentDomain=this.doGetByStudentId(stuId);
		if(studentDomain!=null){
			return studentDomain.getId();
		}
		return null;
	}

	/**
	 * @see com.cb.csystem.service.IStudentService#doSearchstudentList(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<StudentDomain> doSearchstudentList(String gradeId,
			String collegeId, String majorId, String classId) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(StudentDomain.class);
		
		//班级过滤
		if(ValidateUtil.notEmpty(classId)){
			detachedCriteria.add(Restrictions.eq("classDomain.id", classId));
		}else{
			detachedCriteria.createAlias("classDomain", "clazz");
			if(ValidateUtil.notEmpty(majorId)){
				//专业过滤
				detachedCriteria.createAlias("clazz.major", "qmajor");
				detachedCriteria.add(Restrictions.eq("qmajor.id", majorId));
			}else{
				if(ValidateUtil.notEmpty(collegeId)){
					//学院过滤
					detachedCriteria.createAlias("clazz.major", "qmajor");
					detachedCriteria.createAlias("qmajor.college", "qcollege");
					detachedCriteria.add(Restrictions.eq("qcollege.id", collegeId));
				}
			}
			//过滤年级
			if(ValidateUtil.notEmpty(gradeId)){
				detachedCriteria.createAlias("clazz.grade", "qgrade");
				detachedCriteria.add(Restrictions.eq("qgrade.id", gradeId));
			}
		}
		
		return studentDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.csystem.service.IStudentService#doSave(com.cb.csystem.domain.StudentDomain)
	 */
	@Override
	public boolean doSave(StudentDomain studentDomain) throws Exception {
		// TODO Auto-generated method stub
		if(studentDomain.getId()==null){
			return studentDao.save(studentDomain);
		}else{
			return studentDao.update(studentDomain);
		}
	}

}
