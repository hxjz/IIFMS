package com.iif.stock.web;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.DateUtil;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.ReflectionUtil;
import com.iif.cases.entity.Cases;
import com.iif.cases.service.ICasesService;
import com.iif.common.enums.DepartmentTypeEnum;
import com.iif.common.enums.FinanceStateEnum;
import com.iif.common.enums.FinanceTypeEnum;
import com.iif.common.enums.OutstockReasonTypeEnum;
import com.iif.common.util.ExportExcelUtil;
import com.iif.common.util.InitSelect;
import com.iif.common.util.SysConstant;
import com.iif.common.util.TemplateUtil;
import com.iif.common.util.UserUtil;
import com.iif.finances.entity.Finances;
import com.iif.finances.service.IFinancesService;
import com.iif.stock.entity.Stock;
import com.iif.stock.service.IStockService;

/**
 * @Author M
 * @Date 2017年5月21日 上午10:24:07
 * @Version V0.1
 * @Desc 出入库管理 action
 */
@Controller
@RequestMapping("/stock/*")
public class stockAction extends BaseAction {
    @Autowired
    IFinancesService iFinancesService = null;
    @Autowired
    ICasesService iCasesService = null;
    @Autowired
    IStockService iStockService = null;

    ExportExcelUtil<Stock> exportExcelUtil = new ExportExcelUtil<Stock>();
    /**
     * 跳转到财物详情
     *
     * @return
     */
    @RequestMapping("listStock.action")
    public String listStock() {
        // 财物状态下拉列表
        List<?> financeStateList = InitSelect.getSelectList(FinanceStateEnum.class);
        HttpTool.setAttribute("financeStateList", financeStateList);
        // 财物类型下拉列表
        List<?> financeTypeList = InitSelect.getSelectList(FinanceTypeEnum.class);
        HttpTool.setAttribute("financeTypeList", financeTypeList);
        return "jsp/stock/liststock";
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping("showAll.action")
    @ResponseBody
    public Map showAllFinances() {
        int pageNum = HttpTool.getIntegerParameter("page");
        int pageSize = HttpTool.getIntegerParameter("rows");
        page = new Page(pageNum, pageSize);
        Map searchMap = super.buildSearch(); // 组装查询条件

        List<Finances> financeList = iFinancesService.findByPage(page, searchMap);

        return TemplateUtil.toDatagridMap(page, financeList);
    }

    /**
     * 跳转到 添加    出库页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("toOutstock.action")
    public String toOutstock() throws Exception {
    	List<?> outstockReasonTypeList = InitSelect.getSelectList(OutstockReasonTypeEnum.class);
		HttpTool.setAttribute("outstockReasonTypeList", outstockReasonTypeList);
    	List<?> departmentTypeList = InitSelect.getSelectList(DepartmentTypeEnum.class);
		HttpTool.setAttribute("departmentTypeList", departmentTypeList);
        String financesId = HttpTool.getParameter("financesId");
        HttpTool.setAttribute("financesId", financesId);

        if (!StringUtils.isEmpty(financesId)) {
            Finances finances = (Finances) iFinancesService.findById(financesId);
            HttpTool.setAttribute("finances", finances);
            Cases cases = (Cases) finances.getCases();
            HttpTool.setAttribute("cases", cases);
            HttpTool.setAttribute("casesId", cases.getId());
        }
        return "jsp/stock/outstock";
    }

    /**
     * 跳转到 添加    入库页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("toInstock.action")
    public String toInstock() throws Exception {
        String financesId = HttpTool.getParameter("financesId");
        HttpTool.setAttribute("financesId", financesId);
    	List<?> departmentTypeList = InitSelect.getSelectList(DepartmentTypeEnum.class);
		HttpTool.setAttribute("departmentTypeList", departmentTypeList);

        if (!StringUtils.isEmpty(financesId)) {
            Finances finances = (Finances) iFinancesService.findById(financesId);
            HttpTool.setAttribute("finances", finances);
            Cases cases = (Cases) finances.getCases();
            HttpTool.setAttribute("cases", cases);
            HttpTool.setAttribute("casesId", cases.getId());
        }
        return "jsp/stock/instock";
    }

    /**
     * 生成Excel
     * @return
     * @throws Exception 
     */
    @RequestMapping("outstockExcel.action")
    public String outstockExcel(HttpServletRequest request) throws Exception{
    	toPrintExcel(request);
    	return null;
    }
    /**
     * 生成Excel
     * @return
     */
    @RequestMapping("instockExcel.action")
    public String instockExcel(HttpServletRequest request) throws Exception{
    	toPrintExcel(request);
    	return null;
    }
    
    public void toPrintExcel(HttpServletRequest request) throws Exception {
        try {
			//创建只读的 Excel 工作薄的对象副本
	        String templatePath = request.getSession().getServletContext().getRealPath("/template/财物出入库审批表.xls"); 
	        String exportPath = request.getSession().getServletContext().getRealPath("/export/财物出入库审批表" + UUID.randomUUID().toString() + ".xls"); 
	        Workbook wb=Workbook.getWorkbook(new File(templatePath));
			
			// 创建真实写入的 Excel 工作薄对象
			WritableWorkbook book= Workbook.createWorkbook(new File(exportPath),wb);
			//修改文本内容：例修改sheet2中cell B3的label内容
			WritableSheet sheet = book.getSheet(0);
			
			//
			WritableCellFormat tempCellFormat = null;  
            tempCellFormat = getBodyCellStyle();  
            
            /**  将数据写入Excel  **/
			//单位：XXXXXX
			sheet.addCell(new Label(1,0,"单位：北京市公安局"));
			//操作记录编号
			sheet.addCell(new Label(9,2,""));
			//操作员             //制表时间
			sheet.addCell(new Label(3,3, HttpTool.getParameter("operator"),tempCellFormat));
			sheet.addCell(new Label(9,3, DateUtil.getDateTime(DateUtil.DATE_FORMAT, new Date()),tempCellFormat));
			//案件名称       //案件编号
			sheet.addCell(new Label(3,5, HttpTool.getParameter("caseName"),tempCellFormat));
			sheet.addCell(new Label(8,5, HttpTool.getParameter("caseNum"),tempCellFormat));
			//财物名称      财物种类
			sheet.addCell(new Label(3,6, HttpTool.getParameter("financeName"),tempCellFormat));
			sheet.addCell(new Label(8,6, HttpTool.getParameter("financeNum"),tempCellFormat));
			//财物识别码    电子识别码
			sheet.addCell(new Label(3,7,"财物识别码",tempCellFormat));
			sheet.addCell(new Label(8,7," 电子识别码",tempCellFormat));
			//取物人    出库原因
			sheet.addCell(new Label(3,8, HttpTool.getParameter("fetchMan"),tempCellFormat));
			sheet.addCell(new Label(8,8,"出库原因",tempCellFormat));
			//存放位置
			sheet.addCell(new Label(3,9,"存放位置",tempCellFormat));
			//财物初步分析意见/备注
			sheet.addCell(new Label(3,10,"备注",tempCellFormat));
			book.write();
			book.close();
			Runtime.getRuntime().exec("cmd  /c  start " + exportPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** 
     * 表头单元格样式的设定 
     */  
    public WritableCellFormat getBodyCellStyle(){  
          
        /* 
         * WritableFont.createFont("宋体")：设置字体为宋体 
         * 11：设置字体大小 
         * WritableFont.NO_BOLD:设置字体非加粗（BOLD：加粗     NO_BOLD：不加粗） 
         * false：设置非斜体 
         * UnderlineStyle.NO_UNDERLINE：没有下划线 
         */  
        WritableFont font = new WritableFont(WritableFont.createFont("宋体"),  
                                             11,   
                                             WritableFont.NO_BOLD,   
                                             false,  
                                             UnderlineStyle.NO_UNDERLINE);  
          
        WritableCellFormat bodyFormat = new WritableCellFormat(font);  
        try {  
            //设置单元格背景色：表体为白色  
            bodyFormat.setBackground(Colour.WHITE);  
            //设置表头表格边框样式  
            //整个表格线为细线、黑色  
            bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);  
              
        } catch (WriteException e) {  
            System.out.println("表体单元格样式设置失败！");  
        }  
        return bodyFormat;  
    }  
    
    @SuppressWarnings("rawtypes")
	@RequestMapping("saveStock.action")
    @ResponseBody
    public Map saveStock(Finances finance,Stock stock){
        String financesId = HttpTool.getParameter("id");
        String casesId = HttpTool.getParameter("casesid");
        String financeState = HttpTool.getParameter("financeState");
        HttpTool.setAttribute("financesId", financesId);
        HttpTool.setAttribute("casesId", casesId);

        Finances saveFinance=new Finances();
        Stock saveStock = new Stock();
        // 更新Finances、插入Stock操作
        if(!StringUtils.isEmpty(financesId)) {
        	//更新财物表出入库状态相关信息
            saveFinance = (Finances) iFinancesService.findById(financesId);
            ReflectionUtil.copyPropertiesForHasValueIgnoreSerialVersionUID(saveFinance, finance);
            BeanUtils.copyProperties(stock, saveStock);
            saveFinance.setId(financesId);
            // 出入库标志，默认为1登记状态，2入库，3出库
            if (new Integer(financeState) != SysConstant.STOCK_STATE_IN) {
            	saveFinance.setFinanceState(SysConstant.STOCK_STATE_IN);   // 入库
                saveFinance.setInstockMan(saveStock.getFetchMan());
                saveFinance.setInstockTime(new Date().toString());
                saveStock.setFlag(SysConstant.STOCK_STATE_IN);
            } else {
            	saveFinance.setFinanceState(SysConstant.STOCK_STATE_OUT);   // 出库
                saveFinance.setOutstockMan(saveStock.getFetchMan());
                saveFinance.setOutstockTime(new Date().toString());
                saveStock.setFlag(SysConstant.STOCK_STATE_OUT);
            }
            
            saveFinance.setUpdater(UserUtil.getCurrentUser().getUserAccount());// 更新人
            saveFinance.setUpdateTime(new Date()); // 更新时间
            saveFinance.setIsDel(SysConstant.IS_NOT_DEL); //删除标示
            Cases cases=new Cases();
            cases.setId(casesId);
            saveFinance.setCases(cases);            

            //插入出入库表，记录出入库操作
            saveStock.setId(null);
            saveStock.setFinances(saveFinance);
            saveStock.setCreateTime(new Date());// 创建时间
            saveStock.setCreator(UserUtil.getCurrentUser().getUserAccount()); // 当前登录人
            saveStock.setIsDel(SysConstant.IS_NOT_DEL); //删除标示
            saveStock.setUpdateTime(new Date()); // 更新时间
            saveStock.setUpdater(UserUtil.getCurrentUser().getUserAccount());// 当前登录人
        } else {
            return TemplateUtil.toSuccessMap("没找到指定财物！");
        }

        try{
            iFinancesService.save(saveFinance);
            iStockService.save(saveStock);
            return TemplateUtil.toSuccessMap("操作成功！");
        } catch(Exception e) {
            e.printStackTrace();
            return TemplateUtil.toSuccessMap("操作失败！");
        }
    }

}


