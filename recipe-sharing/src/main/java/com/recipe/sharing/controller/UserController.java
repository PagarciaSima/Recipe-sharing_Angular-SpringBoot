package com.recipe.sharing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.sharing.exception.UserException;
import com.recipe.sharing.model.User;
import com.recipe.sharing.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserController {
	
	private UserService userService;
	
	@GetMapping("/user/profile")
	public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		return user;
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) throws UserException {
		return userService.updateUser(user, id);
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable ("id") Long id) {
		userService.deleteUser(id);
		return String.format("User ID '%s' successfully deleted", id);
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();		
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable ("id") Long id) {
		return userService.findUserById(id);		
	}

}
