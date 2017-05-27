package com.iif.finances.service.impl;

import com.hxjz.common.core.SpringTool;
import com.hxjz.common.core.orm.BaseService;
import com.hxjz.common.utils.DateUtil;
import com.hxjz.common.utils.Page;
import com.iif.common.util.EnumUtil;
import com.iif.finances.dao.IFinancesDao;
import com.iif.finances.service.IFinancesService;
import com.iif.system.code.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: FinancesServiceImpl
 * @Description:
 * @Author: GaoGang
 * @Date: 2017-05-16 23:52
 * @Version: V1.0
 */
@Service
@Transactional()
public class FinancesServiceImpl extends BaseService implements IFinancesService {

    @Autowired
    IFinancesDao iFinancesDao;


    @Override
    public List showStatistics(Page page, Map conditions) throws ParseException {
        return iFinancesDao.showStatistics(page, conditions);
    }

}
