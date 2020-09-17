package com.xq17.cloudtools.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xq17.cloudtools.bean.TempFile;
import com.xq17.cloudtools.utils.FtpUtil;
import com.xq17.cloudtools.utils.StringUtil;
import com.xq17.cloudtools.vo.ExceptionMsg;
import com.xq17.cloudtools.vo.ResultVo;

/**
 * 
 * @ClassName: FileController
 * @Description: 文件管理控制器
 * @author xq17
 * @date 2020年9月17日
 *
 */

@RestController
@RequestMapping("/file/api")
public class FileController {

	/**
	 * 
	    * @Title: uploadTempFile  
	    * @Description: 上传临时文件
	    * @param @param file
	    * @param @param url    参数  
	    * @return void    返回类型  
	    * @throws
	 */

	@PostMapping("/uploadTempFile")
	public ResultVo uploadTempFile(@RequestParam(value = "upload") MultipartFile file, String url) {
		String fileName = file.getOriginalFilename().replaceAll(" ", "");
		if (!StringUtil.isOkName(fileName)) {
			return new ResultVo(301, "文件名不符合规范,上传失败!", "");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = format.format(new Date());
		String path = "temp/" + dateStr + "/" + UUID.randomUUID();
		try {
			if (FtpUtil.uploadFile("/" + path, fileName, file.getInputStream())) {
				String size = String.valueOf(file.getSize());
				TempFile tf = new TempFile(size, new Date(), path, fileName);

			}
			;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return new ResultVo(ExceptionMsg.FAILED);

	}
}
