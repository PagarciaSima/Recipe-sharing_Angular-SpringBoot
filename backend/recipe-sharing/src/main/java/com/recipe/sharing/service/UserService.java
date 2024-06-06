package com.recipe.sharing.service;

import java.util.List;

import com.recipe.sharing.model.User;

public interface UserService {
	
	public User createUser(User user);
	public User updateUser(User user, Long id);
	public void deleteUser(Long id);
	public List<User> getAllUsers();
	public User findUserById(Long userId);
	public User findUserByJwt(String jwt) throws Exception;
}
