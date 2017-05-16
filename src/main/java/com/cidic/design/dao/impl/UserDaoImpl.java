package com.cidic.design.dao.impl;

import java.util.HashSet;
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

		for (Long roleId : roleIds) {
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

		for (Long roleId : roleIds) {
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
		User user = (User) sessionFactory.getCurrentSession().load(User.class, userId);
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from User u where u.email = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, email);
		List<User> list = query.list();
		if (list.size() > 0) {
			Optional<User> user = Optional.ofNullable(list.get(0));
			return user;
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Set<String> findRoles(String username) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select rolename from user u, user_role ur,role r where u.email = ? and u.id = ur.userId and r.id = ur.roleId";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, username);
		Set<String> set=new HashSet<String>();
		set.addAll(query.list());
		return set;
	}

	@Override
	public Set<String> findPermissions(String username) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select permission_name from user u, user_role ur,role r,permission_role pr,permission p where u.email = ? and u.id = ur.userId and r.id = ur.roleId "
				+ "and r.id = pr.roleId and pr.permissionId = p.id ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, username);
		Set<String> set=new HashSet<String>();
		set.addAll(query.list());
		return set;
	}

	@Override
	public void updateValidSign(String email, int validValue) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update User u set u.valid = ? where u.email = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, validValue);
		query.setParameter(1, email);
		query.executeUpdate();
	}

	@Override
	public void updateActiveSign(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update User u set u.activesign = 1 where u.email = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, email);
		query.executeUpdate();
	}

	@Override
	public void updatePwd(String email, String password, String oldSlot,String newSlot) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update User u set u.password = ?, u.slot = ? where u.email = ? and u.slot = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, password); 
		query.setParameter(1, oldSlot); 
        query.setParameter(2, email); 
        query.setParameter(3, newSlot);
		query.executeUpdate();
	}

}
