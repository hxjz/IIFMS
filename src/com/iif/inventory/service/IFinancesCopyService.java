package com.iif.inventory.service;

import java.text.ParseException;
import com.hxjz.common.core.orm.IBaseService;

/**
 * @ClassName: FinancesCopyService
 * @Description: 财物copy service
 * @Author: M
 * @Date: 2017-07-12 16:50
 * @Version: V1.0
 */
public interface IFinancesCopyService extends IBaseService {

    public boolean deleteAll() throws ParseException;  // 删除所有数据
}
