package com.iif.finances.dao;

import com.hxjz.common.core.orm.IBaseDao;
import com.hxjz.common.utils.Page;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author GaoGang
 * @Date 2017年5月15日 下午10:26:10
 * @Version V0.1
 * @Desc 财物管理 Dao
 */
public interface IFinancesDao extends IBaseDao {
    List showStatistics(Page page, Map conditions) throws ParseException;  // 显示统计信息
}
