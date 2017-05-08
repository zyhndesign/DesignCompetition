package com.cidic.design.dao;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
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
	
	@RequestMapping(value="/uploadMultiFile", method=RequestMethod.POST)  
    public @ResponseBody ResultModel uploadMultiFile(@RequestParam MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException{  
		ResultModel resultModel = new ResultModel();
        
		for(MultipartFile myfile : myfiles){  
            if(myfile.isEmpty()){  
            	throw new DCException(500, "文件未上传");
            }else{  
                
                String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
                
                String fileName = FileUtil.makeFileName() + "."+ myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf("."));
    			String realPath = FileUtil.makePath(fileName, path);
                System.out.println(realPath);
                System.out.println(realPath.replaceAll(path, ""));
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
                FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, fileName));  
            }  
        }  
        return resultModel;  
    }
    
    @RequestMapping(value = "/updateProductionImage")
	public String updateProductionImage(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request, ModelMap model) throws Exception {
		if (!file.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath(PRODUCTION_FILE_DIR);
			String fileName = FileUtil.makeFileName() + "."+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String realSavePath = FileUtil.makePath(fileName, path);

			File targetFile = new File(realSavePath, fileName);
			//目录不存在，则创建目录
			if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
			//保存  
	        try {  
	            file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName); 
			return "success";
		} else {
			return "fail";
		}
	}

    @RequestMapping(value="/downloadFile", method = RequestMethod.GET)    
    public ResponseEntity<byte[]> download(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String filePath) throws IOException {    
    	String path = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/upload/");
    	
		File file = new File(path + "架构演变图.jpg"); 
		
        HttpHeaders headers = new HttpHeaders();    
        String fileName=new String("新建 Microsoft Word 文档.rar".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);    
    }  
    
    @RequestMapping(value="/image/{imgPath}", method = RequestMethod.GET)    
    public ResponseEntity<byte[]> getImage(HttpServletRequest request,HttpServletResponse response,@PathVariable String imgPath) throws IOException {    
    	String path = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/upload/");
    	
		File file = new File(path + "架构演变图.jpg"); 
		
        HttpHeaders headers = new HttpHeaders();    
        String fileName=new String("新建 Microsoft Word 文档.rar".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);    
    }
}
