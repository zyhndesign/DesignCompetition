package com.cidic.design.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cidic.design.exception.DCException;
import com.cidic.design.model.ResultModel;
import com.cidic.design.util.FileUtil;

@Controller
@RequestMapping(value="/file")
public class FileDisposeController {

	private static final String COMPRESS_FILE_DIR = "/WEB-INF/attachFile";
	private static final String NEWS_IMAGE_FILE_DIR = "/WEB-INF/newsImageFile";
	private static final String PRODUCTION_FILE_DIR = "/WEB-INF/productionFile";

	private ResultModel resultModel = null;

	@ExceptionHandler(DCException.class)
	public @ResponseBody ResultModel handleCustomException(DCException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		return resultModel;
	}

	/**
	 * 上传文件的方法
	 * @param file
	 * @param fileType  1:作品压缩文件附件， 2:作品图， 3，新闻文章图片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
	public @ResponseBody ResultModel uploadZipMultiFile(@RequestParam MultipartFile file, @RequestParam int fileType, HttpServletRequest request,
			HttpServletResponse response) {
		resultModel = new ResultModel();

		if (file.isEmpty()) {
			throw new DCException(500, "文件未上传");
		} else {

			String path = "";
			if (fileType == 1){
				path = request.getSession().getServletContext().getRealPath(COMPRESS_FILE_DIR);
			}
			else if (fileType == 2){
				path = request.getSession().getServletContext().getRealPath(NEWS_IMAGE_FILE_DIR);
			}
			else if (fileType == 3){
				path = request.getSession().getServletContext().getRealPath(PRODUCTION_FILE_DIR);
			}
			

			String fileName = FileUtil.makeFileName() + "."
					+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String realPath = FileUtil.makePath(fileName, path);
			
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, fileName));
				resultModel.setObject(realPath.replaceAll(path, ""));
				resultModel.setResultCode(200);
				return resultModel;
			} catch (IOException e) {
				throw new DCException(500, "转存文件出错");
			}

		}

	}

	/**
	 * 压缩文件下载
	 * @param request
	 * @param response
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String filePath) throws IOException {
		String path = request.getSession().getServletContext().getRealPath(COMPRESS_FILE_DIR);

		File file = new File(path + filePath);

		HttpHeaders headers = new HttpHeaders();
		String fileName = new String(filePath.substring(filePath.lastIndexOf(".")).getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

	/**
	 * 图片下载
	 * @param request
	 * @param response
	 * @param imgPath 图片路径
	 * @param fileType 2:作品图， 3，新闻文章图片
	 * @return
	 * @throws IOException
	 */
	@RequiresRoles("admin")
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String imgPath, @RequestParam int fileType) throws IOException {
		String path = "";
		
		if (fileType == 2){
			path = request.getSession().getServletContext().getRealPath(PRODUCTION_FILE_DIR);
		}
		else if (fileType == 3){
			path = request.getSession().getServletContext().getRealPath(NEWS_IMAGE_FILE_DIR);
		} 
		System.out.println(imgPath);
		File file = new File(path + imgPath);

		HttpHeaders headers = new HttpHeaders();
		String fileName = new String(imgPath.substring(imgPath.lastIndexOf(".")).getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
}
