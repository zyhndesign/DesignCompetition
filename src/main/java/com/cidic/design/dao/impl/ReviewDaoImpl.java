package com.cidic.design.dao.impl;

import java.util.ArrayList;
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
import com.cidic.design.model.ScoreBean;

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

	@Override
	public ScoreBean getScoreByProductionId(int productionId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select productionId, sum(score), count(*) from Review where productionId = ? group by productionId ";
		Query query = session.createQuery(hql);
		query.setParameter(0, productionId);
		
        @SuppressWarnings("unchecked")
		List list = query.list();
        
        ScoreBean scoreBean = null;
        for(int i=0; i<list.size(); i++)
        {
        	scoreBean = new ScoreBean();
            Object []o = (Object[])list.get(i);
            int pId = (Integer)o[0];
            int scoreSum = (Integer)o[1];
            int gradeSum = (Integer)o[2];
            float averageScore = (float)scoreSum/gradeSum;
            
            scoreBean.setProductionId(pId);
            scoreBean.setGradeSum(gradeSum);
            scoreBean.setScoreSum(scoreSum);
            scoreBean.setAverageScore(averageScore);
            
        }
        return scoreBean;
	}

	@Override
	public List<ScoreBean> getAllReviewResult() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select productionId, sum(score), count(*) from Review group by productionId ";
		Query query = session.createQuery(hql);
       
        @SuppressWarnings("unchecked")
		List list = query.list();
        
        List<ScoreBean> scoreList = new ArrayList<ScoreBean>(10);
        ScoreBean scoreBean = null;
        for(int i=0; i<list.size(); i++)
        {
        	scoreBean = new ScoreBean();
            Object []o = (Object[])list.get(i);
            int productionId = (Integer)o[0];
            int scoreSum = ((Number)o[1]).intValue();
            int gradeSum = ((Number)o[2]).intValue();
            float averageScore = (float)scoreSum/gradeSum;
            
            scoreBean.setProductionId(productionId);
            scoreBean.setGradeSum(gradeSum);
            scoreBean.setScoreSum(scoreSum);
            scoreBean.setAverageScore(averageScore);
            
            scoreList.add(scoreBean);
        }
        return scoreList;
	}

}
