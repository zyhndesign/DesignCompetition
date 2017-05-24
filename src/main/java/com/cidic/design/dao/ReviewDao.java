package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.Production;
import com.cidic.design.model.Review;
import com.cidic.design.model.ScoreBean;

public interface ReviewDao {

	public void createReview(Review review);
	
	public void updateReview(Review review);
	
	public void deleteReview(int id);
	
	public List<Review> getReviewListByProductionId(int productionId);
	
	public List<Production> getReviewListByUserId(int userId, int scoreSign, int offset, int limit);
	
	/**
	 * 根据投稿ID，获取其打分情况
	 * @param productionId
	 * @return
	 */
	public ScoreBean getScoreByProductionId(int productionId);
	
	/**
	 * 获取所有作品的打分情况
	 * @return
	 */
	public List<ScoreBean> getAllReviewResult();
	
	public void updateReviewScore(int id, int score);
	
	public List<String> getSendEmailByRound(int round);
	
}
