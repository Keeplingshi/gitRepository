package com.cb.service;

import java.util.List;

import com.cb.domain.CodeBookDomain;
import com.system.util.PageInfo;

/**
 * CodeBook基础服务类
 * @author chen
 *
 */
public interface ICodeBookService {

	/**
	 * 通过id获取CodeBook
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CodeBookDomain doGetById(String id)throws Exception;
	
	/**
	 * 获取CodeBook列表
	 * @return
	 * @throws Exception
	 */
	public List<CodeBookDomain> doGetFilterList() throws Exception;
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public List<CodeBookDomain> doGetPageList(PageInfo pageInfo)throws Exception;
	
	/**
	 * 保存CodeBook
	 * @param CodeBookDomain
	 * @return
	 * @throws Exception
	 */
	public boolean doSave(CodeBookDomain codeBookDomain) throws Exception;
	
	/**
	 * 删除CodeBook
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean doDeleteById(String id) throws Exception;
	
	/**
	 * 通过type获取CodeBook
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<CodeBookDomain> doGetCodeBookByType(String type)throws Exception;
}
