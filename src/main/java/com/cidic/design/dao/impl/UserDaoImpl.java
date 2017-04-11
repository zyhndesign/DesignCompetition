package com.cidic.design.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.UserDao;
import com.cidic.design.model.Role;
import com.cidic.design.model.User;
import com.cidic.design.model.UserRole;

@Repository
@Component
@Qualifier(value = "userDaoImpl")
public class UserDaoImpl implements UserDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void deleteUser(Long userId) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = " update User u set u.valid = 1 where u.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		query.executeUpdate();
		
	}

	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setId(userId.intValue());
		
		for (Long roleId : roleIds){
			UserRole userRole = new UserRole();
			Role role = new Role();
			role.setId(roleId.intValue());
			userRole.setUser(user);
			userRole.setRole(role);
			session.save(userRole);
		}
	}

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setId(userId.intValue());
		
		for (Long roleId : roleIds){
			UserRole userRole = new UserRole();
			Role role = new Role();
			role.setId(roleId.intValue());
			userRole.setUser(user);
			userRole.setRole(role);
			session.delete(userRole);
		}
	}

	@Override
	public Optional<User> findOne(Long userId) {
		User user = (User)sessionFactory.getCurrentSession().load(User.class, userId);
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from User u where u.email = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		List<User> list = query.list();
        if (list.size() > 0){
        	Optional<User> user = Optional.ofNullable(list.get(0));
     		return user;
        }
        else{
        	return Optional.empty();
        }
	}

	@Override
	public Set<String> findRoles(String username) {
		return null;
	}

	@Override
	public Set<String> findPermissions(String username) {
		return null;
	}

}
