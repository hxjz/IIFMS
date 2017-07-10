package com.iif.finances.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hxjz.common.core.web.BaseAction;
import com.hxjz.common.utils.HttpTool;
import com.hxjz.common.utils.Page;
import com.iif.common.enums.FinanceStateEnum;
import com.iif.common.enums.FinanceTypeEnum;
import com.iif.common.util.InitSelect;
import com.iif.common.util.TemplateUtil;
import com.iif.common.util.UploadFileUtil;
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
            if(file.isFile() && file.exists()){ //判断文件是否存在
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

}


