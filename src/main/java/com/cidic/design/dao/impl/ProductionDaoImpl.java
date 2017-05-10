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

import com.cidic.design.dao.ProductionDao;
import com.cidic.design.model.Production;

@Repository
@Component
@Qualifier(value = "productionDaoImpl")
public class ProductionDaoImpl implements ProductionDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createProduction(Production production) {
		sessionFactory.getCurrentSession().save(production);
	}

	@Override
	public void updateProduction(Production production) {
		sessionFactory.getCurrentSession().update(production);
	}

	@Override
	public void deleteProduction(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Production p  where p.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<Production> getListProductionByPage(int offset, int limit, int groupId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		if (groupId == 0){
			hql = " from Production order by createtime desc";
		}
		else{
			hql = " from Production where groupId = ? order by createtime desc";
		}
		Query query = session.createQuery(hql);
		
		if (groupId != 0){
			query.setParameter(0, groupId);
		}
		
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public List<Production> getListProductionByPageAndUserId(int userId, int offset, int limit, int groupId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null;
		if (groupId == 0){
			hql = " from Production where userId = ? order by createtime desc";
			query = session.createQuery(hql);
			query.setParameter(0, userId);
		}
		else{
			hql = " from Production where userId = ? and groupId = ? order by createtime desc";
			query = session.createQuery(hql);
			query.setParameter(0, userId);
			query.setParameter(1, groupId);
		}
		
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public Optional<Production> getProductionDetailById(int id) {
		Production production = (Production) sessionFactory.getCurrentSession().load(Production.class, id);
		return Optional.ofNullable(production);
	}

}
