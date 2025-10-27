package com.dsp_calculator.dsp_calculator.models.recipes;

import java.util.ArrayList;
import java.util.HashMap;

import com.dsp_calculator.dsp_calculator.models.components.Building;
import com.dsp_calculator.dsp_calculator.models.components.DSPComponent;
import com.dsp_calculator.dsp_calculator.models.components.buildings.Factory;

public class Recipe implements Comparable<Recipe> {
	
	private String id; /* Id de la recette, attention : dans fichier = type d'élément produit */
	private String name; /* Nom de la recette */
	
	private HashMap<DSPComponent, Float> in; /* Ingrédients */
	private HashMap<DSPComponent, Float> out; /* Elements produits */
	
	private float time; /* Temps de production */
	
	private ArrayList<Factory> producers; /* Id de ceux qui peuvent exécuter la recette */
	
	public Recipe(String _id, String _name, float _time, ArrayList<Factory> _producers, HashMap<DSPComponent, Float> _in, HashMap<DSPComponent, Float> _out) {
		id = _id;
		name = _name;
		time = _time;
		producers = _in == null ? new ArrayList<>() : _producers;
		in = _in == null ? new HashMap<>() : _in;
		out = _out == null ? new HashMap<>() : _out;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Recette {\n");
	    sb.append("  ID       : ").append(id).append("\n");
	    sb.append("  Nom      : ").append(name).append("\n");
	    sb.append("  Temps    : ").append(time).append(" sec\n");

	    sb.append("  Ingrédients :\n");
	    in.forEach((comp, qty) -> sb.append("    - ").append(comp).append(" x ").append(qty).append("\n"));

	    sb.append("  Produits :\n");
	    out.forEach((comp, qty) -> sb.append("    - ").append(comp).append(" x ").append(qty).append("\n"));

	    sb.append("  Producteurs :\n");
	    for (Building b : producers) {
	        sb.append("    - ").append(b).append("\n");
	    }

	    sb.append("}");
	    return sb.toString();
	}
	
	/* Implémente Comparable par facilité (dans la majorité des cas, on voudra getName croissant
	 * Un comparateur personnalisé sera utilisé en cas de besoin */
	@Override
	public int compareTo(Recipe r) { return name.compareTo(r.getName()); }
	
	public String getId() { return id; }
	public String getName() { return name; }
	public ArrayList<Factory> getProducers() { return producers; }
	public HashMap<DSPComponent, Float> getIngredients() { return in; }
	public HashMap<DSPComponent, Float> getOutput() { return out; }
	
	public ArrayList<String> getOutputClassStringPretty() {
		ArrayList<String> result = new ArrayList<>();
		for (DSPComponent c : out.keySet()) {
			if (!result.contains(c.getClassStringPretty())) result.add(c.getClassStringPretty());
		}
		return result;
	}
}
