package com.iif.server.x1.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxjz.common.core.web.BaseAction;
import com.iif.server.x1.entity.ActiveState;
import com.iif.server.x1.service.IActiveStateService;

/**
 * 
 * 活动中我感兴趣，我参加的接口Action
 * @author LiuM
 * @since 1.0
 * @createDate 2017
 * 
 */
@Controller
@RequestMapping(value = "/community/ActiveState")
public class ActiveStateAction extends BaseAction{
	
	@Autowired
	private IActiveStateService activeStateService;
	
	private final static transient Log LOGGER = LogFactory.getLog(ActiveStateAction.class);
	
	/*说明：根据传递的参数查询出tactivestate表中是否有该用户在该项目下的该活动记录，如果没有就insert一条数据，如果有就update一下falg的状态
		参数：userid=xxx&projectId=xxx&activeId=xxx&type=x&flag=x
		uerid:(用户id)
		projectId: (项目id)
		activeId : (活动id)
		type : (0:感兴趣;1:我参加)
		flag: (0:用户没有点过;1:已经点过，心形变红)*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="updateActiveState.acytion")
	@ResponseBody
	public String updateActiveState(String userId, String projectId, String activeId, String type, String flag){
		JSONObject jsonReturn =  new JSONObject();
		try {
			Map searchMap = new HashMap();
			//searchMap.put("filter_and_projectid_EQ_S", projectId);
			searchMap.put("filter_and_activeid_EQ_S", activeId);
			searchMap.put("filter_and_userid_EQ_S", userId);
			searchMap.put("filter_and_ftype_EQ_S", type);
			
			List<ActiveState> asList = (List<ActiveState>) activeStateService.findByFilterMap(searchMap);
			ActiveState as = null;
			if(asList != null){
				if(!asList.isEmpty()){
					as = asList.get(0);
				}
			}
			
			if(as == null){
				as = new ActiveState(projectId, activeId, userId, type, flag);
			} else {
				as.setFlag(flag);
			}
			
			activeStateService.save(as);
			
			jsonReturn.put("status", "success");
			jsonReturn.put("error_code", "");
			jsonReturn.put("error_message", "");
			jsonReturn.put("data", "");
			return jsonReturn.toString();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("ActiveStateAction接口异常------>" + e);
			jsonReturn.put("status", "fail");
			jsonReturn.put("error_code", "");
			jsonReturn.put("error_message", "");
			jsonReturn.put("data", "");
			return jsonReturn.toString();
		}
	}
}
