package com.cb.csystem.service.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.IClassDao;
import com.cb.csystem.domain.ClassDomain;
import com.cb.csystem.domain.StudentDomain;
import com.cb.csystem.service.IClassService;
import com.cb.csystem.service.IStudentService;
import com.cb.csystem.service.IUserService;
import com.cb.csystem.util.Consts;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;
import com.cb.system.util.ValidateUtil;

/**
 * 班级服务层
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class ClassService implements IClassService{

	@Resource private IClassDao classDao;
	@Resource private IStudentService studentService;
	@Resource private IUserService userService;
	
	/**
	 * @see IClassService#doGetById(String)
	 */
	@Override
	public ClassDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return classDao.getById(id);
	}

	/**
	 * @see IClassService#doGetFilterList()
	 */
	@Override
	public List<ClassDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ClassDomain.class);
		List<ClassDomain> classDomains=classDao.getFilterList(detachedCriteria);
		
		return classDomains;
	}

	/**
	 * @see IClassService#doGetPageList(PageInfo)
	 */
	@Override
	public List<ClassDomain> doGetPageList(PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ClassDomain.class);
		List<ClassDomain> classDomains=classDao.getPageList(detachedCriteria, pageInfo);
		
		return classDomains;
	}

	/**
	 * @see IClassService#doSave(ClassDomain)
	 */
	@Override
	public boolean doSave(ClassDomain classDomain) throws Exception {
		// TODO Auto-generated method stub
		if(classDomain.getId()==null){
			return classDao.save(classDomain);
		}else{
			return classDao.update(classDomain);
		}
	}

	/**
	 * @see IClassService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return classDao.deleteById(id);
	}

	/**
	 * @see IClassService#doSearchclassPageList(PageInfo, String, String)
	 */
	@Override
	public List<ClassDomain> doSearchclassPageList(PageInfo pageInfo,String gradeId,
			String collegeId,String majorId, String searchText) throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ClassDomain.class);
		if(ValidateUtil.notEmpty(majorId)){
			detachedCriteria.add(Restrictions.eq("major.id", majorId));
		}else{
			if(ValidateUtil.notEmpty(collegeId)){
				detachedCriteria.createAlias("major", "m");
				detachedCriteria.createAlias("m.college", "c");
				detachedCriteria.add(Restrictions.eq("c.id", collegeId));
			}
		}
		if(ValidateUtil.notEmpty(gradeId)){
			detachedCriteria.add(Restrictions.eq("grade.id", gradeId));
		}
		
		if(ValidateUtil.notEmpty(searchText)){
			detachedCriteria.add(Restrictions.like("name", searchText,MatchMode.ANYWHERE).ignoreCase());
		}
		
		return classDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see IClassService#doDeleteByIds(String[])
	 */
	@Override
	public boolean doDeleteByIds(String[] classIds) throws Exception {
		// TODO Auto-generated method stub
		boolean b=false;
		for(String id:classIds){
			b=classDao.deleteById(id);
			if(!b){
				return false;
			}
		}
		
		return b;
	}

	/**
	 * @see com.cb.csystem.service.IClassService#dogetClasssByMajorId(java.lang.String)
	 */
	@Override
	public List<SelectItem> dogetClasssByMajorId(String major_id)
			throws Exception {
		// TODO Auto-generated method stub
		List<SelectItem> selectList=new ArrayList<SelectItem>();
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ClassDomain.class);
		if(ValidateUtil.notEmpty(major_id)){
			detachedCriteria.add(Restrictions.eq("major.id", major_id));
		}
		List<ClassDomain> classList=classDao.getFilterList(detachedCriteria);
		for(ClassDomain classDomain:classList){
			selectList.add(new SelectItem(classDomain.getId(),classDomain.getName()));
		}
		
		return selectList;
	}

	/**
	 * @see com.cb.csystem.service.IClassService#doGetMonitor(com.cb.csystem.domain.ClassDomain)
	 */
	@Override
	public StudentDomain doGetMonitor(ClassDomain classDomain) throws Exception {
		// TODO Auto-generated method stub
		for(StudentDomain studentDomain:classDomain.getStudents()){
			if(Consts.IS_MONITOR_B.equals(studentDomain.getIsMonitor())){
				return studentDomain;
			}
		}
		return null;
	}

	/**
	 * @see com.cb.csystem.service.IClassService#doGetClazzByGradeOrCollegeOrMajor(java.lang.String, java.lang.String)
	 */
	@Override
	public List<ClassDomain> doGetClazzByGradeOrCollegeOrMajor(String gradeId,
			String collegeId,String majorId) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ClassDomain.class);
		if(ValidateUtil.notEmpty(gradeId)){
			detachedCriteria.add(Restrictions.eq("grade.id", gradeId));
		}
		if(ValidateUtil.notEmpty(majorId)){
			detachedCriteria.createAlias("major", "m");
			detachedCriteria.add(Restrictions.eq("m.id", majorId));
		}else{
			if(ValidateUtil.notEmpty(collegeId)){
				//多级查询
				detachedCriteria.createAlias("major", "m");
				detachedCriteria.createAlias("m.college", "c");
				detachedCriteria.add(Restrictions.eq("c.id", collegeId));
			}
		}

		return classDao.getFilterList(detachedCriteria);
	}

	/**
	 * 设置班长
	 * @param classDomain
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean doSetMonitor(String classId,String stuId) throws Exception {
		// TODO Auto-generated method stub
		
		if(studentService.doGetByStudentId(stuId)==null){
			return false;
		}
		boolean b=false;
		ClassDomain classDomain=doGetById(classId);
		for(StudentDomain studentDomain:classDomain.getStudents()){
			if(stuId.equals(studentDomain.getStuId())){
				studentDomain.setIsMonitor(Consts.IS_MONITOR_B);
				userService.doSetMonitorByClassDomain(studentDomain, classDomain);
				b=true;
			}else{
				studentDomain.setIsMonitor(Consts.IS_MONITOR_A);
			}
			studentService.doSaveStuAndOthers(studentDomain);
		}
		
		return b;
	}

	/**
	 * @see com.cb.csystem.service.IClassService#doGetClazzSelectItem(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<SelectItem> doGetClazzSelectItem(String gradeId,
			String collegeId, String majorId) throws Exception {
		// TODO Auto-generated method stub
		List<SelectItem> selectList=new ArrayList<SelectItem>();
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ClassDomain.class);
		if(ValidateUtil.notEmpty(gradeId)){
			detachedCriteria.add(Restrictions.eq("grade.id", gradeId));
		}
		if(ValidateUtil.notEmpty(majorId)){
			detachedCriteria.createAlias("major", "m");
			detachedCriteria.add(Restrictions.eq("m.id", majorId));
		}else{
			if(ValidateUtil.notEmpty(collegeId)){
				//多级查询
				detachedCriteria.createAlias("major", "m");
				detachedCriteria.createAlias("m.college", "c");
				detachedCriteria.add(Restrictions.eq("c.id", collegeId));
			}
		}

		List<ClassDomain> classList=classDao.getFilterList(detachedCriteria);
		for(ClassDomain classDomain:classList){
			selectList.add(new SelectItem(classDomain.getId(),classDomain.getName()));
		}
		
		return selectList;
	}
}
