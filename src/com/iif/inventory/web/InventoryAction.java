package com.iif.inventory.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.iif.cases.entity.Cases;
import com.iif.common.util.TemplateUtil;
import com.iif.finances.entity.Finances;
import com.iif.finances.entity.FinancesImages;
import com.iif.finances.service.IFinancesService;
import com.iif.inventory.entity.FinancesCopy;
import com.iif.inventory.service.IFinancesCopyService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author M
 * @Date 2017年7月3日 下午10:24:07
 * @Version V0.1
 * @Desc 异常财物 action
 */
@Controller
@RequestMapping("/inventory/*")
public class InventoryAction extends BaseAction {
    @Autowired
    IFinancesService iFinancesService = null;
    @Autowired
    IFinancesCopyService iFinancesCopyService = null;
    /**
     * 跳转到财物详情
     *
     * @return
     */
    @RequestMapping("listInventory.action")
    public String listInventory() {
        return "jsp/inventory/listInventory";
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping("showInventory.action")
    @ResponseBody
    public Map showInventory() {
        int pageNum = HttpTool.getIntegerParameter("page");
        int pageSize = HttpTool.getIntegerParameter("rows");
        page = new Page(pageNum, pageSize);
        Map searchMap = super.buildSearch(); // 组装查询条件

        List<FinancesCopy> financeCopyList = iFinancesCopyService.findByPage(page, searchMap);

        return TemplateUtil.toDatagridMap(page, financeCopyList);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping("doInventory.action")
    @ResponseBody
    public Map doInventory(HttpServletRequest request) throws Exception{
    	//查询财物列表所有的数据
        List<Finances> financeList = iFinancesService.findAll(); 
        //将文件上传至服务器
    	String uploadedFile = uploadFile2Server(request);
    	// 上传文件后处理    	
    	if(!"".equals(uploadedFile)){	
        	Map<String, String> readTxtList = readTxtFile(uploadedFile);
        	//用于存放比对后的数据。
            List<Finances> financeListAfter = null;
            Iterator iterator = financeList.iterator();
            //finance迭代器
            Finances itFinance = null;
            //epc编码
            String code = "";
            while(iterator.hasNext()){            	
            	itFinance = (Finances)iterator.next();
            	code = itFinance.getFinanceCode();
            	if(readTxtList.containsKey(code)){
            		//若数据库该条记录的epc在txt中存在
            		//则同时删除readTxtList和financeList中的相应记录
            		readTxtList.remove(code);
            		iterator.remove();
            	}
            }
            try{
	            //清空FinancesCopy表中的数据
	            iFinancesCopyService.deleteAll();
	            //将异常财物数据存入数据库
	            Iterator iteratorAfter = financeList.iterator();
	            FinancesCopy itFinanceCopy = new FinancesCopy();
	            while(iteratorAfter.hasNext()){
	            	
	            	itFinance = (Finances)iteratorAfter.next();
	            	//将itFinance转成FinancesCopy类型
	            	Finances2FinancesCopy(itFinance, itFinanceCopy);
                    iFinancesCopyService.save(itFinanceCopy);
	            }
            } catch(Exception e) {
                e.printStackTrace();
            }  
    	} else {
    		//上传文件出错
    		return null;
    	}
    	
        return TemplateUtil.toDatagridMap(page, financeList);
    }    
    
    
    public String uploadFile2Server(HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
        String fileName = "";
        /** 构建文件保存的目录 **/
        String logoPathDir = "/upload/";// + dateformat.format(new Date());
        /** 得到文件保存目录的真实路径 **/
        String logoRealPathDir = request.getSession().getServletContext()
                .getRealPath(logoPathDir);
        /** 根据真实路径创建目录 **/
        File logoSaveFile = new File(logoRealPathDir);
        if (!logoSaveFile.exists())
            logoSaveFile.mkdirs();
        /** 页面控件的文件流 **/
        MultipartFile multipartFile = multipartRequest.getFile("uploadFile");
        if (multipartFile != null) {
            /** 获取文件的后缀 **/
            String suffix = multipartFile.getOriginalFilename().substring(
                    multipartFile.getOriginalFilename().lastIndexOf("."));
            /** 使用UUID生成文件名称 **/
            String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
            /** 拼成完整的文件保存路径加文件 **/
            fileName = logoRealPathDir + File.separator + logImageName;
            File file = new File(fileName);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /** 打印出上传到服务器的文件的绝对路径 **/
        System.out.println("****************"+fileName+"**************");
        return fileName;      
    }    
    

    public static Map<String, String> readTxtFile(String filePath){
    	Map<String, String> txtMap = new HashMap<String, String>();
        try {
        	String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){//判断文件是否存在
            	int i = 0;
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    System.out.println(lineTxt);
                    txtMap.put(lineTxt,"");
//                    txtMap.contains(lineTxt);
                }
                read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
            return null;
        }
        return txtMap;
    }

    public void Finances2FinancesCopy(Finances c1, FinancesCopy c2){
    	c2.setId(c1.getId()); // 财物编码 Key
    	c2.setCases(c1.getCases()); // 相关案件
    	c2.setFinanceName(c1.getFinanceName()); // 财物名称
    	c2.setFinanceType(c1.getFinanceType()); // 财物类型
    	c2.setFinanceNum(c1.getFinanceNum()); // 财物编号
    	c2.setFinanceState(c1.getFinanceState()); // 财物状态
    	c2.setFinanceSource(c1.getFinanceSource()); // 财物来源
    	c2.setSourceOffice(c1.getSourceOffice()); // 财物来源单位
    	c2.setStoreOffice(c1.getStoreOffice());  // 财物保管单位
    	c2.setSeizedMan(c1.getSeizedMan()); // 查获人
    	c2.setSeizedTimeStart(c1.getSeizedTimeStart()); // 查获时间段（起）
    	c2.setSeizedTimeEnd(c1.getSeizedTimeEnd()); // 查获时间段（止）
        c2.setFinanceDesc(c1.getFinanceDesc()); // 财物说明
        c2.setFinanceMemo(c1.getFinanceMemo()); // 财物备注
//        transient private List<FinancesImages> FinanceImages; // 财物照片
        c2.setImageSign(c1.getImageSign()); // 是否有财物照片
        c2.setStoreLocation(c1.getStoreLocation()); // 存放位置
        c2.setFinanceCode(c1.getFinanceCode()); // 财物识别码
        c2.setDigitalCode(c1.getDigitalCode()); // 电子识别码
        c2.setEntryTime(c1.getEntryTime()); // 登记时间
        c2.setEntryMan(c1.getEntryMan()); // 登记人
        c2.setInstockTime(c1.getInstockTime()); // 入库时间
        c2.setInstockMan(c1.getInstockMan()); // 入库人
        c2.setOutstockTime(c1.getOutstockTime()); // 出库时间
        c2.setOutstockMan(c1.getOutstockMan()); // 出库人
    }
}


