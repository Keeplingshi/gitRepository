package com.cb.csystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.IDisciplineDao;
import com.cb.csystem.domain.DisciplineDomain;
import com.cb.csystem.service.IDisciplineService;
import com.cb.system.util.PageInfo;

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

}
