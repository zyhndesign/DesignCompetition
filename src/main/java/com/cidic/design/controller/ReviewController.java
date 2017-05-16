package com.cidic.design.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.Review;
import com.cidic.design.service.ReviewService;

/**
 * 作品评分记录
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/review")
public class ReviewController extends DcController {

	@Autowired
	@Qualifier(value = "reviewServiceImpl")
	private ReviewService reviewServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/createReview", method = RequestMethod.POST)
	public ResultModel createReview(HttpServletRequest request, HttpServletResponse response,@RequestBody Review review){
		resultModel = new ResultModel();
		try{
			review.setCreatetime(new Date());
			reviewServiceImpl.createReview(review);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateReview", method = RequestMethod.POST)
	public ResultModel updateReview(HttpServletRequest request, HttpServletResponse response,@RequestBody Review review){
		resultModel = new ResultModel();
		try{
			review.setCreatetime(new Date());
			reviewServiceImpl.updateReview(review);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteReview/{id}", method = RequestMethod.GET)
	public ResultModel deleteReview(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			reviewServiceImpl.deleteReview(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getReviewListByProductionId/{productionId}", method = RequestMethod.GET)
	public ResultModel getReviewListByProductionId(HttpServletRequest request, HttpServletResponse response,@PathVariable int productionId){
		resultModel = new ResultModel();
		try{
			List<Review> list = reviewServiceImpl.getReviewListByProductionId(productionId);
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "读取数据出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getReviewListByUserId", method = RequestMethod.POST)
	public ResultModel getReviewListByUserId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId,@RequestParam  int offset,@RequestParam  int limit){
		resultModel = new ResultModel();
		try{
			List<Review> list = reviewServiceImpl.getReviewListByUserId(userId, offset, limit);
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "读取数据出错");
		}
	}
}
