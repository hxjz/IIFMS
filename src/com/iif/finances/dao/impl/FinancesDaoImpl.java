package com.iif.finances.dao.impl;

import com.hxjz.common.core.SpringTool;
import com.hxjz.common.utils.DateUtil;
import com.hxjz.common.utils.Page;
import com.iif.common.enums.FinanceType;
import com.iif.common.enums.FinanceTypeEnum;
import com.iif.common.util.EnumUtil;
import com.iif.common.util.InitSelect;
import com.iif.finances.service.impl.FinancesServiceImpl;
import com.iif.system.code.entity.Dictionary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.iif.finances.dao.IFinancesDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author GaoGang
 * @Date 2017年5月15日 下午10:31:07
 * @Version V0.1
 * @Desc 财物管理 daoImpl
 */
@Repository
public class FinancesDaoImpl extends BaseDao implements IFinancesDao {

    @Override
    public List showStatistics(Page page, Map conditions) throws ParseException {
        StringBuilder sqlBuffer = new StringBuilder();
        sqlBuffer.append("select ffinanceType,count(fid) as counts from tfinances");
        sqlBuffer.append(" where 1=1");
        if (!conditions.isEmpty()) {
            // 查询条件组装
            for (Object key : conditions.keySet()) {
                if (key.toString().contains("GE")) {
                    sqlBuffer.append(" and fupdateTime>=");
                } else {
                    sqlBuffer.append(" and fupdateTime<=");
                }
                sqlBuffer.append(DateUtil.convertStringToDate(conditions.get(key).toString()));
            }
        }

        sqlBuffer.append(" and ffinanceState=0 group by ffinanceType");
        List<Map<String, Object>> statisticList = new ArrayList<Map<String, Object>>();

        JdbcDaoSupport dao = (JdbcDaoSupport) SpringTool.getBean("JdbcDaoSupport");
        List resultList = dao.getJdbcTemplate().query(sqlBuffer.toString(), new FinancesDaoImpl.FinanceStatisticsMapper());

        statisticList.addAll(resultList);
        return statisticList;
    }

    protected static class FinanceStatisticsMapper implements RowMapper<Object> {
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            Map<String, Object> result = new HashMap<String, Object>();
//            获取财务类型 todo 暂时这么做，看后续怎么优化
//            List<java.util.Dictionary> financeTypeList = InitSelect.getSelectList(FinanceTypeEnum.class);
//            Map<Integer, String> typeName = new HashMap<>();
//            for (int i = 0; i < financeTypeList.size(); i++) {
//                java.util.Dictionary temp = financeTypeList.get(i);
//                Integer key = temp.get("key") == null ? 0 : Integer.valueOf(temp.get("key").toString());
//                typeName.put(key, temp.get("value").toString());
//            }
            int code=rs.getInt("ffinanceType");
            String financeTypeName=String.valueOf(code);
            FinanceType [] types=FinanceType.values();
            for(int i=0;i<types.length;i++){
                FinanceType type=types[i];
                if(type.getTypeCode()==code){
                    financeTypeName= type.getTypeName();
                }
            }
            result.put("financeType", financeTypeName);
            result.put("sum", rs.getInt("counts"));
            return result;
        }
    }
}
