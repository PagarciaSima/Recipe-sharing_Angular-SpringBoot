package com.recipe.sharing.service;

import java.util.List;

import com.recipe.sharing.model.Recipe;
import com.recipe.sharing.model.User;

public interface RecipeService {
	
	public Recipe createRecipe(Recipe recipe, User user);
	public Recipe findRecipeById(Long id) throws Exception;
	public void deleteRecipe(Long id) throws Exception;
	public Recipe updateRecipe(Recipe recipe, Long Id) throws Exception;
	public List<Recipe> findAllRecipe();
	public Recipe likeRecipe(Long recipeId, User user) throws Exception;
}
