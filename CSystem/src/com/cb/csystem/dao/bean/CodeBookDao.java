package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.ICodeBookDao;
import com.cb.csystem.domain.CodeBookDomain;
import com.cb.system.dao.bean.BaseDao;

/**
 * CodeBook
 * @author chen
 *
 */
@Repository
public class CodeBookDao extends BaseDao<CodeBookDomain> implements ICodeBookDao{

}
