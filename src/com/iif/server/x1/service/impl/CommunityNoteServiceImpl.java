package com.iif.server.x1.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxjz.common.core.orm.BaseService;
import com.iif.server.x1.dao.ICommunityNoteDao;
import com.iif.server.x1.service.ICommunityNoteAService;

@Service
public class CommunityNoteServiceImpl extends BaseService implements ICommunityNoteAService{
	@Autowired
	private ICommunityNoteDao communityNoteDao;
	
	public List<Map<String, Object>> getTopByUserCount(String projectId){
		return communityNoteDao.getTopByUserCount(projectId);
	}
}
