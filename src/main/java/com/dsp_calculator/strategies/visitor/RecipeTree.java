package com.dsp_calculator.strategies.visitor;

import java.util.ArrayList;

import com.dsp_calculator.dsp_calculator.models.recipes.Ingredient;
import com.dsp_calculator.dsp_calculator.models.recipes.Recipe;

public class RecipeTree {
	private RecipeNode root;
	
	public RecipeTree(Recipe r) {
		root = new RecipeNode(null, r);
	}
	
	public RecipeNode getRoot() { return root; }
	public String getRecipeName() { return root.getRecipeName(); }
	public ArrayList<Ingredient> getRecipeIngredients() { return root.getIngredients(); }
	public void buildTree() {
		//buildTreeRecursive();
	}
	
	public String toString() {
		String sb = "[";
		sb += root.toStringPretty(1);
		return sb + "]";
	}
	
	/*public void buildTreeRecursive(Ingredient c, ArrayList<Ingredient> visited, int indent) {
		if (visited.contains(c)) return;
		visited.add(c);
		ArrayList<Recipe> recipes = getRecipesByComponent(c);
		if (recipes.isEmpty()) return;
		for (Recipe r : recipes) {
			printIndent(indent);
			System.out.println("- " + r.getId() + ", entrées : ");
			ArrayList<Ingredient> inputs = r.getIngredients();
			for (Ingredient in : inputs) {
				printIndent(indent + 1);
				System.out.println("- " + in.getComponent().getId() + " x" + in.getQuantity());
				getRecipesByComponentRecursive(in, visited, indent + 2);
			}
		}
		
	}*/
	
}
