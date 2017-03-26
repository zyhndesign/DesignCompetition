package com.cidic.design.dao;

import com.cidic.design.model.Role;

public interface RoleDao {
	
	public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
    
}
