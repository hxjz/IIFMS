package com.iif.finances.entity;

import com.hxjz.common.core.orm.BaseEntity;
import com.iif.cases.entity.Cases;

import java.util.List;

/**
 * @Author GaoGang
 * @Date 2017年5月14日 下午 00点12分12秒
 * @Version V0.1
 */
public class Finances extends BaseEntity {

    private static final long serialVersionUID = -6178522609856969604L;
    private String id; // 财物编码 Key

    private Cases cases; // 相关案件

    private String financeName; // 财物名称

    private Integer financeType; // 财物类型

    private String financeNum; // 财物编号

    private Integer financeState; // 财物状态

    private Integer financeSource; // 财物来源

    private Integer sourceOffice; // 财物来源单位

    private String storeOffice;  // 财物保管单位

    private String seizedMan; // 查获人

    private String seizedTimeStart; // 查获时间段（起）

    private String seizedTimeEnd; // 查获时间段（止）

    private String financeDesc; // 财物说明

    private String financeMemo; // 财物备注

    transient private List<FinancesImages> FinanceImages; // 财物照片

    private Integer imageSign; // 是否有财物照片

    private String storeLocation; // 存放位置

    private String financeCode; // 财物识别码

    private String digitalCode; // 电子识别码

    private String entryTime; // 登记时间

    private String entryMan; // 登记人

    private String instockTime; // 入库时间

    private String instockMan; // 入库人

    private String outstockTime; // 出库时间

    private String outstockMan; // 出库人

    public Finances() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cases getCases() {
        return cases;
    }

    public void setCases(Cases cases) {
        this.cases = cases;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }

    public Integer getFinanceType() {
        return financeType;
    }

    public void setFinanceType(Integer financeType) {
        this.financeType = financeType;
    }

    public String getFinanceNum() {
        return financeNum;
    }

    public void setFinanceNum(String financeNum) {
        this.financeNum = financeNum;
    }

    public Integer getFinanceState() {
        return financeState;
    }

    public void setFinanceState(Integer financeState) {
        this.financeState = financeState;
    }

    public Integer getFinanceSource() {
        return financeSource;
    }

    public void setFinanceSource(Integer financeSource) {
        this.financeSource = financeSource;
    }

    public Integer getSourceOffice() {
        return sourceOffice;
    }

    public void setSourceOffice(Integer sourceOffice) {
        this.sourceOffice = sourceOffice;
    }

    public String getStoreOffice() {
        return storeOffice;
    }

    public void setStoreOffice(String storeOffice) {
        this.storeOffice = storeOffice;
    }

    public String getSeizedMan() {
        return seizedMan;
    }

    public void setSeizedMan(String seizedMan) {
        this.seizedMan = seizedMan;
    }

    public String getSeizedTimeStart() {
        return seizedTimeStart;
    }

    public void setSeizedTimeStart(String seizedTimeStart) {
        this.seizedTimeStart = seizedTimeStart;
    }

    public String getSeizedTimeEnd() {
        return seizedTimeEnd;
    }

    public void setSeizedTimeEnd(String seizedTimeEnd) {
        this.seizedTimeEnd = seizedTimeEnd;
    }

    public String getFinanceDesc() {
        return financeDesc;
    }

    public void setFinanceDesc(String financeDesc) {
        this.financeDesc = financeDesc;
    }

    public String getFinanceMemo() {
        return financeMemo;
    }

    public void setFinanceMemo(String financeMemo) {
        this.financeMemo = financeMemo;
    }

    public List<FinancesImages> getFinanceImages() {
        return FinanceImages;
    }

    public void setFinanceImages(List<FinancesImages> financeImages) {
        FinanceImages = financeImages;
    }

    public Integer getImageSign() {
        return imageSign;
    }

    public void setImageSign(Integer imageSign) {
        this.imageSign = imageSign;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public String getDigitalCode() {
        return digitalCode;
    }

    public void setDigitalCode(String digitalCode) {
        this.digitalCode = digitalCode;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getEntryMan() {
        return entryMan;
    }

    public void setEntryMan(String entryMan) {
        this.entryMan = entryMan;
    }

    public String getInstockTime() {
        return instockTime;
    }

    public void setInstockTime(String instockTime) {
        this.instockTime = instockTime;
    }

    public String getInstockMan() {
        return instockMan;
    }

    public void setInstockMan(String instockMan) {
        this.instockMan = instockMan;
    }

    public String getOutstockTime() {
        return outstockTime;
    }

    public void setOutstockTime(String outstockTime) {
        this.outstockTime = outstockTime;
    }

    public String getOutstockMan() {
        return outstockMan;
    }

    public void setOutstockMan(String outstockMan) {
        this.outstockMan = outstockMan;
    }
}
