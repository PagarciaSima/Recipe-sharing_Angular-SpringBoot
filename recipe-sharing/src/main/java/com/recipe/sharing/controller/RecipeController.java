package com.recipe.sharing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.sharing.model.Recipe;
import com.recipe.sharing.model.User;
import com.recipe.sharing.service.RecipeService;
import com.recipe.sharing.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class RecipeController {
	
	private RecipeService recipeService;
	private UserService userService;
	
	@PostMapping("/recipes")
	public Recipe createRecipe(@RequestHeader("Authorization") String jwt, @RequestBody Recipe recipe) throws Exception {
		User user = userService.findUserByJwt(jwt);
		Recipe createdRecipe = recipeService.createRecipe(recipe, user);
		return createdRecipe;
	}
	
	@GetMapping("/recipes")
	public List<Recipe> getAllRecipe(){
		return recipeService.findAllRecipe();
	}
	
	@GetMapping("/recipes/{recipeId}")
	public Recipe getRecipeById(@PathVariable Long recipeId){
		return recipeService.findRecipeById(recipeId);
	}
	
	@DeleteMapping("/recipes/{recipeId}")
	public String deleteRecipe(@PathVariable Long recipeId){
		recipeService.deleteRecipe(recipeId);
		return String.format("Recipe ID '%s' successfully deleted", recipeId);
	}	
	
	@PutMapping("/recipes/user/{recipeId}")
	public Recipe updateRecipe(@PathVariable Long recipeId, @RequestBody Recipe recipe) {
		return recipeService.updateRecipe(recipe, recipeId);
	}
	
	@PutMapping("/recipes/{recipeId}/like")
	public Recipe likeRecipe(@PathVariable Long recipeId, @RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		return recipeService.likeRecipe(recipeId, user);
	}
	
}
