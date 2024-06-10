package com.recipe.sharing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recipe.sharing.exception.RecipeException;
import com.recipe.sharing.model.Recipe;
import com.recipe.sharing.model.User;
import com.recipe.sharing.repository.RecipeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService{

	private RecipeRepository recipeRepository;

	@Override
	public Recipe createRecipe(Recipe recipe, User user) {
		Recipe createdRecipe = new Recipe();
		createdRecipe.setTitle(recipe.getTitle());
		createdRecipe.setImage(recipe.getImage());
		createdRecipe.setDescription(recipe.getDescription());
		createdRecipe.setVegetarian(recipe.isVegetarian());
		createdRecipe.setCreatedAt(LocalDateTime.now());
		createdRecipe.setLikes(recipe.getLikes());
		createdRecipe.setUser(user);
		return recipeRepository.save(createdRecipe);
	}

	@Override
	public Recipe findRecipeById(Long id)  {
		Optional<Recipe> opt = recipeRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new RecipeException("Recipe not found with ID '" + id + "'");
	}

	@Override
	public void deleteRecipe(Long id){
		//findRecipeById(id);
		recipeRepository.deleteById(id);	
	}

	@Override
	public Recipe updateRecipe(Recipe recipe, Long id) {
		Recipe oldRecipe = findRecipeById(id);
		oldRecipe.setTitle(recipe.getTitle());
		oldRecipe.setImage(recipe.getImage());
		oldRecipe.setDescription(recipe.getDescription());
		oldRecipe.setVegetarian(recipe.isVegetarian());
		oldRecipe.setCreatedAt(LocalDateTime.now());
		oldRecipe.setLikes(recipe.getLikes());
		return recipeRepository.save(oldRecipe);
		
	}

	@Override
	public List<Recipe> findAllRecipe() {
		return recipeRepository.findAll();
	}

	@Override
	public Recipe likeRecipe(Long recipeId, User user) {
		Recipe recipe = findRecipeById(recipeId);
		// If recipe contains like from the user already, remove it
		if (recipe.getLikes().contains(user.getId())) {
			recipe.getLikes().remove(user.getId());
		} else {
			recipe.getLikes().add(user.getId());
		}
		return recipeRepository.save(recipe);
	}
}
