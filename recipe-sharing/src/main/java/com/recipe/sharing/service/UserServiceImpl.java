package com.recipe.sharing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recipe.sharing.exception.UserExistsException;
import com.recipe.sharing.model.User;
import com.recipe.sharing.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;	
	
	@Override
	public User createUser(User user) throws UserExistsException {
		User doesExist = userRepository.findByEmail(user.getEmail());
		if(doesExist != null) {
			throw new UserExistsException ("There is already an user using this email account '" + user.getEmail()+ "'");
		}
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public void deleteUser(Long id) throws IllegalArgumentException{
		userRepository.deleteById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
