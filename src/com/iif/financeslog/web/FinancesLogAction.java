package com.iif.financeslog.web;

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
import com.iif.common.util.InitSelect;
import com.iif.common.util.TemplateUtil;
import com.iif.finances.entity.Finances;
import com.iif.finances.service.IFinancesService;

/**
 * @Author LiuM
 * @Date 2017
 * @Version V0.1
 * @Desc 财物操作日志 action
 */
@Controller
@RequestMapping("/financeslog/*")
public class FinancesLogAction extends BaseAction {
    @Autowired
    IFinancesService iFinancesService = null;
    
    /**
     * 跳转到财物操作日志列表
     *
     * @return
     */
    @RequestMapping("listFinanceslog.action")
    public String listFinances() {
        // 出入库类型下拉列表
        List<?> financeStateList = InitSelect.getSelectList(FinanceStateEnum.class);
        HttpTool.setAttribute("financeStateList", financeStateList);
        return "jsp/financeslog/listFinanceslog";
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
    
}
