package com.dsp_calculator.dsp_calculator.models.recipes;

import java.util.ArrayList;

import com.dsp_calculator.dsp_calculator.models.components.buildings.Factory;

public class Recipe implements Comparable<Recipe> {
	
	private String id; /* Id de la recette, attention : dans fichier = type d'élément produit */
	private String name; /* Nom de la recette */
	
	private ArrayList<Ingredient> in; /* Ingrédients */
	private ArrayList<Ingredient> out; /* Elements produits */
	
	private float time; /* Temps de production */
	
	private ArrayList<Factory> producers; /* Id de ceux qui peuvent exécuter la recette */
	
	public Recipe(String _id, String _name, float _time, ArrayList<Factory> _producers, ArrayList<Ingredient> _in, ArrayList<Ingredient> _out) {
		id = _id;
		name = _name;
		time = _time;
		producers = _in == null ? new ArrayList<>() : _producers;
		in = _in == null ? new ArrayList<>() : _in;
		out = _out == null ? new ArrayList<>() : _out;
	}
	
	@Override
	public String toString() { return "Recette " + name; }
	public float getTime() { return time; }
	
	/* Implémente Comparable par facilité (dans la majorité des cas, on voudra getName croissant
	 * Un comparateur personnalisé sera utilisé en cas de besoin */
	@Override
	public int compareTo(Recipe r) { return name.compareTo(r.getName()); }
	
	public String getId() { return id; }
	public String getName() { return name; }
	public ArrayList<Factory> getProducers() { return producers; }
	public ArrayList<Ingredient> getIngredients() { return in; }
	public ArrayList<Ingredient> getOutput() { return out; }
	
	public ArrayList<String> getOutputClassStringPretty() {
		ArrayList<String> result = new ArrayList<>();
		for (Ingredient c : out) {
			if (!result.contains(c.getComponent().getClassStringPretty())) result.add(c.getComponent().getClassStringPretty());
		}
		return result;
	}
}
