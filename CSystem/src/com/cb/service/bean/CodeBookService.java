package com.cb.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.dao.ICodeBookDao;
import com.cb.domain.CodeBookDomain;
import com.cb.service.ICodeBookService;
import com.system.util.PageInfo;

/**
 * CodeBook服务层
 * @author chen
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
public class CodeBookService implements ICodeBookService{

	@Resource private ICodeBookDao codeBookDao;

	/**
	 * @see com.cb.service.ICodeBookService#doGetById(java.lang.String)
	 */
	@Override
	public CodeBookDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return codeBookDao.getById(id);
	}

	/**
	 * @see com.cb.service.ICodeBookService#doGetFilterList()
	 */
	@Override
	public List<CodeBookDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CodeBookDomain.class);
		return codeBookDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.service.ICodeBookService#doGetPageList(com.system.util.PageInfo)
	 */
	@Override
	public List<CodeBookDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CodeBookDomain.class);
		return codeBookDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.service.ICodeBookService#doSave(com.cb.domain.CodeBookDomain)
	 */
	@Override
	public boolean doSave(CodeBookDomain codeBookDomain) throws Exception {
		// TODO Auto-generated method stub
		if(codeBookDomain.getId()==null){
			return codeBookDao.save(codeBookDomain);
		}else{
			return codeBookDao.update(codeBookDomain);
		}
	}

	/**
	 * @see com.cb.service.ICodeBookService#doDeleteById(java.lang.String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return codeBookDao.deleteById(id);
	}

	/**
	 * @see com.cb.service.ICodeBookService#doGetCodeBookByType(java.lang.String)
	 */
	@Override
	public List<CodeBookDomain> doGetCodeBookByType(String type)
			throws Exception {
		// TODO Auto-generated method stub
		if(type==null||"".equals(type)){
			return null;
		}
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CodeBookDomain.class);
		detachedCriteria.add(Restrictions.eq("type", type));
		detachedCriteria.addOrder(Order.asc("value"));
		return codeBookDao.getFilterList(detachedCriteria);
	}
	
}
