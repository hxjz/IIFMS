package com.iif.finances.web;

import com.hxjz.common.utils.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
        String templatePath=request.getSession().getServletContext().getRealPath("/template/财务统计表.xls");
        String date= DateUtil.convertDateToString(new Date());
        String targetPath=request.getSession().getServletContext().getRealPath("/export/财务统计表+"+date+".xls");
        HSSFWorkbook workbook = null;    //读取excel模板
        try {
            workbook = new HSSFWorkbook(new FileInputStream(templatePath));
            try {
                HSSFSheet sheet = workbook.getSheetAt(0);   //读取第一个工作簿
                HSSFRow row;
                HSSFCell cell = null;
                int rownum = 3; //添加的起始行
                row=sheet.getRow(1);  // 从第二行开始写数据
                cell=row.getCell(1);
                cell.setCellValue("单位：北京市公安局");

                row=sheet.getRow(4);  // 第5行
                cell=row.getCell(1);
                cell.setCellValue("统计时间："+ DateUtil.getDateTime(DateUtil.TIME_FORMAT,new Date()));

                row=sheet.getRow(5);  // 第6行
                cell=row.getCell(2);
                cell.setCellValue(data.size());
                cell=row.getCell(4);
                cell.setCellValue("admin");
                cell=row.getCell(6);
                cell.setCellValue(DateUtil.getDateTime(DateUtil.TIME_FORMAT,new Date()));


                int rowNum=7; // 第8行
                if(data.size()!=0){
                    for(int i=0;i<data.size();i++){
                        HashMap result= (HashMap) data.get(i);

                        row=sheet.getRow(rowNum);
                        cell = row.getCell((short)1);//财物类型
                        cell.setCellValue(result.get("financeType").toString());
                        cell = row.getCell((short)2);//财务统计
                        cell.setCellValue(Integer.parseInt(result.get("sum").toString()));
                        rowNum++;
                    }
                }

              // 生成excel
                File file = new File(targetPath);
                if (file.exists() == false) {
                    file.mkdirs();
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

    /**
     * 服务器上生成excel文件
     *
     * @param pCompId
     * @param pTargetWorkbookPath
     * @param targetwb
     * @param pBeginDate
     * @param pEndDate
     */
//    private String createFile(int pCompId, String pTargetWorkbookPath, HSSFWorkbook targetwb, int pBeginDate,
//                              int pEndDate) {
//        // 写文件
//        String excelName = pBeginDate + "-" + pEndDate + "_" + pCompId + "_" + "MonthYKDKCountReport.xls";
//        String zipName = pBeginDate + "-" + pEndDate + "_" + pCompId + "_" + "MonthYKDKCountReport.ZIP";
//        FileOutputStream fOut;
//        File file;
//        String Path = pTargetWorkbookPath + pEndDate + "/";
//        try {
//            // 生成excel
//            file = new File(Path);
//            if (file.exists() == false) {
//                file.mkdirs();
//            }
//            file = new File(Path + excelName);
//            fOut = new FileOutputStream(file);
//            targetwb.write(fOut);
//            fOut.flush();
//            fOut.close();
//            // 将excel文件打包成ZIP
//            FileOutputStream fileOut = new FileOutputStream(Path + zipName);
//            ZipOutputStream outputStream = new ZipOutputStream(fileOut);
//            File excelFile = new File(Path + excelName);
//            FileInputStream fileIn = new FileInputStream(excelFile);
//            outputStream.putNextEntry(new ZipEntry(excelName));
//            byte[] buffer = new byte[1024];
//            while (fileIn.read(buffer) != -1) {
//                outputStream.write(buffer);
//            }
//            outputStream.closeEntry();
//            fileIn.close();
//            outputStream.close();
//            // 删除Excel文件
//            file.delete();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return zipName;
//    }

}
