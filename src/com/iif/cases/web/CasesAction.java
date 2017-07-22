package com.iif.cases.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.ReflectionUtil;
import com.iif.cases.entity.Cases;
import com.iif.cases.service.ICasesService;
import com.iif.common.enums.CaseTypeEnum;
import com.iif.common.enums.JurisdictionSectionEnum;
import com.iif.common.util.InitSelect;
import com.iif.common.util.SysConstant;
import com.iif.common.util.TemplateUtil;
import com.iif.common.util.UserUtil;
import com.iif.finances.entity.Finances;

/**
 * @author LiuM 
 * @date 2017
 * @version V0.1
 * @Desc 案件维护
 */
@Controller
@RequestMapping("/cases/*")
public class CasesAction extends BaseAction {
	
	@Autowired
	ICasesService iCasesService = null;
	/**
	 * 跳转到案件维护列表页
	 * 
	 * @return
	 */
	@RequestMapping("listCases.action")
	public String listCases() {
		//案件类型下来菜单
		List<?> caseTypeList = InitSelect.getSelectList(CaseTypeEnum.class);
		HttpTool.setAttribute("caseTypeList", caseTypeList);
		return "jsp/cases/listCases";
	}

	/**
	 * 初始化案件维护
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping("showAll.action")
	@ResponseBody
	public Map showAll() throws Exception {
		int pageNum = HttpTool.getIntegerParameter("page");
		int pageSize = HttpTool.getIntegerParameter("rows");
		page = new Page(pageNum, pageSize);
		Map searchMap = super.buildSearch(); // 组装查询条件
		
		// 列表信息
		List<Cases> casesList = iCasesService.findByPage(page,searchMap);
		
		// 财物个数显示
		if(!CollectionUtils.isEmpty(casesList)) {
			for(int i=0; i<casesList.size();i++) {
				Cases tempCs = casesList.get(i);
				tempCs.setEvidenceNum(CollectionUtils.isEmpty(tempCs.getPhysicalevidence())?"0":String.valueOf(tempCs.getPhysicalevidence().size()));
			}
		}
		
		return TemplateUtil.toDatagridMap(page, casesList);
	}
	
	/**
	 * 跳转到案件新增或修改页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toEditCases.action")
	public String toEditCases() throws Exception {
		String casesId = HttpTool.getParameter("casesId");
		HttpTool.setAttribute("casesId", casesId);
		
		List<?> caseTypeList = InitSelect.getSelectList(CaseTypeEnum.class);
		HttpTool.setAttribute("caseTypeList", caseTypeList);
		
		List<?> jurisdictionList = InitSelect.getSelectList(JurisdictionSectionEnum.class);
		HttpTool.setAttribute("jurisdictionList", jurisdictionList);
		
		// 财物添加页面弹出用
		String fromSource = HttpTool.getParameter("fromSource");
		if(!StringUtils.isEmpty(fromSource)) {
			HttpTool.setAttribute("fromSource", fromSource);
		}
				
		
		if(!StringUtils.isEmpty(casesId)) {
			Cases casesShow = (Cases)iCasesService.findById(casesId);
			HttpTool.setAttribute("cases", casesShow);
		}
		
		return "jsp/cases/editCases";
	}
	
	/**
	 * 保存案件信息（新增或修改）
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("saveCases.action")
	@ResponseBody
	public Map saveCases(Cases cases){
		String casesId=HttpTool.getParameter("id");
		
		Cases saveCases = new Cases();
		if(null != cases && StringUtils.isNotEmpty(cases.getCaseTimeStart())) {
			String[] strTimeArr = cases.getCaseTimeStart().split(",");
			if(null != strTimeArr && strTimeArr.length >0) {
				cases.setCaseTimeStart(StringUtils.isNotEmpty(strTimeArr[0])?strTimeArr[0]:strTimeArr[1]);
			}else {
				String oldCaseTimeStart = HttpTool.getParameter("oldCaseTimeStart");
				if(StringUtils.isNotEmpty(oldCaseTimeStart)) {
					cases.setCaseTimeStart(oldCaseTimeStart.substring(0, 10));
				}
			}
		}
		
		if(!StringUtils.isEmpty(casesId)) {
			saveCases = (Cases) iCasesService.findById(casesId);
			ReflectionUtil.copyPropertiesForHasValueIgnoreSerialVersionUID(saveCases, cases);
		}else {
			BeanUtils.copyProperties(cases, saveCases);
			saveCases.setCreateTime(new Date());// 创建时间
			saveCases.setCreator(UserUtil.getCurrentUser().getUserAccount()); // 当前登录人
			saveCases.setIsDel(SysConstant.IS_NOT_DEL); //删除标示 
			saveCases.setId(null);
		}
		saveCases.setIsMurder(cases.getIsMurder()==null?Integer.valueOf(SysConstant.SYSTEM_CON_ZER):Integer.valueOf(SysConstant.SYSTEM_CON_ONE));//Murder标示
		saveCases.setUpdateTime(new Date()); // 更新时间
		saveCases.setUpdater(UserUtil.getCurrentUser().getUserAccount());// 当前登录人
		
		try{
			iCasesService.save(saveCases);
			return TemplateUtil.toSuccessMap("操作成功！" + "," + saveCases.getId());
		} catch(Exception e) {
			e.printStackTrace();
			return TemplateUtil.toSuccessMap("操作失败！");
		}
	}
	
	/**
	 * 跳转到案件新增或修改页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toDetailCases.action")
	public String toDetailCases() throws Exception {
		List<?> caseTypeList = InitSelect.getSelectList(CaseTypeEnum.class);
		HttpTool.setAttribute("caseTypeList", caseTypeList);
		
		List<?> jurisdictionList = InitSelect.getSelectList(JurisdictionSectionEnum.class);
		HttpTool.setAttribute("jurisdictionList", jurisdictionList);
		
		String casesId = HttpTool.getParameter("casesId");
		
		if(StringUtils.isNotEmpty(casesId)) {
			Cases cases = (Cases)iCasesService.findById(casesId);
			HttpTool.setAttribute("cases", cases);
		}
		
		return "jsp/cases/casesDetail";
	}
	
	/**
	 * 删除案件信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("delCases.action")
	@ResponseBody
	public Map delCases(){
		
		String casesId=HttpTool.getParameter("casesId");
		
		try{
			if(StringUtils.isNotEmpty(casesId)) {
				Cases delCases = (Cases)iCasesService.findById(casesId);
				//在有关联的财物时不能删除
				if(null != delCases && !CollectionUtils.isEmpty(delCases.getPhysicalevidence())) {
					for(int i=0; i<delCases.getPhysicalevidence().size(); i++) {
						Finances finChk = delCases.getPhysicalevidence().get(i);
						if(null != finChk && finChk.getIsDel()==0) {
							return TemplateUtil.toSuccessMap("有关联的财物，该案件不能删除。");
						}
					}
				}
				
				delCases.setIsDel(SysConstant.IS_DEL); //删除标示
				delCases.setUpdater("admin");
				delCases.setUpdateTime(new Date());
				iCasesService.save(delCases);
			}
			
			return TemplateUtil.toSuccessMap("操作成功！");
		}catch(Exception e){
			e.printStackTrace();
			return TemplateUtil.toSuccessMap("操作失败！");
		}
	}
	
	/**
	 * 跳转到案件选择页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toSelectCase.action")
	public String toSelectCase() throws Exception {
		
		return "jsp/prompt/casesSelect";
	}

}
