package com.recipe.sharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
    @Size(min = 6, message = "Password must be at least 6 characters long")
	@JsonIgnore
	private String password;
	
	@Column(nullable = false, unique = true)
	@Email
	private String email;
	
	@Column(nullable = false)
	private String fullName;
}
