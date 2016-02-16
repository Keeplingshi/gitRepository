package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.IGradeDao;
import com.cb.domain.GradeDomain;
import com.cb.service.IGradeService;
import com.system.util.PageInfo;

/**
 * 年级基础服务类
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class GradeService implements IGradeService{

	@Resource private IGradeDao gradeDao;

	/**
	 * @see com.cb.service.IGradeService#doGetById(java.lang.String)
	 */
	@Override
	public GradeDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return gradeDao.getById(id);
	}

	/**
	 * @see com.cb.service.IGradeService#doGetFilterList()
	 */
	@Override
	public List<GradeDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(GradeDomain.class);
		return gradeDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.service.IGradeService#doGetPageList(com.system.util.PageInfo)
	 */
	@Override
	public List<GradeDomain> doGetPageList(PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(GradeDomain.class);
		return gradeDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.service.IGradeService#doSave(com.cb.domain.GradeDomain)
	 */
	@Override
	public boolean doSave(GradeDomain gradeDomain) throws Exception {
		// TODO Auto-generated method stub
		if(gradeDomain.getId()==null){
			return gradeDao.save(gradeDomain);
		}else{
			return gradeDao.update(gradeDomain);
		}
	}

	/**
	 * @see com.cb.service.IGradeService#doDeleteById(java.lang.String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return gradeDao.deleteById(id);
	}
	
	
	
}
