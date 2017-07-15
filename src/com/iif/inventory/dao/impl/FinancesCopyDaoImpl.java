package com.iif.inventory.dao.impl;

import com.hxjz.common.core.SpringTool;
import com.hxjz.common.core.orm.BaseDao;
import com.iif.inventory.dao.IFinancesCopyDao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.text.ParseException;

/**
 * @Author M
 * @Date 2017年7月12日 下午16:31:07
 * @Version V0.1
 * @Desc 财物copy daoImpl
 */
@Repository
public class FinancesCopyDaoImpl extends BaseDao implements IFinancesCopyDao {

    @Override
    public boolean deleteAll() throws ParseException {
    	StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("delete ");
        sqlBuilder.append(" from tfinances_copy ");
        sqlBuilder.append(" where 1=1");
        System.out.println(sqlBuilder.toString());
        JdbcDaoSupport dao = (JdbcDaoSupport) SpringTool.getBean("JdbcDaoSupport");
        dao.getJdbcTemplate().execute(sqlBuilder.toString());
        return true;
    }
}
