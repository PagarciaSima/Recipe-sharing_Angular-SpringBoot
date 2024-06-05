package com.recipe.sharing.service;

import java.util.List;

import com.recipe.sharing.exception.UserExistsException;
import com.recipe.sharing.model.User;

public interface UserService {
	
	public User createUser(User user) throws UserExistsException;
	public void deleteUser(Long id) throws IllegalArgumentException;
	public List<User> getAllUsers();
}
