package com.cidic.design.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.PermissionDao;
import com.cidic.design.model.Permission;
import com.cidic.design.service.PermissionsService;


@Service
@Component
@Qualifier(value = "permissionServiceImpl")
@Transactional
public class PermissionsServiceImpl implements PermissionsService {

	@Autowired
	@Qualifier(value = "permissionDaoImpl")
	private PermissionDao permissionDaoImpl;
	
	@Override
	public Permission createPermission(Permission permission) {
		
		return permissionDaoImpl.createPermission(permission);
	}

	@Override
	public void deletePermission(Long permissionId) {
		permissionDaoImpl.deletePermission(permissionId);
	}

}
