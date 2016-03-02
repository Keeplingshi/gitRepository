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

import com.cb.csystem.dao.IMajorDao;
import com.cb.csystem.domain.MajorDomain;
import com.cb.csystem.service.IMajorService;
import com.cb.system.util.PageInfo;
import com.cb.system.util.SelectItem;

/**
 * 专业服务类
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class MajorService implements IMajorService{

	@Resource private IMajorDao majorDao;
	
	/**
	 * @see IMajorService#doGetById(String)
	 */
	@Override
	public MajorDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return majorDao.getById(id);
	}

	/**
	 * @see IMajorService#doGetFilterList()
	 */
	@Override
	public List<MajorDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(MajorDomain.class);
		List<MajorDomain> majorList=majorDao.getFilterList(detachedCriteria);
		
		return majorList;
	}

	/**
	 * @see IMajorService#doGetPageList(PageInfo)
	 */
	@Override
	public List<MajorDomain> doGetPageList(PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(MajorDomain.class);
		List<MajorDomain> majorList=majorDao.getPageList(detachedCriteria, pageInfo);
		return majorList;
	}

	/**
	 * @see IMajorService#doSave(MajorDomain)
	 */
	@Override
	public boolean doSave(MajorDomain majorDomain) throws Exception {
		// TODO Auto-generated method stub
		
		if(majorDomain.getId()==null){
			return majorDao.save(majorDomain);
		}else{
			return majorDao.update(majorDomain);
		}
		
	}

	/**
	 * @see IMajorService#doDeleteById(String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return majorDao.deleteById(id);
	}

	/**
	 * @see IMajorService#doSearchmajorPageList(PageInfo, String, String)
	 */
	@Override
	public List<MajorDomain> doSearchmajorPageList(PageInfo pageInfo,
			String collegeId, String searchText) throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(MajorDomain.class);

		if(collegeId!=null&&!"".equals(collegeId)){
			detachedCriteria.add(Restrictions.eq("college.id", collegeId));
		}
		if(searchText!=null&&!"".equals(searchText)){
			detachedCriteria.add(Restrictions.like("name", searchText,MatchMode.ANYWHERE).ignoreCase());
		}
		
		List<MajorDomain> majorList=majorDao.getPageList(detachedCriteria, pageInfo);
		return majorList;
	}

	/**
	 * @see IMajorService#dogetMajorsByCollegeId(String)
	 */
	@Override
	public List<SelectItem> dogetMajorsByCollegeId(String collegeId) {
		// TODO Auto-generated method stub
		
		List<SelectItem> selectList=new ArrayList<SelectItem>();
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(MajorDomain.class);
		if(collegeId!=null&&!"".equals(collegeId)){
			detachedCriteria.add(Restrictions.eq("college.id", collegeId));
		}
		List<MajorDomain> majorList=majorDao.getFilterList(detachedCriteria);
		for(MajorDomain majorDomain:majorList){
			selectList.add(new SelectItem(majorDomain.getId(),majorDomain.getName()));
		}
		
		return selectList;
	}

}
