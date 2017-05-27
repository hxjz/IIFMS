package com.iif.stock.entity;

import com.hxjz.common.core.orm.BaseEntity;
import com.iif.cases.entity.Cases;
import com.iif.finances.entity.Finances;

import java.util.List;

/**
 * @Author M
 * @Date 2017年5月24日 下午5点52分12秒
 * @Version V0.1
 */
public class Stock extends BaseEntity {

    private static final long serialVersionUID = -6178522609856969604L;
    private String id; // 出入库Key    

    private Finances financesId; // 财物Key

    private Integer flag; // 出入库标志，默认为0登记状态，1入库，2出库

	private String fetchMan; // 取物人或送物人

    private String operator; // 经办人

    private Integer department; // 报送或取物单位

    private Integer reason; // 出库原因

    public Stock() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Finances getFinancesId() {
		return financesId;
	}

	public void setFinancesId(Finances financesId) {
		this.financesId = financesId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getFetchMan() {
		return fetchMan;
	}

	public void setFetchMan(String fetchMan) {
		this.fetchMan = fetchMan;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
