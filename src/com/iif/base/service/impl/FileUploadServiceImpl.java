package com.iif.base.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hxjz.common.utils.DateUtil;
import com.hxjz.common.utils.SystemConstant;
import com.iif.base.entity.FileInfo;
import com.iif.base.service.IFileUploadService;
import com.iif.base.type.FileType;
import com.iif.common.util.SysPropUtil;

/**
 * 
 * @author LiuM
 * 
 */
@Service
public class FileUploadServiceImpl implements IFileUploadService {

	@Override
	public FileInfo upload(MultipartFile file, FileType type) {

		// 基本路径
		StringBuilder basePath = new StringBuilder();
		basePath.append(SystemConstant.getSystemConstant("pic_path"));
		basePath.append(DateUtil.DateToStr(new Date(), "yyyyMMdd"));
		basePath.append("/");
		basePath.append(type.toString().toLowerCase());

		// 新的文件名称
		StringBuilder newname = new StringBuilder();
		String filename = file.getOriginalFilename();
		int index = filename.lastIndexOf(".");
		if (index != -1) {
			newname.append(System.nanoTime());
			newname.append(filename.substring(index));
		}

		// 文件路径
		StringBuilder relative = new StringBuilder();
		relative.append(basePath);
		relative.append("/");
		relative.append(newname);

		// 上传文件
		File outFile = new File(relative.toString());
		FileInfo fileInfo = new FileInfo();
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			if (!outFile.getParentFile().exists())
				outFile.getParentFile().mkdirs();
			is = file.getInputStream();
			fos = new FileOutputStream(outFile);
			IOUtils.copy(is, fos);

			//
			fileInfo.setSize(file.getSize());
			if (FileType.IMAGE == type) {
				StringBuilder url = new StringBuilder();
				url.append(SysPropUtil.getSystemConstant("pic_url"));
				url.append(relative);
				fileInfo.setUrl(url.toString());
			}
			fileInfo.setRelativePath(relative.toString());
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
			}

			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
			}
		}
		return fileInfo;
	}

}
