package com.dsp_calculator.dsp_calculator.recipes;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.dsp_calculator.dsp_calculator.models.recipes.Recipe;
import com.dsp_calculator.dsp_calculator.utilities.Game;

@Service
public class RecipeService {
	
	public HashMap<String, Recipe> getAllRecipes() { return Game.getInstance().getRecipes(); }
	public int getRecipeCount() { return getAllRecipes().size(); }
	
}
