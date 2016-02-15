package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
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
	 * @see com.cb.service.IStudentService#doSearchstudentPageList(com.system.util.PageInfo, java.lang.String)
	 */
	@Override
	public List<StudentDomain> doSearchstudentPageList(PageInfo pageInfo,
			String searchText) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
