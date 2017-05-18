package com.iif.base.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hxjz.common.core.web.BaseAction;
import com.iif.base.entity.FileInfo;
import com.iif.base.service.IFileUploadService;
import com.iif.base.type.FileType;

/**
 * 上传附件公共API
 * 
 * @author LiuM
 * 
 */
@Controller
@RequestMapping(value = "file")
public class FileUploadAction extends BaseAction {

	@Autowired
	private IFileUploadService fileUploadService;

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/upload.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(
			@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "type", required = true, defaultValue = "file") String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			FileInfo fileInfo = fileUploadService.upload(file, FileType.valueOf(type.toUpperCase()));
			map.put("file", fileInfo);
		} catch (Exception e) {
		}
		return map;
	}

}
