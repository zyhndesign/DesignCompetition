package com.cidic.design.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.model.News;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.NewsService;

/**
 * 大赛新闻信息处理
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/news")
public class NewsController  extends DcController{

	@Autowired
	@Qualifier(value = "newsServiceImpl")
	private NewsService newsServiceImpl;
	
	@RequestMapping(value = "/news")
	public String news(HttpServletRequest request, Model model) {
		return "frontend/news";
	}

	@RequestMapping(value = "/newsMgr")
    public String newsMgr(HttpServletRequest request, Model model) {
        return "backend/newsMgr";
    }
	
	@RequestMapping(value = "/newsCOU")
    public String newsCOU(HttpServletRequest request, Model model) {
        return "backend/newsCOU";
    }
	
	@RequestMapping(value = "/newsCOU/{id}",method = RequestMethod.GET)
    public ModelAndView updateCOU(HttpServletRequest request, @PathVariable int id) {
		News news = null;
		if (id > 0){
			news = newsServiceImpl.findNewsById(id).get();
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName("backend/newsCOU");
		model.addObject(news);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(value="/createNews", method = RequestMethod.POST)
	public ResultModel createNews(HttpServletRequest request, HttpServletResponse response,@RequestBody News news){
		resultModel = new ResultModel();
		try{
			news.setPublishTime(new Date());
			newsServiceImpl.createNews(news);
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
			newsServiceImpl.deleteNews(id);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateNews", method = RequestMethod.POST)
	public ResultModel updateNews(HttpServletRequest request, HttpServletResponse response,@RequestBody News news){
		resultModel = new ResultModel();
		try{
			news.setPublishTime(new Date());
			newsServiceImpl.updateNews(news);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/findNewsById/{id}", method = RequestMethod.GET)
	public ResultModel findNewsById(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		
		resultModel = new ResultModel();
		try{
			News news = newsServiceImpl.findNewsById(id).get();
			
			resultModel.setObject(news);
			
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/findNewsByPage", method = RequestMethod.POST)
	public ResultModel findNewsByPage(HttpServletRequest request, HttpServletResponse response,@RequestParam int offset, @RequestParam int limit){

		resultModel = new ResultModel();
		try{
			List<News> list = newsServiceImpl.findNewsByPage(offset, limit);
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
}
