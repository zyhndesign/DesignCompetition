package com.cidic.design.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
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
import com.cidic.design.exception.ServerException;
import com.cidic.design.model.News;
import com.cidic.design.model.Production;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.Review;
import com.cidic.design.service.ProductionService;
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
	
	@Autowired
	@Qualifier(value = "productionServiceImpl")
	private ProductionService productionServiceImpl;
	
	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/sendEmail")
	public String sendEmail(HttpServletRequest request, Model model) {
		return "backend/sendEmail";
	}
	
	@RequestMapping(value = "/judgeIndex/{round}")
	public ModelAndView judgeIndex(HttpServletRequest request, Model model, @PathVariable int round) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("frontend/judge/index");
		modelView.addObject(round);
		return modelView;
	}
	
	@RequestMapping(value = "/score/{id}/{round}")
	public ModelAndView score(HttpServletRequest request, Model model, @PathVariable int id,@PathVariable int round) {
		try {
			Optional<Production> production = productionServiceImpl.getProductionDetailById(id);
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("frontend/judge/score");
			modelView.addObject(production.get());
			return modelView;
		} catch (Exception e) {
			throw new ServerException(400, "服务器内部出错了");
		}

	}
	
	/**
	 * 针对评委创建多个多个评分的作品任务，
	 * @param request
	 * @param response
	 * @param userId
	 * @param productIds  = example: “1,3,5,6,7”
	 * @return
	 */
	@RequiresRoles(value ={"评委"})
	@ResponseBody
	@RequestMapping(value="/createReviews", method = RequestMethod.POST)
	public ResultModel createReviews(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam String productIds, @RequestParam  int round){
		resultModel = new ResultModel();
		try{
			reviewServiceImpl.createReviews(userId, productIds,round);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/bindProductAndRound", method = RequestMethod.POST)
	public ResultModel bindProductAndRound(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int productId, @RequestParam  int round){
		resultModel = new ResultModel();
		try{
			reviewServiceImpl.bindProductAndRound(productId,round);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@RequiresRoles(value ={"评委"})
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
	
	@RequiresRoles(value ={"评委"})
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
	
	@RequiresRoles(value ={"评委"})
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
	
	@RequiresRoles(value ={"评委"})
	@ResponseBody
	@RequestMapping(value="/getReviewListByUserId", method = RequestMethod.POST)
	public ResultModel getReviewListByUserId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int scoreSign,  @RequestParam int round, @RequestParam  int offset,@RequestParam  int limit){
		resultModel = new ResultModel();
		try{
			List<Production> list = reviewServiceImpl.getReviewListByUserId(userId,scoreSign, round, offset, limit);
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "读取数据出错");
		}
	}
	
	@RequiresRoles(value ={"评委"})
	@ResponseBody
	@RequestMapping(value="/updateReviewScore", method = RequestMethod.POST)
	public ResultModel updateReviewScore(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int productionId, @RequestParam int userId, @RequestParam int round, @RequestParam int score){
		resultModel = new ResultModel();
		try{
			reviewServiceImpl.updateReviewScoreByCondition(productionId, userId, round, score);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "提交数据出错");
		}
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/sendReviewEmail", method = RequestMethod.POST)
	public ResultModel sendReviewEmail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int round){
		resultModel = new ResultModel();
		try{
			reviewServiceImpl.sendReviewEmail(round);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "提交数据出错");
		}
	}
}
