package com.iif.office;

import java.io.File;

import com.iif.office.excel.ExcelComponent;
import com.iif.office.excel.ExcelResultEntity;

/**
 * static factory
 * 
 * @author LiuM
 * 
 */
public class OfficeComponents {

	public static <T> ExcelResultEntity<T> excelReadForList(File file,
			ExcelComponent<T> excel) {
		return excel.readForList(file);
	}

	public static <T> ExcelResultEntity<T> excelReadForList(String filename,
			ExcelComponent<T> excel) {
		return excelReadForList(new File(filename), excel);
	}

}
