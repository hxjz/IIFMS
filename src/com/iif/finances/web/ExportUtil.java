package com.iif.finances.web;

import com.hxjz.common.utils.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: ExportUtil
 * @Description:
 * @Author: GaoGang
 * @Date: 2017-05-31 23:53
 * @Version: V1.0
 */
public class ExportUtil {
    public static void exportStatistics(HttpServletRequest request, List data) {
        String templatePath = request.getSession().getServletContext().getRealPath("/template/财物统计表.xls");
        String date = DateUtil.getDateTime(DateUtil.DATE_FORMAT, new Date());
        String targetPath = request.getSession().getServletContext().getRealPath("/export/");
        String fileName = "财务统计表_" + date + ".xls";
        HSSFWorkbook workbook = null;    //读取excel模板
        try {
            workbook = new HSSFWorkbook(new FileInputStream(templatePath));
            try {
                HSSFSheet sheet = workbook.getSheetAt(0);   //读取第一个工作簿
                HSSFRow row;
                HSSFCell cell = null;
                int rownum = 3; //添加的起始行
                row = sheet.getRow(1);  // 从第二行开始写数据
                cell = row.getCell(1);
                cell.setCellValue("单位：北京市公安局");

                row = sheet.getRow(4);  // 第5行
                cell = row.getCell(1);
                cell.setCellValue("统计时间：" + DateUtil.getDateTime(DateUtil.TIME_FORMAT, new Date()));

                row = sheet.getRow(5);  // 第6行
                cell = row.getCell(2);
                cell.setCellValue(data.size());
                cell = row.getCell(5);
                cell.setCellValue("admin");
                cell = row.getCell(8);
                cell.setCellValue(DateUtil.getDateTime(DateUtil.TIME_FORMAT, new Date()));


                int rowNum = 7; // 第8行
                row = sheet.getRow(7);
                CellStyle style = row.getRowStyle();
                if (data.size() != 0) {
                    for (int i = 0; i < data.size(); i++) {
                        HashMap result = (HashMap) data.get(i);

                        row = sheet.createRow(rowNum);
                        cell = row.getCell(3);//财物类型
                        if (cell == null)
                            cell = row.createCell(3);
                        cell.setCellValue(result.get("typeName").toString());
                        cell = row.getCell(6);//财务统计
                        if (cell == null)
                            cell = row.createCell(6);
                        cell.setCellValue(Integer.parseInt(result.get("total").toString()));

                        rowNum++;
                    }
                }

                // 生成excel
                File file = new File(targetPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                file = new File(targetPath + fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(file);
                workbook.write(out);
                out.flush();
                out.close();
                Runtime.getRuntime().exec("cmd  /c  start  " + targetPath + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String templatePath = "e:\\财物统计表.xls";
        Date date = new Date();
        String targetPath = "e:\\export\\财务统计表" + date.getTime() + ".xls";
        HSSFWorkbook workbook = null;    //读取excel模板
        try {
            workbook = new HSSFWorkbook(new FileInputStream(templatePath));
            try {
                HSSFSheet sheet = workbook.getSheetAt(0);   //读取第一个工作簿
                HSSFRow row;
                HSSFCell cell = null;
                int rownum = 3; //添加的起始行
                row = sheet.getRow(1);  // 从第二行开始写数据
                cell = row.getCell(1);
                cell.setCellValue("单位：北京市公安局");

                row = sheet.getRow(4);  // 第5行
                cell = row.getCell(1);
                cell.setCellValue("统计时间：" + DateUtil.getDateTime(DateUtil.TIME_FORMAT, new Date()));

                row = sheet.getRow(5);  // 第6行
                cell = row.getCell(2);
//                cell.setCellValue(data.size());
                cell = row.getCell(5);
                cell.setCellValue("admindd");
                cell = row.getCell(8);
                cell.setCellValue(DateUtil.getDateTime(DateUtil.TIME_FORMAT, new Date()));


                int rowNum = 7; // 第8行
                row = sheet.getRow(7);
                int a = row.getFirstCellNum();
                int b = row.getRowNum();
                System.out.println(a);
                System.out.println(b);
                CellStyle style = row.getRowStyle();
                for (int i = 0; i < 3; i++) {

                    row = sheet.getRow(rowNum);
                    row.setRowStyle(style);
                    cell = row.getCell(3);//财物类型
                    if (cell == null) {
                        cell = row.createCell(3);
                    }

                    cell.setCellValue("足印痕迹");
                    cell = row.getCell(6);//财务统计
                    if (cell == null) {
                        cell = row.createCell(6);
                    }
                    cell.setCellValue("2");
                    rowNum++;
                }


//                if(data.size()!=0){
//                    for(int i=0;i<data.size();i++){
//                        HashMap result= (HashMap) data.get(i);
//
//                        row=sheet.getRow(rowNum);
//                        cell = row.getCell((short)1);//财物类型
//                        cell.setCellValue(result.get("financeType").toString());
//                        cell = row.getCell((short)2);//财务统计
//                        cell.setCellValue(Integer.parseInt(result.get("sum").toString()));
//                        rowNum++;
//                    }
//                }

                // 生成excel
                File file = new File("");
                if (file.exists() == false) {
                    file.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(file);
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
