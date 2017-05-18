package com.iif.server.x1.dao;

import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseDao;

public interface ICommunityNoteDao extends IBaseDao{
	public List<Map<String, Object>> getTopByUserCount(String projectId);
}
