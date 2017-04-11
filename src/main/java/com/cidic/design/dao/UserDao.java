package com.cidic.design.dao;

import java.util.Optional;
import java.util.Set;

import com.cidic.design.model.User;

public interface UserDao {

	public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId, Long... roleIds);
    public void uncorrelationRoles(Long userId, Long... roleIds);

    public Optional<User> findOne(Long userId);

    public Optional<User> findByUsername(String username);

    public Set<String> findRoles(String username);

    public Set<String> findPermissions(String username);
    
}
