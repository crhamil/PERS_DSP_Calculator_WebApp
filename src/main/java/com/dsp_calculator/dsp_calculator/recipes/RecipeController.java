package com.dsp_calculator.dsp_calculator.recipes;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsp_calculator.dsp_calculator.models.recipes.Recipe;

@Controller
public class RecipeController {
	
	@Value("${spring.application.name}")
	String appName;
	
	private final RecipeService service;
	public RecipeController(RecipeService s) { service = s; }
	
	@RequestMapping("/recipes")
	public String homePage(Model model) {
		HashMap<String, Recipe> recipes = service.getAllRecipes();
		int nbRecipes = service.getRecipeCount();
		System.out.println("....... " + nbRecipes + " recettes dans RecipeController");
		
		model.addAttribute("appName", appName);
		model.addAttribute("recipes", recipes);
		model.addAttribute("nbRecipes", nbRecipes);
		return "recipes";
	}
	
}
