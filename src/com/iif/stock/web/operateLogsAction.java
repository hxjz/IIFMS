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
 * @Date 2017年6月14日 上午12:24:07
 * @Version V0.1
 * @Desc 财务操作记录 action
 */
@Controller
@RequestMapping("/stock/*")
public class operateLogsAction extends BaseAction {
    @Autowired
    IFinancesService iFinancesService = null;
    @Autowired
    ICasesService iCasesService = null;
    @Autowired
    IStockService iStockService = null;
    /**
     * 跳转到财物操作记录
     *
     * @return
     */
    @RequestMapping("listOperateLogs.action")
    public String listOperateLogs() {
        // 财物状态下拉列表
        List<?> financeStateList = InitSelect.getSelectList(FinanceStateEnum.class);
        HttpTool.setAttribute("financeStateList", financeStateList);
        // 报送/取物单位下拉列表
    	List<?> departmentTypeList = InitSelect.getSelectList(DepartmentTypeEnum.class);
		HttpTool.setAttribute("departmentTypeList", departmentTypeList);
        // 出库原因下拉列表
    	List<?> reasonTypeList = InitSelect.getSelectList(OutstockReasonTypeEnum.class);
		HttpTool.setAttribute("outstockReasonTypeList", reasonTypeList);
		
        return "jsp/stock/listoperateLogs";
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping("showOperateLogs.action")
    @ResponseBody
    public Map showOperateLogs() {
        int pageNum = HttpTool.getIntegerParameter("page");
        int pageSize = HttpTool.getIntegerParameter("rows");
        page = new Page(pageNum, pageSize);
        Map searchMap = super.buildSearch(); // 组装查询条件

        List<Stock> stockList = iStockService.findByPage(page, searchMap);

        return TemplateUtil.toDatagridMap(page, stockList);
    }

}


