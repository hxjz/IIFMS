package com.iif.inventory.dao;

import com.hxjz.common.core.orm.IBaseDao;
import java.text.ParseException;

/**
 * @Author M
 * @Date 2017年5月15日 下午10:26:10
 * @Version V0.1
 * @Desc 财物管理 Dao
 */
public interface IFinancesCopyDao extends IBaseDao {

    public boolean deleteAll() throws ParseException;  // 删除所有数据
}
