package com.dsp_calculator.strategies.visitor;

import java.util.ArrayList;

import com.dsp_calculator.dsp_calculator.models.recipes.Ingredient;
import com.dsp_calculator.dsp_calculator.models.recipes.Recipe;
import com.dsp_calculator.dsp_calculator.utilities.Game;

public class RecipeNode {
	private Ingredient item; /* A node = an ingredient. Ingredient from which we deducted the recipe. Is null when root */
	private Recipe recipe; /* Recipe that produces the ingredient */
	private ArrayList<RecipeNode> children; /* Nodes = ingredients of the recipe */
	private static Game game = Game.getInstance();
	
	public RecipeNode(Ingredient i, Recipe r) {
		item = i;
		recipe = r;
		children = new ArrayList<>();
		buildTree();
	}
	
	public String getRecipeName() { return recipe.getName(); }
	public float getQuantity() { return item.getQuantity(); }
	public String getUrl() { return item.getUrl(); }
	public ArrayList<Ingredient> getIngredients() { return recipe.getIngredients(); }
	public void addChildren(RecipeNode node) { children.add(node); }
	public ArrayList<RecipeNode> getChildren() { return children; }
	public void buildTree() {
		for (Ingredient i : recipe.getIngredients()) {
			Recipe r = getRecipeByIngredient(i);
			if (r != null) children.add(new RecipeNode(i, r));
		}
	}
	
	
	public Recipe getRecipeByIngredient(Ingredient ingredient) {
		/* Implementation choice: we only choose the first recipe found */
		ArrayList<Recipe> allRecipes = game.getList(game.getRecipes().values(), null);
		for (Recipe recipe : allRecipes) {
			ArrayList<Ingredient> outputs = recipe.getOutput();
			for (Ingredient iOut : outputs) {
				if (iOut.getComponent().getId() == ingredient.getComponent().getId()) {
					return recipe;
				}
			}
		}
		/* If no recipe found, then it's likely to be a resource (obtainable via mining only, no recipe available) */
		return null;
	}

	public String toStringPretty(int i) {
		String sb = "\n";
		for (int j = 0; j < i; j++)  sb +="\t";
		sb += "recette " + getRecipeName() +  ", ingredients : ";
		for (var c : children) {
			sb += c.toStringPretty(i+1);
		}
		return sb;
	}
	
}
