package com.iif.finances.dao.impl;

import com.hxjz.common.core.SpringTool;
import com.hxjz.common.core.orm.BaseDao;
import com.hxjz.common.utils.DateUtil;
import com.hxjz.common.utils.Page;
import com.iif.common.util.SysConstant;
import com.iif.finances.dao.IFinancesDao;
import com.iif.finances.dao.IFinancesImagesDao;
import com.iif.finances.service.IFinancesImagesService;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author GaoGang
 * @Date 2017-10-13 23:22
 * @Version V0.1
 * @Desc 财物图片 daoImpl
 */
@Repository
public class FinancesImagesDaoImpl extends BaseDao implements IFinancesImagesDao {
}
