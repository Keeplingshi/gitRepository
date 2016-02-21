package com.cb.csystem.service.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.IClassDao;
import com.cb.csystem.domain.ClassDomain;
import com.cb.csystem.service.IClassService;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

/**
 * 班级服务层
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class ClassService implements IClassService{

	@Resource private IClassDao classDao;
	
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
	public List<ClassDomain> doSearchclassPageList(PageInfo pageInfo,
			String collegeId,String majorId, String searchText) throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ClassDomain.class);
		if(collegeId!=null&&!"".equals(collegeId)){
			//多级查询
			detachedCriteria.createAlias("major", "m");
			detachedCriteria.createAlias("m.college", "c");
			detachedCriteria.add(Restrictions.eq("c.id", collegeId));
		}
		if(majorId!=null&&!"".equals(majorId)){
			detachedCriteria.add(Restrictions.eq("major.id", majorId));
		}
		if(searchText!=null&&!"".equals(searchText)){
			detachedCriteria.add(Restrictions.like("name", "%"+searchText+"%"));
		}
		List<ClassDomain> classList=classDao.getPageList(detachedCriteria, pageInfo);
		
		return classList;
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
		List<SelectItem> selectList=new ArrayList<>();
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(ClassDomain.class);
		if(major_id!=null&&!"".equals(major_id)){
			detachedCriteria.add(Restrictions.eq("major.id", major_id));
		}
		List<ClassDomain> classList=classDao.getFilterList(detachedCriteria);
		for(ClassDomain classDomain:classList){
			selectList.add(new SelectItem(classDomain.getId(),classDomain.getName()));
		}
		
		return selectList;
	}

}
