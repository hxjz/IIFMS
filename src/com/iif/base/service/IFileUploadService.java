package com.iif.base.service;

import org.springframework.web.multipart.MultipartFile;

import com.iif.base.entity.FileInfo;
import com.iif.base.type.FileType;

public interface IFileUploadService {

	/**
	 * 
	 * @param file
	 *            上传的文件
	 * @param type
	 *            基于类型来生成目录
	 * @return
	 */
	FileInfo upload(MultipartFile file, FileType type);

}
