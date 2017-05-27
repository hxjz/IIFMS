package com.iif.stock.web;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.ReflectionUtil;
import com.iif.cases.entity.Cases;
import com.iif.cases.service.ICasesService;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

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

        if (!StringUtils.isEmpty(financesId)) {
            Finances finances = (Finances) iFinancesService.findById(financesId);
            HttpTool.setAttribute("finances", finances);
            Cases cases = (Cases) finances.getCases();
            HttpTool.setAttribute("cases", cases);
            HttpTool.setAttribute("casesId", cases.getId());
        }
        return "jsp/stock/instock";
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
            saveFinance.setId(financesId);
            if (!"1".equals(financeState)) {
            	saveFinance.setFinanceState(SysConstant.STOCK_STATE_IN);   // 入库
            } else {
            	saveFinance.setFinanceState(SysConstant.STOCK_STATE_OUT);   // 出库
            }
            
            saveFinance.setInstockMan(saveStock.getFetchMan());
            saveFinance.setInstockTime(new Date().toString());
            saveFinance.setUpdater("admin");// 更新人
            saveFinance.setUpdateTime(new Date()); // 更新时间
            saveFinance.setIsDel(SysConstant.IS_NOT_DEL); //删除标示
            // just for test TODO
            Cases cases=new Cases();
            cases.setId(casesId);
            saveFinance.setCases(cases);            

            //插入出入库表，记录出入库操作
            BeanUtils.copyProperties(stock, saveStock);
            saveStock.setFinancesId(saveFinance);
            saveStock.setFlag(2);  // 出入库标志，默认为0登记状态，1入库，2出库
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


