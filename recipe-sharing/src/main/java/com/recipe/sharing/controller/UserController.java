package com.recipe.sharing.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.sharing.exception.UserExistsException;
import com.recipe.sharing.model.User;
import com.recipe.sharing.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserController {
	
	private UserService userService;
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) throws UserExistsException {
		return userService.createUser(user);
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable ("id") Long id) {
		userService.deleteUser(id);
		return String.format("User ID '%s' successfully deleted", id);
	}

}
