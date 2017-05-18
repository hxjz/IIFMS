package com.iif.office.excel;

import java.util.List;

import com.iif.office.ResultEntity;

/**
 * 
 * @author quzile
 * 
 * @param <T>
 */
public class ExcelResultEntity<T> implements ResultEntity<T> {

	private List<T> list;

	private int correctNum;

	public ExcelResultEntity(List<T> list, int correctNum) {
		this.list = list;
		this.correctNum = correctNum;
	}

	/**
	 * 总数
	 * 
	 * @return
	 */
	public int size() {
		return list.size();
	}

	/**
	 * 
	 * @return
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 成功的数量
	 * 
	 * @return
	 */
	public int getCorrectNum() {
		return correctNum;
	}

}
