package com.cidic.design.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.ReviewDao;
import com.cidic.design.model.Review;
import com.cidic.design.service.ReviewService;


@Service
@Component
@Qualifier(value = "reviewServiceImpl")
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	@Qualifier(value = "reviewDaoImpl")
	private ReviewDao reviewDaoImpl;
	
	@Override
	public void createReview(Review review) {
		reviewDaoImpl.createReview(review);
	}

	@Override
	public void updateReview(Review review) {
		reviewDaoImpl.updateReview(review);
	}

	@Override
	public void deleteReview(int id) {
		reviewDaoImpl.deleteReview(id);
	}

	@Override
	public List<Review> getReviewListByProductionId(int productionId) {
		return reviewDaoImpl.getReviewListByProductionId(productionId);
	}

	@Override
	public List<Review> getReviewListByUserId(int userId, int offset, int limit) {
		return reviewDaoImpl.getReviewListByUserId(userId, offset, limit);
	}

	@Override
	public void createReviews(int userId, String productIds) {
		String[] productStrIds = productIds.split("\\,");
		for (String pId : productStrIds){
			Review review = new Review();
			review.setCreatetime(new Date());
			review.setCode(UUID.randomUUID().toString());
			review.setUserId(userId);
			review.setProductionId(Integer.parseInt(pId));
			reviewDaoImpl.createReview(review);
		}
		
	}

	@Override
	public void updateReviewScore(int id, String code, int score) {
		reviewDaoImpl.updateReviewScore(id, code, score);
	}

}
