package com.iif.stock.web;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.ReflectionUtil;
import com.iif.cases.entity.Cases;
import com.iif.cases.service.ICasesService;
import com.iif.common.enums.CaseTypeEnum;
import com.iif.common.enums.DepartmentTypeEnum;
import com.iif.common.enums.FinanceStateEnum;
import com.iif.common.enums.FinanceTypeEnum;
import com.iif.common.enums.OutstockReasonTypeEnum;
import com.iif.common.util.InitSelect;
import com.iif.common.util.SysConstant;
import com.iif.common.util.TemplateUtil;
import com.iif.finances.entity.Finances;
import com.iif.finances.service.IFinancesService;
import com.iif.stock.entity.Stock;
import com.iif.stock.service.IStockService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
     */
    @RequestMapping("toPrintExcel.action")
    public String toPrintExcel() throws Exception {
        System.out.println("***************Excel  print**********");
        String caseId = HttpTool.getParameter("caseId");
        String caseNum = HttpTool.getParameter("caseNum");
        String caseName = HttpTool.getParameter("caseName");
        String financesId = HttpTool.getParameter("financeId");
        String financesName = HttpTool.getParameter("financeName");
        String financesNum = HttpTool.getParameter("financeNum");
        String fetchMan = HttpTool.getParameter("fetchMan");
        String operator = HttpTool.getParameter("operator");

        System.out.println("caseId:" + caseId);
        System.out.println("caseNum:" + caseNum);
        System.out.println("caseName:" + caseName);
        System.out.println("financesId:" + financesId);
        System.out.println("financesName:" + financesName);
        System.out.println("financesNum:" + financesNum);
        System.out.println("fetchMan:" + fetchMan);
        System.out.println("operator:" + operator);
        System.out.println("***************Excel  print**********");
		//创建只读的 Excel 工作薄的对象副本
//		Workbook wb=Workbook.getWorkbook(new File("G:\\Works\\IIFMS\\财物出入库审批表.xls"));
        //System.out.println("***" + stockAction.class.getResource("/").getFile()); 
        String basePath = stockAction.class.getResource("/").getFile();
        String excelPath = basePath + SysConstant.STOCK_IN_EXCEL_NAME;
        String excelPathPrint = basePath + SysConstant.STOCK_IN_EXCEL_NAME2;
        //String excelPathPrint = stockAction.class.getResource(SysConstant.STOCK_IN_EXCEL_NAME2).getFile();


		Workbook wb=Workbook.getWorkbook(new File(excelPath));
		
		// 创建真实写入的 Excel 工作薄对象
		WritableWorkbook book= Workbook.createWorkbook(new File(excelPathPrint),wb);
		//修改文本内容：例修改sheet2中cell B3的label内容
		WritableSheet sheet = book.getSheet(0);
		sheet.addCell(new Label(9,2,"modified cell"));
		sheet.addCell(new Label(3,3,operator));
		sheet.addCell(new Label(9,3,new Date().toString()));
		sheet.addCell(new Label(3,5,caseName));
		sheet.addCell(new Label(3,6,caseNum));
		book.write();
		book.close();
		Runtime.getRuntime().exec("cmd  /c  start " + excelPathPrint);
        return "jsp/stock/outstock";
    }
    
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
            
            saveFinance.setUpdater("admin");// 更新人
            saveFinance.setUpdateTime(new Date()); // 更新时间
            saveFinance.setIsDel(SysConstant.IS_NOT_DEL); //删除标示
            // just for test TODO
            Cases cases=new Cases();
            cases.setId(casesId);
            saveFinance.setCases(cases);            

            //插入出入库表，记录出入库操作
            saveStock.setFinancesId(saveFinance);
            saveStock.setCreateTime(new Date());// 创建时间
            saveStock.setCreator("admin"); // 当前登录人
            saveStock.setIsDel(SysConstant.IS_NOT_DEL); //删除标示
            saveStock.setId(null);
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


