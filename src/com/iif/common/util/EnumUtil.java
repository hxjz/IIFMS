package com.iif.common.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.hxjz.common.core.SpringTool;
import com.iif.system.code.entity.Dictionary;

/**
 * 读取 Enum 值存入数据库
 * 
 * @author LiuM
 * @date 2017
 * @version V0.1
 */
public class EnumUtil {
	
	/**
	 * 通过数据库获得数据字典
	 * @param enumMap
	 * @param enumName
	 */
	public static List<Dictionary> getDictionaryFromDB(String enumName ){
		List<Dictionary> dictionarys=getDictionarys(enumName);
		return dictionarys;
	}
	
	/**
	 * 通过枚举名获取所有枚举
	 * @param enumName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public static List<Dictionary> getDictionarys(String enumName) {	
		if (StringUtils.isBlank(enumName))return null;
		
		String sql="select d.fid,d.fkey,d.fvalue from tdictionary d where d.fenum_name=? and d.fis_del=0 order by d.findex ";
		Object[] params={enumName};
		List<Dictionary> list=new ArrayList<Dictionary>();
		
		JdbcDaoSupport dao = (JdbcDaoSupport) SpringTool.getBean("JdbcDaoSupport");
		List d = dao.getJdbcTemplate().query(sql, params,new DictionaryMapper());
		
		list.addAll(d);
		return list;
	}
	
	protected static class DictionaryMapper implements RowMapper<Object> {
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Dictionary d=new Dictionary();
			d.setId(rs.getString("FID"));
			d.setKey(rs.getString("FKEY"));
			d.setValue(rs.getString("FVALUE"));
			return d;
		}
	}
}