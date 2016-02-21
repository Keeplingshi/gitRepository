package com.cb.csystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.ICollegeDao;
import com.cb.csystem.domain.CollegeDomain;
import com.cb.csystem.service.ICollegeService;
import com.cb.system.util.PageInfo;

/**
 * 学院服务类
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class CollegeService implements ICollegeService{

	@Resource private ICollegeDao collegeDao;

	/**
	 * @see ICollegeService#doGetById(String)
	 */
	@Override
	public CollegeDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return collegeDao.getById(id);
	}

	/**
	 * @see ICollegeService#doGetFilterList()
	 */
	@Override
	public List<CollegeDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CollegeDomain.class);
		List<CollegeDomain> collegeList=collegeDao.getFilterList(detachedCriteria);
		
		return collegeList;
	}

	/**
	 * @see ICollegeService#doGetPageList(PageInfo)
	 */
	@Override
	public List<CollegeDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CollegeDomain.class);
		List<CollegeDomain> collegeList=collegeDao.getPageList(detachedCriteria, pageInfo);
		
		return collegeList;
	}

	/**
	 * @see ICollegeService#doSave(CollegeDomain)
	 */
	@Override
	public boolean doSave(CollegeDomain collegeDomain) throws Exception {
		// TODO Auto-generated method stub
		
		if(collegeDomain.getId()==null){
			return collegeDao.save(collegeDomain);
		}else{
			return collegeDao.update(collegeDomain);
		}
	}

	/**
	 * @see ICollegeService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return collegeDao.deleteById(id);
	}
	
	
}
