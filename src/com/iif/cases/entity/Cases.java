package com.iif.cases.entity;

import java.util.Date;
import java.util.List;

import com.hxjz.common.core.orm.BaseEntity;

 /**
 *
 * @Author GaoG
 * @Date 2017
 * @Version V0.1
 */
public class Cases extends BaseEntity{
	
	private static final long serialVersionUID = -2072214904049755663L;
	
	public Cases() {
	}
	
	public Cases(String _id) {
		this();
	}
	
	private String id; //案件编码 Key
	
	private String caseName; //案件名称
	
	private Integer caseType; //案件类型
	
	private Integer isMurder; //是否命案
	
	private String caseNum; //案件编号
	
	private String casePlace; //案发地点
	
	private String caseDesc; //案件描述
	
	private String caseTimeStart; //案发时间段（起）
	
	private String caseTimeEnd; //案发时间段（止）
	
	private String siteNum; //现场勘验号
	
	private String inspectionPerson; //勘验人
	
	private String inspectionTimeStart; //勘验时间段（起）
	
	private String inspectionTimeEnd; //勘验时间段（止）
	
	private String jurisdiction; //管辖单位
	
	transient private List<?> physicalevidence;//案件与物证映射 后续添加

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Integer getCaseType() {
		return caseType;
	}

	public void setCaseType(Integer caseType) {
		this.caseType = caseType;
	}

	public Integer getIsMurder() {
		return isMurder;
	}

	public void setIsMurder(Integer isMurder) {
		this.isMurder = isMurder;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getCasePlace() {
		return casePlace;
	}

	public void setCasePlace(String casePlace) {
		this.casePlace = casePlace;
	}

	public String getCaseDesc() {
		return caseDesc;
	}

	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}

	public String getCaseTimeStart() {
		return caseTimeStart;
	}

	public void setCaseTimeStart(String caseTimeStart) {
		this.caseTimeStart = caseTimeStart;
	}

	public String getCaseTimeEnd() {
		return caseTimeEnd;
	}

	public void setCaseTimeEnd(String caseTimeEnd) {
		this.caseTimeEnd = caseTimeEnd;
	}

	public String getSiteNum() {
		return siteNum;
	}

	public void setSiteNum(String siteNum) {
		this.siteNum = siteNum;
	}

	public String getInspectionPerson() {
		return inspectionPerson;
	}

	public void setInspectionPerson(String inspectionPerson) {
		this.inspectionPerson = inspectionPerson;
	}

	public String getInspectionTimeStart() {
		return inspectionTimeStart;
	}

	public void setInspectionTimeStart(String inspectionTimeStart) {
		this.inspectionTimeStart = inspectionTimeStart;
	}

	public String getInspectionTimeEnd() {
		return inspectionTimeEnd;
	}

	public void setInspectionTimeEnd(String inspectionTimeEnd) {
		this.inspectionTimeEnd = inspectionTimeEnd;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

}