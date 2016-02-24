package com.cb.csystem.service;

import java.util.List;

import com.cb.csystem.domain.CodeBookDomain;
import com.cb.system.util.PageInfo;

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
	
	/**
	 * 通过值和类型获取名字
	 * @param value
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String doGetNameByValueAndType(String value,String type)throws Exception;

	/**
	 * 通过名称和类型获取值
	 * @param name
	 * @param type
	 * @return
	 */
	public String doGetValueByNameAndType(String name, String type)throws Exception;
	
	/**
	 * 通过值和类型获取id
	 * @param value
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String doGetIdByValueAndType(String value,String type)throws Exception;
	
	/**
	 * 根据父节点值和类型查询子节点
	 * @param parentValue
	 * @param parentType
	 * @param childType
	 * @return
	 * @throws Exception
	 */
	public List<CodeBookDomain> doGetCodeBookListByParent(String parentValue,String parentType,String childType)throws Exception;
}
