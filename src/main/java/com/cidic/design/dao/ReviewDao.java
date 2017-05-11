package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.Review;

public interface ReviewDao {

	public void createReview(Review review);
	
	public void updateReview(Review review);
	
	public void deleteReview(int id);
	
	public List<Review> getReviewListByProductionId(int productionId);
	
	public List<Review> getReviewListByUserId(int userId, int offset, int limit);
}
