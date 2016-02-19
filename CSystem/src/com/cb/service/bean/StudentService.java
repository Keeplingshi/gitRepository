package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.IStudentDao;
import com.cb.domain.StudentDomain;
import com.cb.service.IStudentService;
import com.system.util.PageInfo;

/**
 * 学生基础服务类
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class StudentService implements IStudentService{

	private @Resource IStudentDao studentDao;
	
	/**
	 * @see com.cb.service.IStudentService#doGetById(java.lang.String)
	 */
	@Override
	public StudentDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.getById(id);
	}

	/**
	 * @see com.cb.service.IStudentService#doGetFilterList()
	 */
	@Override
	public List<StudentDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(StudentDomain.class);
		
		return studentDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.service.IStudentService#doGetPageList(com.system.util.PageInfo)
	 */
	@Override
	public List<StudentDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(StudentDomain.class);
		
		return studentDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.service.IStudentService#doSave(com.cb.domain.StudentDomain)
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

	/**
	 * @see com.cb.service.IStudentService#doDeleteById(java.lang.String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return studentDao.deleteById(id);
	}

	/**
	 * @see com.cb.service.IStudentService#doDeleteByIds(java.lang.String[])
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
	 * @see com.cb.service.IStudentService#doSearchstudentPageList(com.system.util.PageInfo, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<StudentDomain> doSearchstudentPageList(PageInfo pageInfo,
			String collegeId, String majorId, String classId, String searchText)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(StudentDomain.class);
		
		//班级过滤
		if(classId!=null&&!"".equals(classId)){
			detachedCriteria.add(Restrictions.eq("classDomain.id", classId));
		}else{
			if(majorId!=null&&!"".equals(majorId)){
				//专业过滤
				detachedCriteria.createAlias("classDomain", "clazz");
				detachedCriteria.createAlias("clazz.major", "qmajor");
				detachedCriteria.add(Restrictions.eq("qmajor.id", majorId));
			}else{
				if(collegeId!=null&&!"".equals(collegeId)){
					//学院过滤
					detachedCriteria.createAlias("classDomain", "clazz");
					detachedCriteria.createAlias("clazz.major", "qmajor");
					detachedCriteria.createAlias("qmajor.college", "qcollege");
					detachedCriteria.add(Restrictions.eq("qcollege.id", collegeId));
				}
			}
		}
		
		if(searchText!=null&&!"".equals(searchText)){
			//多条件过滤，此处名字，宿舍，籍贯
			Disjunction disjunction = Restrictions.disjunction();  
			disjunction.add(Restrictions.like("name", "%"+searchText+"%",MatchMode.ANYWHERE).ignoreCase());  
			disjunction.add(Restrictions.like("dormitory", "%"+searchText+"%",MatchMode.ANYWHERE).ignoreCase());  
			disjunction.add(Restrictions.like("nativePlace", "%"+searchText+"%",MatchMode.ANYWHERE).ignoreCase());  
	              
			detachedCriteria.add(disjunction);  
		}
		
		return studentDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.service.IStudentService#doGetByStudentId(java.lang.String)
	 */
	@Override
	public StudentDomain doGetByStudentId(String stuId) {
		// TODO Auto-generated method stub
		if(stuId==null||"".equals(stuId)){
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

}
