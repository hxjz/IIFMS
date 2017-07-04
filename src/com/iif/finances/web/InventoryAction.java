package com.iif.finances.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.iif.common.enums.FinanceStateEnum;
import com.iif.common.enums.FinanceTypeEnum;
import com.iif.common.util.InitSelect;
import com.iif.common.util.TemplateUtil;
import com.iif.finances.entity.Finances;
import com.iif.finances.service.IFinancesService;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author M
 * @Date 2017年7月3日 下午10:24:07
 * @Version V0.1
 * @Desc 异常财物 action
 */
@Controller
@RequestMapping("/finances/*")
public class InventoryAction extends BaseAction {
    @Autowired
    IFinancesService iFinancesService = null;
    
    /**
     * 跳转到财物详情
     *
     * @return
     */
    @RequestMapping("listInventory.action")
    public String listInventory() {
        // 财物状态下拉列表
        List<?> financeStateList = InitSelect.getSelectList(FinanceStateEnum.class);
        HttpTool.setAttribute("financeStateList", financeStateList);
        // 财物类型下拉列表
        List<?> financeTypeList = InitSelect.getSelectList(FinanceTypeEnum.class);
        HttpTool.setAttribute("financeTypeList", financeTypeList);
        return "jsp/finances/listInventory";
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping("showInventory.action")
    @ResponseBody
    public Map showInventory() {
        int pageNum = HttpTool.getIntegerParameter("page");
        int pageSize = HttpTool.getIntegerParameter("rows");
        page = new Page(pageNum, pageSize);
        Map searchMap = super.buildSearch(); // 组装查询条件

        List<Finances> financeList = iFinancesService.findByPage(page, searchMap);

        return TemplateUtil.toDatagridMap(page, financeList);
    }

    @RequestMapping("upLoad.action")
    @ResponseBody
    public Map upLoad(HttpServletRequest request) {
    	String savePath = request.getSession().getServletContext().getRealPath("/template/财物统计表.xls");
    	return null;
    }
    

}


