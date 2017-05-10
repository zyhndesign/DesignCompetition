package com.cidic.design.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.model.News;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.NewsService;

@Controller
@RequestMapping(value="/news")
public class NewsController  extends DcController{

	@Autowired
	@Qualifier(value = "newsServiceImpl")
	private NewsService newsServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/createNews", method = RequestMethod.POST)
	public ResultModel createNews(HttpServletRequest request, HttpServletResponse response,@RequestParam News news){
		resultModel = new ResultModel();
		try{
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteNews", method = RequestMethod.POST)
	public ResultModel deleteNews(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateNews", method = RequestMethod.POST)
	public ResultModel updateNews(HttpServletRequest request, HttpServletResponse response,@RequestParam News news){
		resultModel = new ResultModel();
		try{
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/findNewsById", method = RequestMethod.POST)
	public ResultModel findNewsById(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		
		resultModel = new ResultModel();
		try{
			Optional<News> news = newsServiceImpl.findNewsById(id);
			resultModel.setObject(news);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ResultModel findNewsByPage(HttpServletRequest request, HttpServletResponse response,@RequestParam int offset, @RequestParam int limit){
		List<News>
		resultModel = new ResultModel();
		try{
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
}
