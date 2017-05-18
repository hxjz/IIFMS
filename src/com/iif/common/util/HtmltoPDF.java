package com.iif.common.util;

import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;

import org.apache.log4j.Logger;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

/**
 * HTML转pdf文件
 * @author 
 */
public class HtmltoPDF {

		private static Logger logger = Logger.getLogger(HtmltoPDF.class);
	
		public static String pdfHeaderLogo = "http://localhost:8080/IIFMS/img/logo/logo.png"; // 默认地址，在spring中可以注入

		public static PD4ML pd4ml = null;

		private static void initPD4ML() {
			pd4ml = new PD4ML();

	        pd4ml.setPageInsets(new Insets(0, 20, 10, 20));
	        pd4ml.setHtmlWidth(675);
	        pd4ml.setPageSize(PD4Constants.A4);
	        try {
				pd4ml.useTTF("java:com/iif/common/fonts", true);
			} catch (FileNotFoundException e) {
				logger.error("---HtmltoPDF-加载字体失败", e);
			}
	        //pd4ml.setDefaultTTFs("SimHei", "SimSun", "YouYuan");

	       //页眉  
	        PD4PageMark headerMark = new PD4PageMark();  
	        headerMark.setAreaHeight(40);  
	        headerMark.setInitialPageNumber(0);  
	        headerMark.setPagesToSkip(0);  
	        headerMark.setTitleAlignment(PD4PageMark.CENTER_ALIGN);  
	        headerMark.setHtmlTemplate("<div id='lg'><img width='100' height='32' src='"+pdfHeaderLogo+"' height='30'></div><hr/>"); // autocompute  
	        pd4ml.setPageHeader(headerMark);

	        //页脚
	        PD4PageMark footerMark = new PD4PageMark();  
	        footerMark.setAreaHeight(20);
	        footerMark.setInitialPageNumber(10);
	        footerMark.setHtmlTemplate("");
	        pd4ml.setPageFooter(footerMark);

		}

		/**
		 * htmlstr装pdf文件
		 * @param htmlStr
		 * @param filePath
		 * @throws Exception
		 */
	    public static void htmlStrToPdf(String htmlStr,String filePath) throws Exception   
	    {       
	    	if(!new File(filePath).getParentFile().exists()) {
				new File(filePath).getParentFile().mkdirs();
			}
	    	FileOutputStream fos = new FileOutputStream(filePath);
	       
	    	if( pd4ml== null) {
	    		initPD4ML();
	    	}

	        pd4ml.enableDebugInfo();
	        pd4ml.render(new StringReader(htmlStr), fos);
	    }

		public static void setPdfHeaderLogo(String pdfHeaderLogo) {
			HtmltoPDF.pdfHeaderLogo = pdfHeaderLogo;
		}
		
}
