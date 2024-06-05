package com.recipe.sharing.service;

import org.springframework.stereotype.Service;

import com.recipe.sharing.model.User;
import com.recipe.sharing.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;	
	
	@Override
	public User createUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

}
