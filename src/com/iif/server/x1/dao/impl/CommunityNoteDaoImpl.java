package com.iif.server.x1.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hxjz.common.core.orm.BaseDao;
import com.iif.server.x1.dao.ICommunityNoteDao;

@Repository
public class CommunityNoteDaoImpl extends BaseDao implements ICommunityNoteDao{
	
	/**
	 * 查询TOP5记事 
	 * @param projectId
	 * @return
	 */
	public List<Map<String, Object>> getTopByUserCount(String projectId){
		StringBuffer sql = new StringBuffer("select count(*) count,noteid from tcommunitynote ");
		sql.append(" where fflag=1 ");
		if(projectId != null && !"".equals(projectId) && !"all".equalsIgnoreCase(projectId)){
			sql.append(" and projectid='" + projectId+"'");
		}
		
		sql.append(" group by noteid order by count desc");
		sql.append(" limit 0,5");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		
		return list;
	}
}
