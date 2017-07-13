package com.iif.inventory.service.impl;

import java.text.ParseException;

import com.hxjz.common.core.orm.BaseService;
import com.iif.inventory.dao.IFinancesCopyDao;
import com.iif.inventory.service.IFinancesCopyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @ClassName: FinancesCopyServiceImpl
 * @Description: 财物copy service
 * @Author: M
 * @Date: 2017-07-12 16:50
 * @Version: V1.0
 */
@Service
@Transactional()
public class FinancesCopyServiceImpl extends BaseService implements IFinancesCopyService {

    @Autowired
    IFinancesCopyDao iFinancesCopyDao;


    @Override
    public boolean deleteAll() throws ParseException  // 删除所有数据
    {
    	return true;
    }
}
