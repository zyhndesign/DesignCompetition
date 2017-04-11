package com.cidic.design.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.design.dao.PermissionDao;
import com.cidic.design.model.Permission;

@Repository
@Component
@Qualifier(value = "permissionDaoImpl")
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Permission createPermission(Permission permission) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePermission(Long permissionId) {
		// TODO Auto-generated method stub

	}

}
