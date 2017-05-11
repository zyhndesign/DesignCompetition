package com.cidic.design.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.ReviewDao;
import com.cidic.design.model.Review;


@Repository
@Component
@Qualifier(value = "reviewDaoImpl")
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createReview(Review review) {
		sessionFactory.getCurrentSession().save(review);
	}

	@Override
	public void updateReview(Review review) {
		sessionFactory.getCurrentSession().update(review);
	}

	@Override
	public void deleteReview(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Review r  where r.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<Review> getReviewListByProductionId(int productionId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Review where productionId = ? order by createtime desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, productionId);
		return query.list();
	}

	@Override
	public List<Review> getReviewListByUserId(int userId, int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Review where userId = ? order by createtime desc";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

}
