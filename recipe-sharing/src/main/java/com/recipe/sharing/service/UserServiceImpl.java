package com.recipe.sharing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recipe.sharing.config.JwtProvider;
import com.recipe.sharing.exception.UserException;
import com.recipe.sharing.model.User;
import com.recipe.sharing.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;	
	private JwtProvider jwtProvider;
	
	@Override
	public User createUser(User user) throws UserException {
		User doesExist = userRepository.findByEmail(user.getEmail());
		if(doesExist != null) {
			throw new UserException ("There is already an user using this email account '" + user.getEmail()+ "'");
		}
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public void deleteUser(Long id) {
		findUserById(id);
        userRepository.deleteById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(Long id) {
		Optional<User> opt = userRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("User with id " + id + " not found");
	}

	@Override
	public User updateUser(User user, Long id) {
		User oldUser = findUserById(id);
		oldUser.setPassword(user.getPassword());
		oldUser.setEmail(user.getEmail());
		oldUser.setFullName(user.getFullName());
		return userRepository.save(oldUser);
	}

	@Override
	public User findUserByJwt(String jwt) throws Exception {
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		if(email == null) {
			throw new Exception("Provide a valid jwt token");
		}
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UserException("User with email " + email + " not found");
		}
		return user;
	}
}
