package com.iif.server.x1.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.Page;
import com.iif.common.util.SysConstant;
import com.iif.server.x1.entity.ActiveState;
import com.iif.server.x1.entity.CommunityActive;
import com.iif.server.x1.service.IActiveStateService;
import com.iif.server.x1.service.ICommunityActiveService;

/**
 * 
 * 前端活动用Action
 * @author LiuM
 * @since 1.0	
 * @createDate 2017
 * 
 */
@Controller
@RequestMapping(value = "/community/communityActive")
public class CommunityActiveAction extends BaseAction{
	
	@Autowired
	private ICommunityActiveService communityActiveService;
	@Autowired
	private IActiveStateService activeStateService;
	
	private final static transient Log LOGGER = LogFactory.getLog(CommunityActiveAction.class);
	
	/**
	 * 返回前台用活动内容
	 * @throws IOException 
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/listCommunityActive.do")
	@ResponseBody
	public JSONObject listCommunityActive(String uid, String projectId) throws IOException {
		
		// 分页
		/*int pageNum = SysConstant.PAGENUM;
		int rows = SysConstant.ROWS;*/
		page = new Page();
		
		// searchMap
		Map searchMap =new HashMap();
		searchMap.put("filter_and_isDel_EQ_I", SysConstant.IS_NOT_DEL);
		searchMap.put("order_joinDate", "desc");
		if(projectId != null && !"".equals(projectId)){
			searchMap.put("filter_and_projectId_EQ_S", projectId);
		}
		//searchMap.put("filter_and_projectId_EQ_S", projectId);
		List<CommunityActive> lstCommunityActive = communityActiveService.findByPage(page, searchMap);
		
		JSONArray josnList = new JSONArray();//数据集合(多个对象)
		JSONObject jsonMap = new JSONObject();//数据对象
		if(lstCommunityActive != null && lstCommunityActive.size() > 0) {
			for(int i=0; i<lstCommunityActive.size(); i++) {
				CommunityActive tempMap = lstCommunityActive.get(i);
				jsonMap.put("id", tempMap.getId());//Id
				jsonMap.put("projectid", tempMap.getProjectId());//项目ID
				//String title1 = new String(tempMap.getTitle().getBytes("iso-8859-1"),"utf-8");
				jsonMap.put("title", tempMap.getTitle());//标题
				jsonMap.put("article", tempMap.getArticle());//活动文章
				jsonMap.put("activeimg", tempMap.getActiveImg());//活动缩略图
				jsonMap.put("date", tempMap.getDate());//时间
				jsonMap.put("joindate", tempMap.getJoinDate());//时间
				jsonMap.put("cost", tempMap.getCost());//费用
				jsonMap.put("address", tempMap.getAddress());//地址
				
				Map seaMap = new HashMap();
				seaMap.put("filter_and_activeId_EQ_S", tempMap.getId());//活动Id
				seaMap.put("filter_and_userId_EQ_S", uid);//用户id
				//seaMap.put("filter_and_type_EQ_S", SysConstant.INTERFLG);//已点赞
				List<ActiveState> lstActiveStateInterest = activeStateService.findByFilterMap(seaMap);
				if(lstActiveStateInterest != null && lstActiveStateInterest.size() > 0) {
					jsonMap.put("interestFlg", lstActiveStateInterest.get(0).getFlag());//感兴趣
				}
				
				seaMap.clear();
				seaMap.put("filter_and_activeId_EQ_S", tempMap.getId());//活动Id
				seaMap.put("filter_and_userId_EQ_S", uid);//用户id
				List<ActiveState> lstActiveStatePar = activeStateService.findByFilterMap(seaMap);
				if(lstActiveStatePar != null && lstActiveStatePar.size() > 0) {
					jsonMap.put("participateFlg", lstActiveStatePar.get(0).getFlag());//参加
				}
				
				seaMap.clear();
				seaMap.put("filter_and_activeId_EQ_S", tempMap.getId());//活动Id
				List<ActiveState> lstActiveStateIntCnt = activeStateService.findByFilterMap(seaMap);
				if(lstActiveStateIntCnt != null && lstActiveStateIntCnt.size() > 0) {
					jsonMap.put("interestCnt", lstActiveStateIntCnt.size());//感兴趣总数
				}
				
				seaMap.clear();
				seaMap.put("filter_and_activeId_EQ_S", tempMap.getId());//活动Id
				List<ActiveState> lstActiveStateParCnt = activeStateService.findByFilterMap(seaMap);
				if(lstActiveStateParCnt != null && lstActiveStateParCnt.size() > 0) {
					jsonMap.put("participateCnt", lstActiveStateParCnt.size());//参加总数
				}
				
				josnList.add(jsonMap);//放入JSON集合
			}
		}
		
		JSONObject jsonReturn =  new JSONObject();
		jsonReturn.put("status", "success");
		jsonReturn.put("error_code", "");
		jsonReturn.put("error_message", "");
		jsonReturn.put("data", josnList);
		LOGGER.info("jsonReturn====="+jsonReturn.toString());
		
		return jsonReturn;
	}
}
