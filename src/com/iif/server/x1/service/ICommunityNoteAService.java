package com.iif.server.x1.service;

import java.util.List;
import java.util.Map;

import com.hxjz.common.core.orm.IBaseService;

public interface ICommunityNoteAService extends IBaseService{
	public List<Map<String, Object>> getTopByUserCount(String projectId);
}
