package com.iif.finances.service;

import com.hxjz.common.core.orm.IBaseService;
import com.hxjz.common.utils.Page;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: FinancesService
 * @Description: 财物 service
 * @Author: GaoGang
 * @Date: 2017-05-16 23:50
 * @Version: V1.0
 */
public interface IFinancesService extends IBaseService {

    List showStatistics(Page page, Map conditions) throws ParseException;  // 显示统计信息
}
