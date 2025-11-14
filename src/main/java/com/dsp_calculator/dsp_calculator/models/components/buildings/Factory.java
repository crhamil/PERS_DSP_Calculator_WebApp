package com.dsp_calculator.dsp_calculator.models.components.buildings;

import java.util.ArrayList;

import com.dsp_calculator.dsp_calculator.models.components.Building;
import com.dsp_calculator.dsp_calculator.models.recipes.Recipe;
import com.dsp_calculator.dsp_calculator.utilities.Game;

public class Factory extends Building {
	
	private float speed; /* Vitesse de minage OU d'extraction des ressources si Extractor */
	private int usage; /* Energie utilisée en fonctionnement */
	private int drain; /* Energie utilisée au repos */
	private int modules;

	public Factory(String _id, String _name, int _row, int _stack, float _speed, int _usage, int _drain, int _modules) {
		super(_id, _name, _row, _stack);
		speed = _speed;
		usage = _usage;
		drain = _drain;
		modules = _modules;
	}
	
	public String getClassStringPretty() { return "Usine électrique"; }
	public String toString() { return "Usine électrique " +  id + " : " + name; }
	
	public float getSpeed() { return speed; }
	public int getUsage() { return usage; }
	public int getDrain() { return drain; }
	public int getModules() { return modules; }
	
	public ArrayList<Recipe> getRecipes() {
		Game game = Game.getInstance();
		ArrayList<Recipe> gameRecipes = game.getList(game.getRecipes().values(), null);
		ArrayList<Recipe> bRecipes = new ArrayList<>();
		for (Recipe r : gameRecipes) {
			for (Building b : r.getProducers()) {
				if (b.equals(this)) bRecipes.add(r);
			}
		}
		return bRecipes;
	}
	
	public float getProductionSpeed() { return speed; }
	public int getElectricConsumption() {
		/* TODO: si usine non-utilisée -> return drain, sinon -> return usage */
		if (true) return usage;
		return drain;
	}

}
