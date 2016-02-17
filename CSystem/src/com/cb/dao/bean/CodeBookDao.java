package com.cb.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.dao.ICodeBookDao;
import com.cb.domain.CodeBookDomain;
import com.system.dao.bean.BaseDao;

/**
 * CodeBook
 * @author chen
 *
 */
@Repository
public class CodeBookDao extends BaseDao<CodeBookDomain> implements ICodeBookDao{

}
