package com.recipe.sharing.service;

import java.util.List;

import com.recipe.sharing.model.Recipe;
import com.recipe.sharing.model.User;

public interface RecipeService {
	
	public Recipe createRecipe(Recipe recipe, User user);
	public Recipe findRecipeById(Long id);
	public void deleteRecipe(Long id);
	public Recipe updateRecipe(Recipe recipe, Long Id);
	public List<Recipe> findAllRecipe();
	public Recipe likeRecipe(Long recipeId, User user);
}
