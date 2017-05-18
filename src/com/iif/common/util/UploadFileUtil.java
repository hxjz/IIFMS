package com.iif.common.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hxjz.common.utils.DateUtil;
import com.hxjz.common.utils.FileUtil;

public class UploadFileUtil {

	/**
	 * 上传图片到服务器, 返回上传后图片路径
	 * @param uploadFile
	 * @param uploadPath
	 * @return
	 */
	public static String uploadFile(MultipartFile uploadFile, String uploadPath, HttpServletRequest request){
		// 获取上传文件名字
		String fileName = uploadFile.getOriginalFilename();
		fileName = System.currentTimeMillis()+"-"+fileName;
		
		//由天分类存储
	    String dayPath = DateUtil.DateToStr(new Date(), "yyyyMMdd")+"/";
		
		String filePath = uploadPath + dayPath;
		try {
			// 文件上传到服务器
			FileUtil.createNewFile(uploadFile.getInputStream(),fileName, filePath);
			return filePath+fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
