package com.cb.csystem.service.bean;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cb.csystem.dao.ICodeBookDao;
import com.cb.csystem.domain.CodeBookDomain;
import com.cb.csystem.service.ICodeBookService;
import com.cb.system.util.PageInfo;
import com.cb.system.util.ValidateUtil;

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
	 * @see com.cb.csystem.service.ICodeBookService#doGetById(java.lang.String)
	 */
	@Override
	public CodeBookDomain doGetById(String id) throws Exception {
		// TODO Auto-generated method stub
		return codeBookDao.getById(id);
	}

	/**
	 * @see com.cb.csystem.service.ICodeBookService#doGetFilterList()
	 */
	@Override
	public List<CodeBookDomain> doGetFilterList() throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CodeBookDomain.class);
		return codeBookDao.getFilterList(detachedCriteria);
	}

	/**
	 * @see com.cb.csystem.service.ICodeBookService#doGetPageList(com.cb.system.util.PageInfo)
	 */
	@Override
	public List<CodeBookDomain> doGetPageList(PageInfo pageInfo)
			throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CodeBookDomain.class);
		return codeBookDao.getPageList(detachedCriteria, pageInfo);
	}

	/**
	 * @see com.cb.csystem.service.ICodeBookService#doSave(com.cb.csystem.domain.CodeBookDomain)
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
	 * @see com.cb.csystem.service.ICodeBookService#doDeleteById(java.lang.String)
	 */
	@Override
	public boolean doDeleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return codeBookDao.deleteById(id);
	}

	/**
	 * @see com.cb.csystem.service.ICodeBookService#doGetCodeBookByType(java.lang.String)
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

	/**
	 * @see com.cb.csystem.service.ICodeBookService#doGetNameByValueAndType(java.lang.String, java.lang.String)
	 */
	@Override
	public String doGetNameByValueAndType(String value, String type)
			throws Exception {
		// TODO Auto-generated method stub
		if(value==null||"".equals(value)){
			return null;
		}
		if(type==null||"".equals(type)){
			return null;
		}
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CodeBookDomain.class);
		detachedCriteria.add(Restrictions.eq("value", value));
		detachedCriteria.add(Restrictions.eq("type", type));
		List<CodeBookDomain> codeBookList=codeBookDao.getFilterList(detachedCriteria);
		if(codeBookList.size()==1){
			return codeBookList.get(0).getName();
		}
		
		return null;
	}

	/**
	 * @see com.cb.csystem.service.ICodeBookService#doGetValueByNameAndType(java.lang.String, java.lang.String)
	 */
	@Override
	public String doGetValueByNameAndType(String name, String type)
			throws Exception {
		// TODO Auto-generated method stub
		if(name==null||"".equals(name)){
			return null;
		}
		if(type==null||"".equals(type)){
			return null;
		}
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CodeBookDomain.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		detachedCriteria.add(Restrictions.eq("type", type));
		List<CodeBookDomain> codeBookList=codeBookDao.getFilterList(detachedCriteria);
		if(codeBookList.size()==1){
			return codeBookList.get(0).getValue();
		}		
		return null;
	}

	/**
	 * @see com.cb.csystem.service.ICodeBookService#doGetIdByValueAndType(java.lang.String, java.lang.String)
	 */
	@Override
	public String doGetIdByValueAndType(String value, String type)
			throws Exception {
		// TODO Auto-generated method stub
		
		if(ValidateUtil.isEmpty(value)||ValidateUtil.isEmpty(type)){
			return null;
		}
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CodeBookDomain.class);
		detachedCriteria.add(Restrictions.eq("value", value));
		detachedCriteria.add(Restrictions.eq("type", type));
		List<CodeBookDomain> codeBookList=codeBookDao.getFilterList(detachedCriteria);
		if(codeBookList.size()==1){
			return codeBookList.get(0).getId();
		}
		
		return null;
	}

	/**
	 * @see com.cb.csystem.service.ICodeBookService#doGetCodeBookListByParent(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<CodeBookDomain> doGetCodeBookListByParent(String parentValue,
			String parentType, String childType) throws Exception {
		// TODO Auto-generated method stub
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(CodeBookDomain.class);
		
		//如果不为空，添加父节点过滤
		if(ValidateUtil.notEmpty(parentValue)&&ValidateUtil.notEmpty(parentType)){
			detachedCriteria.add(Restrictions.eq("parentId", doGetIdByValueAndType(parentValue,parentType)));
		}
		detachedCriteria.add(Restrictions.eq("type", childType));
		
		List<CodeBookDomain> codeBookList=codeBookDao.getFilterList(detachedCriteria);
		
		return codeBookList;
	}
	
}
