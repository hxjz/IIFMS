package com.iif.stock.service.impl;

import com.hxjz.common.core.orm.BaseService;
import com.iif.stock.service.IStockService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: StockServiceImpl
 * @Description:
 * @Author: M
 * @Date: 2017-05-16 23:52
 * @Version: V1.0
 */
@Service
@Transactional()
public class StockServiceImpl extends BaseService implements IStockService {
}
