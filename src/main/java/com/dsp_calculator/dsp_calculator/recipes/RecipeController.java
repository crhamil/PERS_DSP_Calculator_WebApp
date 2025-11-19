package com.dsp_calculator.dsp_calculator.recipes;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsp_calculator.dsp_calculator.models.recipes.Recipe;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
	
	@Value("${spring.application.name}")
	String appName;
	
	private final RecipeService service;
	public RecipeController(RecipeService s) { service = s; }
	
	@RequestMapping({"", "/"})
	public String recipesListing(Model model) {
		HashMap<String, Recipe> recipes = service.getAllRecipes();
		int nbRecipes = service.getRecipeCount();
		System.out.println("....... " + nbRecipes + " recettes dans RecipeController");
		
		model.addAttribute("appName", appName);
		model.addAttribute("recipes", recipes);
		model.addAttribute("nbRecipes", nbRecipes);
		return "recipes";
	}
	
	@GetMapping({"/{id}", "/{id}/"})
	public String recipeDetails(Model model, @PathVariable String id) {
		Recipe recipe = service.getRecipe(id);
		var ingredients = recipe.getIngredients();
		var ingredientsTree = service.getRecipeTree(recipe);
		
		model.addAttribute("appName", appName);
		model.addAttribute("r", recipe);
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("ingredientsTree", ingredientsTree);
		return "recipe";
	}
	
}
