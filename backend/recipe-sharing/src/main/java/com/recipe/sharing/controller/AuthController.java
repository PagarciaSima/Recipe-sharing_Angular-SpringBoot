package com.recipe.sharing.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.sharing.config.JwtProvider;
import com.recipe.sharing.exception.UserException;
import com.recipe.sharing.model.User;
import com.recipe.sharing.repository.UserRepository;
import com.recipe.sharing.request.LoginRequest;
import com.recipe.sharing.response.AuthResponse;
import com.recipe.sharing.service.CustomUserDetailService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200/*")

public class AuthController {

	private UserRepository userRepository;
	private CustomUserDetailService customUserDetails;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		
		User userByEmail = userRepository.findByEmail(email);
		if(userByEmail != null) {
			throw new UserException(String.format("The email '%s' is being used by another account.", email));
		}
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFullName(fullName);
		
		userRepository.save(createdUser);
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		AuthResponse res = new AuthResponse();
		res.setJwt(token);
		res.setMessage("Signup success");
		return res;
	}
	
	@PostMapping("/signin")
	public AuthResponse login(@RequestBody LoginRequest loginRequest) {
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);
		AuthResponse res = new AuthResponse();
		res.setJwt(token);
		res.setMessage("Signin success");
		return res;
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(username);
		if(userDetails == null) {
			throw new BadCredentialsException("User not found");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid credentials");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	}
}
