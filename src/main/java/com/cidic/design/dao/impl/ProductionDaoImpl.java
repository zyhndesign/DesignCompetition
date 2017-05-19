package com.cidic.design.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.ProductionDao;
import com.cidic.design.model.ProductUserModel;
import com.cidic.design.model.Production;
import com.cidic.design.model.ScoreBean;
import com.cidic.design.model.User;

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
			hql = "  from Production p order by createtime desc";
		}
		else{
			hql = "  from Production p where p.groupId = ? order by createtime desc";
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
	public List<Production> getListProductionByPageAndUserId(int offset, int limit, int groupId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null;
		if (groupId == 0){
			hql = " from Production  where userId = ? order by createtime desc";
			query = session.createQuery(hql);
			query.setParameter(0, userId);
		}
		else{
			hql = " from Production where  groupId = ? and userId = ? order by createtime desc";
			query = session.createQuery(hql);
			query.setParameter(0, groupId);
			query.setParameter(1, userId);
		}
		
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public Optional<Production> getProductionDetailById(int id) {
		Production production = (Production) sessionFactory.getCurrentSession().get(Production.class, id);
		return Optional.ofNullable(production);
	}

	@Override
	public void updateProductionScore(int productionId, float averageScore) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "update Production set score = ? where id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, averageScore);
		query.setParameter(1, productionId);
		query.executeUpdate();
		 
	}
	
	@Override
	public void batchUpdateProductionScore(List<ScoreBean> list){
		//打开Session  
        Session session = sessionFactory.getCurrentSession();  
        //开始事务  
        Transaction tx = session.beginTransaction();  
       
        int i = 0;
        for (ScoreBean scoreBean : list)  
        {  
        	i++;
        	String hql = "update from Production set score = ? where id = ? ";
        	Query query = session.createQuery(hql);
        	query.setParameter(0, scoreBean.getScoreSum());
        	query.setParameter(1, scoreBean.getProductionId());
    		query.executeUpdate();
            if (i % 20 == 0)  
            {  
                session.flush();  
                session.clear();  
            }  
        }  
        //提交事务  
        tx.commit();  
        //关闭事务  
        sessionFactory.close();
	}

	@Override
	public int getCountProduction(int groupId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null;
		if (groupId == 0){
			hql = " select count(p) from Production p";
			query = session.createQuery(hql);
		}
		else{
			hql = " select count(p) from Production p where groupId = ? ";
			query = session.createQuery(hql);
			query.setParameter(0, groupId);
		} 
		
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public int getCountProductionByUserId(int groupId, int userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null;
		if (groupId == 0){
			hql = " select count(p) from Production p where userId = ? ";
			query = session.createQuery(hql);
			query.setParameter(0, userId);
		}
		else{
			hql = " select count(p) from Production p where groupId = ? and userId = ?";
			query = session.createQuery(hql);
			query.setParameter(0, groupId);
			query.setParameter(1, userId);
		} 
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public List<ProductUserModel> getListProductionByPageRelationRegisterUser(int offset, int limit, int groupId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		if (groupId == 0){
			hql = " select p.id, p.title, p.thumb, p.groupId, u.realname,u.mobile,u.address  from Production p, User u where p.userId = u.id order by createtime desc";
		}
		else{
			hql = " from p.title, p.thumb, u.realname , u.mobile, u.address  from Production p, "
					+ "User u Production where p.groupId = ? and p.userId = u.id order by createtime desc";
		}
		Query query = session.createQuery(hql);
		
		if (groupId != 0){
			query.setParameter(0, groupId);
		}
		
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List list = query.list();
		
		List<ProductUserModel> puList = new ArrayList<ProductUserModel>(10);
		ProductUserModel productUserModel = null;
        for(int i=0;i<list.size();i++)
        {
        	productUserModel = new ProductUserModel();
            Object []o = (Object[])list.get(i);
            int pId = ((Number)o[0]).intValue();
            String title = (String)o[1];
            String thumb = (String)o[2];
            int gId = ((Number)o[3]).intValue();
            String realname = (String)o[4];
            String mobile = (String)o[5];
            String address = ((String)o[6]);
            
            productUserModel.setpId(pId);
            productUserModel.setGroupId(gId);
            productUserModel.setThumb(thumb);
            productUserModel.setTitle(title);
            productUserModel.setRealname(realname);
            productUserModel.setMobile(mobile);
            productUserModel.setAddress(address);
            puList.add(productUserModel);
        }
        return puList;
	}

	@Override
	public int getCountProductionRelationRegisterUser(int groupId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";
		Query query = null;
		if (groupId == 0){
			hql = " select count(p) from Production p";
			query = session.createQuery(hql);
		}
		else{
			hql = " select count(p) from Production p where groupId = ? ";
			query = session.createQuery(hql);
			query.setParameter(0, groupId);
		} 
		
        return (int)((Long)query.uniqueResult()).longValue();
	}

}
