package com.iif.finances.web;

import com.hxjz.common.utils.DateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

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
    public static void exportStatistics(HttpServletRequest request, List datas) {
        String templatePath = request.getSession().getServletContext().getRealPath("/template/财物统计表.xls");
        String date = DateUtil.getDateTime(DateUtil.DATE_FORMAT, new Date());
        String targetPath = request.getSession().getServletContext().getRealPath("/export/");
        String fileName = "财物统计表_" + date + ".xls";
        HSSFWorkbook workbook = null;    //读取excel模板
        try {
            workbook = new HSSFWorkbook(new FileInputStream(templatePath));
            try {
                HSSFSheet sheet = workbook.getSheetAt(0);   //读取第一个工作簿
                HSSFRow row;
                HSSFCell cell = null;
                HSSFCellStyle style = setCellStyle(workbook);
                int rowNum = 3; //添加的起始行
                row = sheet.getRow(1);  // 从第二行开始写数据
                cell = row.getCell(1);
                cell.setCellValue("单位：北京市公安局");

                row = sheet.getRow(4);  // 第5行
                cell = row.getCell(1);
                cell.setCellValue("统计时间：" + DateUtil.getDateTime(DateUtil.TIME_FORMAT, new Date()));
                sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 1, 3));

                row = sheet.getRow(5);  // 第6行
                cell = row.getCell(2);
                cell.setCellValue(datas.size());
                cell = row.getCell(5);
                cell.setCellValue("admin");
                cell = row.getCell(8);
                cell.setCellValue(DateUtil.getDateTime(DateUtil.TIME_FORMAT, new Date()));


                rowNum = 7; // 第8行
                if (datas.size() != 0) {
                    for (Object data : datas) {
                        HashMap result = (HashMap) data;
                        row = sheet.createRow(rowNum);
                        cell = row.getCell(1);//财物类型
                        if (cell == null)
                            cell = row.createCell(1);
                        cell.setCellStyle(style);
                        cell.setCellValue(result.get("typeName").toString());

                        cell = row.getCell(4);//财物统计
                        if (cell == null)
                            cell = row.createCell(4);
                        cell.setCellStyle(style);
                        cell.setCellValue(Integer.parseInt(result.get("total").toString()));

                        // 合并单元格 处理
                        int []columns=new int[]{1,4,7};
                        for (int column : columns) {
                            CellRangeAddress cra = new CellRangeAddress(rowNum, rowNum, column, column+2); // 起始行, 终止行, 起始列, 终止列
                            sheet.addMergedRegion(cra);
                            setMergedRegionStyle(cra,sheet,workbook);
                        }

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

    private static HSSFCellStyle setCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle(); // 样式对象
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
        Font font = workbook.createFont();
        font.setFontName("黑体");//字体类型
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
//        style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//下边框类型
//        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);//左边框类型
//        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//右边框类型
//        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框类型
        return style;
    }

    private static void setMergedRegionStyle(CellRangeAddress cra, HSSFSheet sheet, HSSFWorkbook workbook) {
        RegionUtil.setBorderBottom(2, cra, sheet, workbook); // 下边框
        RegionUtil.setBorderLeft(2, cra, sheet, workbook); // 左边框
        RegionUtil.setBorderRight(2, cra, sheet, workbook); // 有边框
        RegionUtil.setBorderTop(2, cra, sheet, workbook); // 上边框
    }

    public static void main(String[] args) {
        String templatePath = "e:\\财物统计表.xls";
        String date = DateUtil.getDateTime(DateUtil.DATE_FORMAT, new Date());
        String targetPath = "e:\\财物统计表_" + date + ".xls";
        HSSFWorkbook workbook = null;    //读取excel模板
        try {
            workbook = new HSSFWorkbook(new FileInputStream(templatePath));
            try {
                HSSFSheet sheet = workbook.getSheetAt(0);   //读取第一个工作簿
                HSSFRow row;
                HSSFCell cell = null;
                int rowNum = 3; //添加的起始行
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

                rowNum = 7; // 第8行
                HSSFCellStyle newstaye = setCellStyle(workbook);
                row = sheet.getRow(7);
//                CellStyle style = row.getRowStyle();
                for (int i = 0; i < 5; i++) {

                    row = sheet.getRow(rowNum);
                    if (row == null) {
                        row = sheet.createRow(rowNum);
                    }

                    cell = row.createCell((short) 1);

                    cell = row.getCell(1);//财物类型
                    if (cell == null) {
                        cell = row.createCell(1);
                    }
                    cell.setCellValue("足印痕迹" + i);

                    cell = row.getCell(4);//财物统计
                    if (cell == null) {
                        cell = row.createCell(4);
                    }
                    cell.setCellValue("2" + i);
                    // 合并单元格
                    CellRangeAddress cra = new CellRangeAddress(rowNum, rowNum, 1, 3); // 起始行, 终止行, 起始列, 终止列
                    sheet.addMergedRegion(cra);

                    sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 1, 3));
                    sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 4, 6));
                    sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 7, 9));
                    RegionUtil.setBorderBottom(2, cra, sheet, workbook); // 下边框
                    RegionUtil.setBorderLeft(2, cra, sheet, workbook); // 左边框
                    RegionUtil.setBorderRight(2, cra, sheet, workbook); // 有边框
                    RegionUtil.setBorderTop(2, cra, sheet, workbook); // 上边框
                    rowNum++;
                }


                // 生成excel
                File file = new File(targetPath);
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
