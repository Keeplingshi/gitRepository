package com.cb.csystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.IDisciplineTypeDao;
import com.cb.csystem.domain.DisciplineTypeDomain;
import com.cb.csystem.service.IDisciplineTypeService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class DisciplineTypeService implements IDisciplineTypeService {

	@Resource private IDisciplineTypeDao disciplineTypeDao;
	
	/**
	 * @see com.cb.csystem.service.IDisciplineTypeService#doGetById(java.lang.String)
	 */
	@Override
	public DisciplineTypeDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return disciplineTypeDao.getById(id);
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineTypeService#doGetFilterList()
	 */
	@Override
	public List<DisciplineTypeDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(DisciplineTypeDomain.class);
		return disciplineTypeDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineTypeService#doSave(com.cb.csystem.domain.DisciplineTypeDomain)
	 */
	@Override
	public boolean doSave(DisciplineTypeDomain disciplineTypeDomain)
			throws Exception {
		// TODO Auto-generated method stub
		if(disciplineTypeDomain.getId()==null){
			return disciplineTypeDao.save(disciplineTypeDomain);
		}else{
			return disciplineTypeDao.update(disciplineTypeDomain);
		}
	}

	/**
	 * @see com.cb.csystem.service.IDisciplineTypeService#doDeleteById(java.lang.String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return disciplineTypeDao.deleteById(id);
	}

}
