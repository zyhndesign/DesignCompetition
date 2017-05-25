package com.cidic.design.service;

import java.util.List;

import com.cidic.design.model.Production;
import com.cidic.design.model.Review;

public interface ReviewService {

	public void createReview(Review review);
	
	public void updateReview(Review review);
	
	public void deleteReview(int id);
	
	public List<Review> getReviewListByProductionId(int productionId);
	
	public List<Production> getReviewListByUserId(int userId, int scoreSign, int offset, int limit);
	
	public void createReviews(int userId, String productIds,int round);
	
	public void updateReviewScore(int id, int score);
	
	public List<String> getSendEmailByRound(int round);
	
	public void sendReviewEmail(int round);
	
	public void bindProductAndRound(int productionId, int round);
}
