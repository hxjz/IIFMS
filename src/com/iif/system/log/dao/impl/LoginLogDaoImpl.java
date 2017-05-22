package com.iif.system.log.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.hxjz.common.core.orm.OrmConverter;
import com.hxjz.common.utils.Page;
import com.iif.system.log.dao.ILoginLogDao;
import com.iif.system.log.entity.LoginLog;

/**
 * <p>@desc : 登陆日志Dao</p>
 * <p>@see : </p>
 *
 * <p>@author : LiuM</p>
 * <p>@createDate : 2017</p>
 * <p>@version : V0.1 </p>
 * <p>HXJZ</p>
 */
@Repository()
public class LoginLogDaoImpl extends BaseDao implements ILoginLogDao {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findByPage(Page page, Map searchMap) {
		StringBuffer query = new StringBuffer(" from LoginLog where 1=1");
		List valueList = new ArrayList();
		OrmConverter.getQuery(query, searchMap, "filter_and_userName_LIKE_S", valueList);
		OrmConverter.getQuery(query, searchMap, "filter_and_orgName_LIKE_S", valueList);
		OrmConverter.getQuery(query, searchMap, "filter_and_loginTime_GE_T", valueList);
		OrmConverter.getQuery(query, searchMap, "filter_and_loginTime_LE_T", valueList);
		query.append(" order by loginTime desc");
		return super.findByHql(page, query, valueList);
	}

	@Override
	public synchronized void saveForJdbc(LoginLog loginLog) {
		long id = this.getJdbcTemplate().queryForLong("select max(id) from sys_login_log") + 1;
		loginLog.setId(id);
		String sql = "insert into sys_login_log(id,user_name,login_ip,org_name,login_time)values(?,?,?,?,?)";
		this.getJdbcTemplate().update(
				sql,
				new Object[] { loginLog.getId(), loginLog.getUserName(),
						loginLog.getLoginIp(), loginLog.getOrgName(),
						loginLog.getLoginTime() });
	}
}
