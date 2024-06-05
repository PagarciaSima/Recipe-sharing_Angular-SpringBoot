package com.recipe.sharing.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "recipe")
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;	
	
	@ManyToOne
	private User user;
	@Column(nullable = false)
	private String image;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private boolean vegetarian;
	private LocalDateTime createdAt;
	private List<Long> likes = new ArrayList<>();
}
