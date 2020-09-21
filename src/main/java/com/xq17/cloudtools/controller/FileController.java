package com.xq17.cloudtools.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xq17.cloudtools.bean.TempFile;
import com.xq17.cloudtools.service.TempFileService;
import com.xq17.cloudtools.utils.FtpUtil;
import com.xq17.cloudtools.utils.HttpUtil;
import com.xq17.cloudtools.utils.QRCodeUtil;
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
@RequestMapping("/file")
public class FileController {


	@Autowired
	TempFileService tfService;

	/**
	 * 
	 * @Title: getToken @Description: 获取114.mx的token @param @return 参数 @return
	 * ResultVo 返回类型 @throws
	 */
	@GetMapping("/api/getToken")
	public ResultVo getToken() {
		String apiURL = "http://114.mx";
		String token = "";
		Map<String, Object> map = new HashMap<String, Object>();
 		try {
			Scanner s = new Scanner(new URL(apiURL).openStream(), "utf-8");
			String rspHtml = s.useDelimiter("\\A").next();
			// System.out.println(rspHtml);
			Pattern pattern = Pattern.compile("name=\"token\" value=\"(.*?)\"");
			Matcher m = pattern.matcher(rspHtml);
			if (m.find()) {
				token = m.group(1);
				map.put("token", token);
				return new ResultVo(200, "成功获取到Token", map);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResultVo(301,"获取接口Token出错!", "");
	}

	/**
	 * 
	    * @Title: shareFile  
	    * @Description: 文件分享控制器
	    * @param @param t uuid的值
	    * @param @param f field
	    * @param @param p file size
	    * @param @param flag 区分临时文件和正常文件上传
	    * @param @return    参数  
	    * @return ResultVo    返回类型  
	    * @throws
	 */
	@GetMapping("/share")
	public ResultVo shareFile(String t, Integer f, String p, Integer flag, HttpServletRequest request,
			HttpServletResponse response) {
        String fileNameTemp = "";
        String remotePath = "";
        String fileName = "";
		if (StringUtil.checkStringNull(t, p) || f == null || flag == null) {
			return new ResultVo(301, "参数错误", "");
		}

		// 上传临时文件
		if (flag == 1) {

		} else if (flag == 2) {
			TempFile tempFile = tfService.findById(f);
			if (tempFile == null) {
				return new ResultVo(301, "文件不存在", "");
			}
			String size = tempFile.getSize();
			if (!size.equals(p)) {
				return new ResultVo(301, "参数错误", "");
			}
			remotePath = tempFile.getFilePath();
			fileName = tempFile.getFileName();
		} else {
			return new ResultVo(301, "参数错误", "");
		}
		// 解决中文乱码
		fileNameTemp = fileName;
		try {
			boolean isMSIE = HttpUtil.isMSBrowser(request);
			if (isMSIE) {
				//浏览器解决
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				// 万能解决
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"), "ISO-8859-1");
			}
			//
			try {
				// 读取输出流
				OutputStream os = new BufferedOutputStream(response.getOutputStream());
				response.setCharacterEncoding("utf-8");
				// 设置返回类型为下载类型
				// response.setContentType("multipart/form-data");
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;fileName=" + fileNameTemp);
				if (FtpUtil.downloadFile("/" + remotePath, fileName, os)) {
					// 输出文件流
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@GetMapping("/api/showTempFile")
	public ResultVo showTempFile(HttpSession session, HttpServletRequest request) {
		String url;
		String imgPath;
		try {
			url = session.getAttribute("url").toString();
			imgPath = session.getAttribute("imgPath").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResultVo(301, "你还没有上传临时文件!", "");
		}
		String path = request.getServletContext().getRealPath("/");
		File fileCode = new File(path, imgPath);
		if (!fileCode.exists()) {
			return new ResultVo(303, "当前二维码已经失效!", "");
		}
		if (!StringUtil.checkStringNull(url, imgPath)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("url", url);
			map.put("imgPath", imgPath);
			return new ResultVo(200, "成功获取文件信息!", map);
		} else {
			return new ResultVo(301, "你还没有上传临时文件!", "");
		}
	}

	/**
	 * 
	    * @Title: uploadTempFile  
	    * @Description: 上传临时文件
	    * @param @param file
	    * @param @param url    参数  
	    * @return void    返回类型  
	    * @throws
	 */
	// TODO: uuid最好作为判断的条件,防止出现遍历爆破的情况
	@PostMapping("/api/uploadTempFile")
	public ResultVo uploadTempFile(@RequestParam(value = "file") MultipartFile file, String url,
			HttpServletRequest request, HttpSession session) {
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
				String sessionId = request.getSession().getId();
				TempFile tf = new TempFile(size, new Date(), path, fileName, sessionId);
				try {
					if (tfService.insert(tf) > 0) {
						try {
							TempFile tf_ = tfService.findLastBySessionId(sessionId);
							System.out.println(tf_);
							String id = UUID.randomUUID().toString();
							// 获取tomcat中的绝对路径
							String p = request.getServletContext().getRealPath("/");
							String savePath = request.getServletContext().getInitParameter("uploadPath");
							if (StringUtil.checkStringNull(savePath)) {
								savePath = "user_code";
							}
							Long t = tf.getUploadTime().getTime();
							url += "/file/share?t=" + UUID.randomUUID().toString().substring(0, 10) + "&f="
									+ tf_.getFileId();
							url += "&p=" + size + "&flag=2";
							// System.out.println(url);
							File targetFile = new File(p, savePath);
							if (!targetFile.exists()) {
								targetFile.mkdirs();
							}
							p = p + "/" + savePath;
							System.out.println(p);
							File codeFile = new File(p, id + ".jpg");
							File codeFileParent = codeFile.getParentFile();
							if (!codeFileParent.exists()) {
								codeFileParent.mkdirs();
							}
							if (!codeFile.exists()) {
								// 文件不存在,开始生成二维码,并且保存文件
								OutputStream os = new FileOutputStream(codeFile);
								QRCodeUtil.encode(url, "/logo/logo.jpg", os, true);
								os.close();
							}

							Map<String, Object> map = new HashMap<String, Object>();
							map.put("url", url);
							map.put("imgPath", "user_code/" + id + ".jpg");

							// 写入到session
							session.setAttribute("url", url);
							session.setAttribute("imgPath", "user_code/" + id + ".jpg");

							// 这里利用计时器来执行删除操作

							return new ResultVo(200, "上传成功,扫码/访问链接下载!", map);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return new ResultVo(301, "二维码生成失败!", "");
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ResultVo(301, "数据库插入失败!", "");
				}
			}
			;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResultVo(301, "FTP操作失败!", "");
		}
		return new ResultVo(ExceptionMsg.FAILED);
	}


}
