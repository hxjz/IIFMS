package com.iif.server.x1.web;

import java.util.Date;
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
import com.hxjz.common.utils.DateUtil;
import com.iif.common.util.SysConstant;
import com.iif.common.util.TemplateUtil;
import com.iif.server.x1.entity.CommunityNote;
import com.iif.server.x1.service.ICommunityNoteAService;

/**
 * 
 * 活动记事
 * @author LiuM
 * @since 1.0
 * @createDate 2017
 * 
 */
@Controller
@RequestMapping(value = "/community/communityNote")
public class CommunityNoteAction extends BaseAction{
	
	@Autowired
	private ICommunityNoteAService communityNoteAService;
	
	private final static transient Log LOGGER = LogFactory.getLog(CommunityNoteAction.class);
	
	/*说明：根据传递的参数查询出tcommunitynote表中是否有该用户在该项目下的该记事记录，如果没有就insert一条数据，如果有就update一下falg的状态
		参数：uid=xxx&projectId=xxx&noteId=xxx&noteName=xxx&flag=x
		uerid:(用户id)
		projectId: (项目id)
		noteId : (记事id)
		noteName:(记事标题,冗余，便于返回信息)
		flag: (0:用户没有点过;1:已经点过，心形变红)*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="updateNote.acytion")
	@ResponseBody
	public String updateNote(String userId, String projectId, String noteId, String flag){
		JSONObject jsonReturn =  new JSONObject();
		try {
			Map searchMap = new HashMap();
			searchMap.put("filter_and_noteid_EQ_S", noteId);
			searchMap.put("filter_and_userid_EQ_S", userId);
			//searchMap.put("filter_and_projectid_EQ_S", projectId);
			
			List<CommunityNote> asList = (List<CommunityNote>) communityNoteAService.findByFilterMap(searchMap);
			CommunityNote as = null;
			if(asList != null){
				if(!asList.isEmpty()){
					as = asList.get(0);
				}
			}
			
			if(as == null){
				as = new CommunityNote(projectId, noteId, userId, flag);
			} else {
				as.setFlag(flag);
			}
			
			communityNoteAService.save(as);
			
			jsonReturn.put("status", "success");
			jsonReturn.put("error_code", "");
			jsonReturn.put("error_message", "");
			jsonReturn.put("data", "");
			return jsonReturn.toString();
		} catch (Exception e) {
			LOGGER.info("CommunityNoteAction接口异常------>" + e);
			jsonReturn.put("status", "fail");
			jsonReturn.put("error_code", "");
			jsonReturn.put("error_message", "");
			jsonReturn.put("data", "");
			return jsonReturn.toString();
		}
	}
	
	/**
	 * 获取项目下/所有top5的记事
	 * @param projectId
	 * @return
	 */
	@SuppressWarnings({"rawtypes" })
	@RequestMapping(value = "/getTop5Note.do")
	@ResponseBody
	public Map getTop5Note(String projectId){
		
		List<Map<String, Object>> list = communityNoteAService.getTopByUserCount(projectId);
		return TemplateUtil.toSuccessMap(list);
	}
	
	/**
	 * 根据id查询记事状态
	 * @param projectId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getNoteFalgByNoteId.do")
	@ResponseBody
	public String getNoteFalgByNoteId(String noteId, String userId){
		Map searchMap = new HashMap();
		searchMap.put("filter_and_noteId_EQ_S", noteId);
		searchMap.put("filter_and_userId_EQ_S", userId);
		List<CommunityNote> list = communityNoteAService.findByFilterMap(searchMap);
		JSONArray josnList = new JSONArray();//数据集合(多个对象)
		JSONObject jsonMap = new JSONObject();//数据对象
		if(list!= null && list.size() > 0){
			CommunityNote communityNote = list.get(0);
			jsonMap.put("id", communityNote.getId());//Id
			jsonMap.put("noteId", communityNote.getNoteId());//记事Id
			jsonMap.put("flag", communityNote.getFlag());//记事Id和用户id对应的状态
			jsonMap.put("projectId", communityNote.getProjectId());//项目ID
			jsonMap.put("userId", communityNote.getUserId());// 用户id
			Map seaMap = new HashMap();
			seaMap.put("filter_and_noteId_EQ_S", communityNote.getNoteId());//记事Id
			List lstCntNum = communityNoteAService.findByFilterMap(seaMap);
			if(lstCntNum != null && lstCntNum.size() > 0) {
				jsonMap.put("likeNum", lstCntNum.size());//感兴趣总数
			}
			josnList.add(jsonMap);//放入JSON集合
		}else{
			jsonMap.put("flag", "0");//记事Id和用户id对应的状态
			Map seaMap = new HashMap();
			seaMap.put("filter_and_noteId_EQ_S", noteId);//记事Id
			List lstCntNum = communityNoteAService.findByFilterMap(seaMap);
			if(lstCntNum != null && lstCntNum.size() > 0) {
				jsonMap.put("likeNum", lstCntNum.size());//感兴趣总数
			}
			josnList.add(jsonMap);//放入JSON集合
		}
		JSONObject jsonReturn =  new JSONObject();
		jsonReturn.put("status", "success");
		jsonReturn.put("error_code", "");
		jsonReturn.put("error_message", "");
		jsonReturn.put("data", josnList);
		Date endDate = new Date();
		String endTime = DateUtil.DateToStr(endDate, SysConstant.DATE_FORMAT_SECONDS);
		LOGGER.info("NotejsonReturn====="+jsonReturn.toString());
		LOGGER.info("进入记事信息查询-->End"+endTime);
		return jsonReturn.toString();
	}
}
