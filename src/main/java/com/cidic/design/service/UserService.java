package com.cidic.design.service;

import java.util.Optional;
import java.util.Set;

import com.cidic.design.model.User;

public interface UserService {
	
	public int createUser(User user);
    public int updateUser(User user);
    public int deleteUser(Long userId);

    public int correlationRoles(Long userId, Long... roleIds);
    public int uncorrelationRoles(Long userId, Long... roleIds);

    public Optional<User> findOne(Long userId);

    public Optional<User> findByUsername(String username);

    public Set<String> findRoles(String username);

    public Set<String> findPermissions(String username);
    
    
}
