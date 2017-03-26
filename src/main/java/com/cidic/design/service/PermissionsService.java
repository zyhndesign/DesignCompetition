package com.cidic.design.service;

import com.cidic.design.model.Permission;

public interface PermissionsService {

	public Permission createPermission(Permission permission);

	public void deletePermission(Long permissionId);
}
