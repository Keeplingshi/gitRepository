/**
 * 
 */
package com.cb.csystem.dao.bean;

import org.springframework.stereotype.Repository;

import com.cb.csystem.dao.IDisciplineTypeDao;
import com.cb.csystem.domain.DisciplineTypeDomain;
import com.cb.system.dao.bean.BaseDao;

/**
 * @author chenbin
 *
 */
@Repository
public class DisciplineTypeDao extends BaseDao<DisciplineTypeDomain> implements IDisciplineTypeDao{

}
