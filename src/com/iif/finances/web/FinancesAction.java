package com.iif.finances.web;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.iif.common.enums.*;
import com.iif.common.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.hxjz.common.utils.ReflectionUtil;
import com.iif.cases.entity.Cases;
import com.iif.finances.entity.Finances;
import com.iif.finances.service.IFinancesService;
import com.iif.stock.entity.Stock;
import com.iif.stock.service.IStockService;

/**
 * @Author GaoGang
 * @Date 2017年5月15日 下午10:24:07
 * @Version V0.1
 * @Desc 财物管理 action
 */
@Controller
@RequestMapping("/finances/*")
public class FinancesAction extends BaseAction {
    @Autowired
    IFinancesService iFinancesService = null;
    ////*****************Add By M ************////
    @Autowired
    IStockService iStockService = null;
    /**
     * 跳转到财物详情
     *
     * @return
     */
    @RequestMapping("listFinances.action")
    public String listFinances() {
        // 财物状态下拉列表
        List<?> financeStateList = InitSelect.getSelectList(FinanceStateEnum.class);
        HttpTool.setAttribute("financeStateList", financeStateList);
        // 财物类型下拉列表
        List<?> financeTypeList = InitSelect.getSelectList(FinanceTypeEnum.class);
        HttpTool.setAttribute("financeTypeList", financeTypeList);
        return "jsp/finances/listFinances";
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
     * 跳转到 添加财物页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("toEditFinances.action")
    public String toEditFinances() throws Exception {
        String financesId = HttpTool.getParameter("financesId");
        HttpTool.setAttribute("financesId", financesId);
        
        /////////////////////////////From 案件列表 情况 Start
        String caseId = HttpTool.getParameter("caseId");
        if(!StringUtils.isEmpty(caseId)) {
        	Cases finCase = new Cases();
        	finCase.setId(caseId);
        	String caseName = HttpTool.getParameter("caseName");
        	finCase.setCaseName(caseName);
        	String caseNum = HttpTool.getParameter("caseNum");
        	finCase.setCaseNum(caseNum);
        	Finances fin = new Finances();
        	fin.setCases(finCase);
        	HttpTool.setAttribute("finances", fin);
        	
        	HttpTool.setAttribute("fromSource", "listCases");
        }
        /////////////////////////////From 案件列表 End

        if (!StringUtils.isEmpty(financesId)) {
            Finances finances = (Finances) iFinancesService.findById(financesId);
            HttpTool.setAttribute("finances", finances);
        }
        // 财物类型下拉列表
        List<?> financeTypeList = InitSelect.getSelectList(FinanceTypeEnum.class);
        HttpTool.setAttribute("financeTypeList", financeTypeList);
        // 财物来源下拉列表
        List<?> financeSourceList = InitSelect.getSelectList(FinanceSourceEnum.class);
        HttpTool.setAttribute("financeSourceList", financeSourceList);
        // 来源单位下拉列表
        List<?> sourceOfficeList = InitSelect.getSelectList(SourceOfficeEnum.class);
        HttpTool.setAttribute("sourceOfficeList", sourceOfficeList);
        return "jsp/finances/editFinances";
    }

    @SuppressWarnings("rawtypes")
	@RequestMapping("saveFinances.action")
    @ResponseBody
    public Map saveFinances(Finances finance){
        String financesId = HttpTool.getParameter("id");
        HttpTool.setAttribute("financesId", financesId);

        // 财物关联的案件
        String caseId = HttpTool.getParameter("caseId");
        if(StringUtils.isNotBlank(caseId)){
            String caseName=HttpTool.getParameter("caseName");
            String caseNum=HttpTool.getParameter("caseNum");

            Cases relateCase = new Cases();
            relateCase.setId(caseId);
            relateCase.setCaseName(caseName);
            relateCase.setCaseNum(caseNum);
            finance.setCases(relateCase);
        }

        Finances saveFinance=new Finances();
        ////*****************Add By M start************////
        Stock saveStock = new Stock();
        ////****************Add By M End**************////
        // 更新时候的时间设置
        if(null!=finance&&StringUtils.isNotEmpty(finance.getSeizedTimeStart())){
            String []strTimeArr=finance.getSeizedTimeStart().split(",");
            if(null != strTimeArr && strTimeArr.length >0) {
                finance.setSeizedTimeStart(StringUtils.isNotEmpty(strTimeArr[0]) ? strTimeArr[0] : strTimeArr[1]);
            }else{
                String oldSeizedTimeStart = HttpTool.getParameter("oldSeizedTimeStart");
                if(StringUtils.isNotEmpty(oldSeizedTimeStart)) {
                    finance.setSeizedTimeStart(oldSeizedTimeStart.substring(0, 10));
                }
            }
        }
        // 更新 or 插入操作
        if(!StringUtils.isEmpty(financesId)) {
            saveFinance = (Finances) iFinancesService.findById(financesId);
            ReflectionUtil.copyPropertiesForHasValueIgnoreSerialVersionUID(saveFinance, finance);
        }else {
            BeanUtils.copyProperties(finance, saveFinance);
            saveFinance.setFinanceState(1); // 财物默认状态git
            saveFinance.setCreateTime(new Date());// 创建时间
            saveFinance.setCreator(UserUtil.getCurrentUser().getUserAccount()); // 当前登录人
            saveFinance.setIsDel(SysConstant.IS_NOT_DEL); //删除标示
            saveFinance.setId(null);
            ////*****************Add By M start************////
            ////   update stock table for operateLogs      ////
            ////*******************************************////
            saveStock.setId(null);
            saveStock.setFlag(SysConstant.STOCK_STATE_NONE);
            saveStock.setFinances(saveFinance);
            saveStock.setCreateTime(new Date());// 创建时间
            saveStock.setCreator(UserUtil.getCurrentUser().getUserAccount()); // 当前登录人
            saveStock.setIsDel(SysConstant.IS_NOT_DEL); //删除标示
            ////****************Add By M End**************////
        }
        saveFinance.setUpdateTime(new Date()); // 更新时间
        saveFinance.setUpdater(UserUtil.getCurrentUser().getUserAccount());// 当前登录人

        try{
            iFinancesService.save(saveFinance);
            ////*****************Add By M start************////
            if(StringUtils.isEmpty(financesId)) iStockService.save(saveStock);
            ////****************Add By M End**************////
            return TemplateUtil.toSuccessMap("操作成功！");
        } catch(Exception e) {
            e.printStackTrace();
            return TemplateUtil.toSuccessMap("操作失败！");
        }
    }


    /**
     * 跳转到财物详情页
     * @return
     * @throws Exception
     */
    @RequestMapping("toDetailFinances.action")
    public String toDetailFinances() throws Exception {
        String financesId = HttpTool.getParameter("financesId");

        if(StringUtils.isNotEmpty(financesId)) {
            Finances finances= (Finances) iFinancesService.findById(financesId);
            String storeOffice=	SysPropUtil.getSystemConstant(SysConstant.INIT_ROOT_ORG_NAME);
            finances.setStoreOffice(storeOffice);
            HttpTool.setAttribute("finances", finances);

            // 财物类型
            List<?> financeTypeList = InitSelect.getSelectList(FinanceTypeEnum.class);
            HttpTool.setAttribute("financeTypeList", financeTypeList);

            // 财物状态
            List<?> financeStateList = InitSelect.getSelectList(FinanceStateEnum.class);
            HttpTool.setAttribute("financeStateList", financeStateList);

            // 案件类型
            List<?> caseTypeList = InitSelect.getSelectList(CaseTypeEnum.class);
            HttpTool.setAttribute("caseTypeList", caseTypeList);

            // 财物来源
            List<?> financeSourceList = InitSelect.getSelectList(FinanceSourceEnum.class);
            HttpTool.setAttribute("financeSourceList", financeSourceList);

           // 来源单位
            List<?> sourceOfficeList = InitSelect.getSelectList(SourceOfficeEnum.class);
            HttpTool.setAttribute("sourceOfficeList", sourceOfficeList);
        }
        return "jsp/finances/financesDetail";
    }
    /**
     * 删除财物信息
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("delFinances.action")
    @ResponseBody
    public Map delFinances(){
        String financesId=HttpTool.getParameter("financesId");

        //财物需不需要关联状态  比如什么状态不能删除 或者 和案件有关联
        //boolean existEvidence =

        try{
            if(StringUtils.isNotEmpty(financesId)) {
                Finances finances= (Finances) iFinancesService.findById(financesId);
                finances.setIsDel(SysConstant.IS_DEL); //删除标示
                finances.setUpdater(UserUtil.getCurrentUser().getUserAccount());
                finances.setUpdateTime(new Date());
                iFinancesService.save(finances);
            }
            return TemplateUtil.toSuccessMap("操作成功！");
        }catch(Exception e){
            e.printStackTrace();
            return TemplateUtil.toSuccessMap("操作失败！");
        }
    }

    /**
     * 跳转到财物统计
     *
     * @return
     */
    @RequestMapping("toStatistics.action")
    public String toFinancesStatistics() {
        return "jsp/finances/financeStatistics";
    }

    /**
     * 显示财物统计信息
     *
     * @return
     */
    @RequestMapping("showStatistics.action")
    @ResponseBody
    public Map showFinancesStatistics() {
        int pageNum = HttpTool.getIntegerParameter("page");
        int size = HttpTool.getIntegerParameter("rows");
        page = new Page(pageNum, size);
        int total=0;
        Map searchMap = super.buildSearch(); // 组装查询条件
        // 查询数据
        List<Map<String,Object>> statistics = new ArrayList<>();
        try {
            statistics = iFinancesService.showStatistics(page, searchMap);
            if(statistics.size()>0){
                for(Map statistic:statistics)
                    total+=Integer.valueOf(statistic.get("total").toString());
            }
            Map<String,Object> financeTotal=new HashMap<>();
            financeTotal.put("typeName","合计");
            financeTotal.put("total",total);
            statistics.add(financeTotal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 默认 count 为1 否则前台处理会有 -49
        page.setTotalCount((long) statistics.size());
        return TemplateUtil.toDatagridMap(page, statistics);
    }

    /**
     * 显示财物统计信息
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("export.action")
    @ResponseBody
    public String exportFinancesStatistics(HttpServletRequest request) {
        String filter_and_updateTime_GE_T=HttpTool.getParameter("filter_and_updateTime_GE_T");
        String filter_and_updateTime_LE_T=HttpTool.getParameter("filter_and_updateTime_LE_T");

        page = new Page(1, 50);
        Map searchMap = super.buildSearch(); // 组装查询条件
        if(StringUtils.isNotBlank(filter_and_updateTime_GE_T))
            searchMap.put("filter_and_updateTime_GE_T",filter_and_updateTime_GE_T);
        if(StringUtils.isNotBlank(filter_and_updateTime_LE_T))
            searchMap.put("filter_and_updateTime_LE_T",filter_and_updateTime_LE_T);

        // 查询数据
        List<Map<String,Object>> statistics = new ArrayList<>();
        try {
            statistics = iFinancesService.showStatistics(page, searchMap);
            ExportUtil.exportStatistics(request,statistics);
            return JsonUtil.jsonSuccess("");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return JsonUtil.jsonFailure("");
    }
    @RequestMapping("upload.action")
    @ResponseBody
    public String uploadImage(HttpServletRequest request) { // todo 未完成
        String fileName = "";
        String logoPathDir = "/upload/images/";
        String logoRealPathDir = request.getSession().getServletContext()
                .getRealPath(logoPathDir);
        File logoSaveFile = new File(logoRealPathDir);
        if (!logoSaveFile.exists())
            logoSaveFile.mkdirs();
        MultipartHttpServletRequest  multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("imagePath");
        String file=HttpTool.getParameter("imgPath");
        System.out.print(file);
        return "";
    }
}


