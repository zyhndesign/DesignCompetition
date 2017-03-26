package com.cidic.design.dao;

import com.cidic.design.model.Permission;

public interface PermissionDao {

	 public Permission createPermission(Permission permission);

	 public void deletePermission(Long permissionId);
	    
}
