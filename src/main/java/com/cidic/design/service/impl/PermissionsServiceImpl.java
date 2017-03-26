package com.cidic.design.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cidic.design.dao.PermissionDao;
import com.cidic.design.model.Permission;
import com.cidic.design.service.PermissionsService;


@Service
@Component
@Qualifier(value = "permissionsServiceImpl")
public class PermissionsServiceImpl implements PermissionsService {

	@Autowired
	@Qualifier(value = "permissionsDaoImpl")
	private PermissionDao permissionsDaoImpl;
	
	@Override
	public Permission createPermission(Permission permission) {
		
		return permissionsDaoImpl.createPermission(permission);
	}

	@Override
	public void deletePermission(Long permissionId) {
		permissionsDaoImpl.deletePermission(permissionId);
	}

}
