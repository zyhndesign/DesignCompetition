package com.cidic.design.dao.impl;

import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.FindPwdDao;
import com.cidic.design.model.FindPwd;

@Repository
@Component
@Qualifier(value = "filePwdDaoImpl")
public class FindPwdDaoImpl implements FindPwdDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createFindPwd(FindPwd findPwd) {
		sessionFactory.getCurrentSession().save(findPwd);
	}

	@Override
	public Optional<FindPwd> getFindPwdByCondition(String email, String validCode) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from FindPwd where email = ? and validCode = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, email); 
        query.setParameter(1, validCode); 
        query.setMaxResults(1);
        query.setCacheable(true);
        Optional<FindPwd> optFeedback = Optional.ofNullable((FindPwd)query.uniqueResult());
        return optFeedback;
	}

}
