package com.recipe.sharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.sharing.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
