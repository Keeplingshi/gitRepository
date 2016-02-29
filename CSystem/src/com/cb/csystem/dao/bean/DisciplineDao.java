/**
 * 
 */
package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.IDisciplineDao;
import com.cb.csystem.domain.DisciplineDomain;
import com.cb.system.dao.bean.BaseDao;

/**
 * @author Administrator
 *
 */
@Repository
public class DisciplineDao extends BaseDao<DisciplineDomain> implements IDisciplineDao{

}
