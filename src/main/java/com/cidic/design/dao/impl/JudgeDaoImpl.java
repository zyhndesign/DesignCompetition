package com.cidic.design.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.JudgeDao;
import com.cidic.design.model.Judge;

@Repository
@Component
@Qualifier(value = "judgeDaoImpl")
public class JudgeDaoImpl implements JudgeDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createJudge(Judge judge) {
		sessionFactory.getCurrentSession().save(judge);
	}

	@Override
	public void deleteJudge(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Judge j  where j.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public void updateJudge(Judge judge) {
		sessionFactory.getCurrentSession().update(judge);
	}

	@Override
	public Optional<Judge> findJudgeById(int id) {
		Judge judge = (Judge) sessionFactory.getCurrentSession().load(Judge.class, id);
		return Optional.ofNullable(judge);
	}

	@Override
	public List<Judge> getAllJudge() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Judge";
		Query query = session.createQuery(hql);
		return query.list();
	}

}
