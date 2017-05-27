package com.iif.stock.web;

import java.io.File;

import jxl.*;
import jxl.write.*;

public class print {

	private static WritableCellFormat titleFormat=null;  
	public static void main(String args[]){		
		 //Arial字体，9号，粗体，单元格黄色，田字边框，居中对齐  
		WritableFont font1;
        font1 = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD, false);  

		try
		{
	        titleFormat = new WritableCellFormat (font1); 
	        titleFormat.setBackground(Colour.YELLOW);  
	        titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);  
	        titleFormat.setAlignment(Alignment.CENTRE);  
//			modifyExcel(new File("G:/红楼人物.xls"), new File("F:/红楼人物3.xls"));  
			//创建只读的 Excel 工作薄的对象副本
			Workbook wb=Workbook.getWorkbook(new File("G:\\Works\\IIFMS\\财物出入库审批表.xls"));
			// 创建真实写入的 Excel 工作薄对象
			WritableWorkbook book= Workbook.createWorkbook(new File("G:\\Works\\IIFMS\\Target.xls"),wb);
			//修改文本内容：例修改sheet2中cell B3的label内容
			WritableSheet sheet = book.getSheet(0);
			WritableCell cell = sheet.getWritableCell(5, 3);
			sheet.addCell(new Label(9,2,"modified cell",titleFormat));
			if (cell.getType() == CellType.LABEL)
			{
				Label l = (Label) cell;
				l.setString("modified cell");
			}
			//修改文本格式：例修改sheet2中cell C5的Number的格式
			WritableSheet sheet2 = book.getSheet(0);
			WritableCell cell2 = sheet2.getWritableCell(2, 4);
			NumberFormat fivedps = new NumberFormat("#.#####");
			WritableCellFormat cellFormat = new WritableCellFormat(fivedps);
			cell2.setCellFormat(cellFormat);
			//添加一个工作表
			WritableSheet sheet3=book.createSheet("第二页",1);
			sheet3.addCell(new Label(0,0,"第二页的测试数据"));
			book.write();
			book.close();
			Runtime.getRuntime().exec("cmd  /c  start  G:\\Works\\IIFMS\\Target.xls");
		} catch(Exception e) {
			System.out.println(e);
		}

    }
	

//  
//   public static void modifyExcel(File file1,File file2)   
// 
//    {     
//       try{     
//            Workbook rwb = Workbook.getWorkbook(file1);     
//            WritableWorkbook wwb = Workbook.createWorkbook(file2,rwb);//copy   
//  
//            WritableFont wfc = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,   
//                      UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLUE);   
//  
//            WritableCellFormat wcfFC = new WritableCellFormat(wfc);    
//  
//            WritableSheet ws = wwb.getSheet(0);    
//            WritableCell wc = ws.getWritableCell(0,0);  
//  
//            //判断单元格的类型,做出相应的转换   
//            if(wc.getType() == CellType.LABEL)   
//  
//            {     
//                Label labelCF =new Label(0,0,"人物（新）",wcfFC);   
//                ws.addCell(labelCF);     
//  
//              //Label label = (Label)wc;   
//  
//              //label.setString("被修改");   
//  
//            }     
//            wwb.write();     
//            wwb.close();    
//            rwb.close();     
//        }   
//  
//        catch(Exception e)     
//        {   
//            e.printStackTrace();   
//  
//        }   
//  
//    }   
  


}
