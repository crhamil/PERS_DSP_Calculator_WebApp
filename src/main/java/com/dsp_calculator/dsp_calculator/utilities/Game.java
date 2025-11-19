package com.dsp_calculator.dsp_calculator.utilities;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.dsp_calculator.dsp_calculator.models.components.Building;
import com.dsp_calculator.dsp_calculator.models.components.DSPComponent;
import com.dsp_calculator.dsp_calculator.models.components.Fuel;
import com.dsp_calculator.dsp_calculator.models.components.buildings.Extractor;
import com.dsp_calculator.dsp_calculator.models.components.buildings.Factory;
import com.dsp_calculator.dsp_calculator.models.components.buildings.PowerPlant;
import com.dsp_calculator.dsp_calculator.models.recipes.Recipe;

public class Game {
	private ArrayList<String> categories; /* "buildings", "resource", "components" */
	private HashMap<String, DSPComponent> gameItems; /* Objets de classe Component, Resource et tous les Building, y compris ceux de sous-classe. 79 */
	private HashMap<String, Building> buildings; /* Objets de classe Building, y compris ceux de sous-classe */
	private HashMap<String, Building> extractors; /* Objets de Extractor (sous-classe de Building) */
	private HashMap<String, Building> powerPlants; /* Objets de classe PowerPlant (sous-classe de Building) */
	private HashMap<String, Building> factories; /* Objets de classe Factory */
	private HashMap<String, DSPComponent> components; /* Objets de classe Component ou Resource */
	private HashMap<String, Fuel> fuels; /* Objets de type Fuel */
	
	private HashMap<String, Recipe> recipes; /* Objets de classe Recipe */
	
	// Singleton design pattern
	private static Game instance;
	public static int cpt = 0;

	private Game() { 
		gameItems = new HashMap<>();
		buildings = new HashMap<>();
		extractors = new HashMap<>();
		powerPlants = new HashMap<>();
		factories = new HashMap<>();
		components = new HashMap<>();
		fuels = new HashMap<>();
		
		recipes = new HashMap<>();
		
		categories = new ArrayList<>();
		categories.add("buildings");
		categories.add("resource");
		categories.add("components");
		cpt++;
	}
	
	public static Game getInstance() {
		if (instance != null) {
			return instance;
		}
		else {
			instance = new Game();
			return instance;
		}
	}
	
	@Override
	public String toString() {
		return "Jeu";
	}
	
	/* Getters */
	public ArrayList<String> getCategories() { return categories; }
	public HashMap<String, DSPComponent> getGameItems() { return gameItems; }
	public HashMap<String, Building> getBuildings() { return buildings; }
	public HashMap<String, Building> getExtractors() { return extractors; }
	public HashMap<String, Building> getPowerPlants() { return powerPlants; }
	public HashMap<String, Building> getFactories() { return factories; }
	public HashMap<String, DSPComponent> getComponents() { return components; }
	public HashMap<String, Fuel> getFuels() { return fuels; }
	public HashMap<String, Recipe> getRecipes() { return recipes; }

	public DSPComponent getGameItem(String k) { return gameItems.get(k); }
	public Building getBuilding(String k) { return buildings.get(k); }
	public PowerPlant getPowerPlant(String k) { return (PowerPlant) powerPlants.get(k); }
	public Extractor getExtractor(String k) { return (Extractor) extractors.get(k); }
	public Factory getFactory(String k) { return (Factory) factories.get(k); }
	public DSPComponent getComponent(String k) { return components.get(k); }
	public Fuel getFuel(String k) { return fuels.get(k); }
	public Recipe getRecipe(String k) { return recipes.get(k); }
	
	
	/* Setters */
	public void addGameItem(String k, DSPComponent v) { gameItems.put(k, v); }
	public void addBuilding(String k, Building v) { buildings.put(k, v); }
	public void addExtractor(String k, Building v) { extractors.put(k, v); }
	public void addPowerPlant(String k, Building v) { powerPlants.put(k, v); }
	public void addFactory(String k, Building v) { factories.put(k, v); }
	public void addComponent(String k, DSPComponent v) { components.put(k, v); }
	public void addFuel(String k, Fuel v) { fuels.put(k, v); }
	public void addRecipe(String k, Recipe v) { recipes.put(k, v); }
	
	/**
	 * Méthode générique pour convertir une collection (typiquement les valeurs d'une HashMap)
	 * en une liste triée selon un comparateur donné.
	 *
	 * @param <T>       Le type des éléments contenus dans la collection.
	 * @param values    La collection des éléments à transformer en liste.
	 * @param comparator Le comparateur utilisé pour trier la liste. Peut être {@code null}, auquel cas l'ordre naturel sera utilisé.
	 * @return Une liste triée contenant tous les éléments de la collection.
	 */
    public <T> ArrayList<T> getList(Collection<T> values, Comparator<T> comparator) {
        ArrayList<T> list = new ArrayList<>(values);
        Collections.sort(list, comparator);
        return list;
    }
	
	/* TODO: Params devront être les paramètres d'une requête, de sorte que [{name: asc}, {category: desc}] par ex */
	public ArrayList<DSPComponent> allNotBuildingsSorted(String ... params) {
		return getList(components.values(), Comparator.comparing(DSPComponent::getName));
	}
	/* TODO: Params devront être les paramètres d'une requête, de sorte que [{name: asc}, {category: desc}] par ex */
	public ArrayList<Building> allBuildingsSorted(String ... params) {
		return getList(buildings.values(), Comparator.comparing(Building::getName));
	}
	/* TODO: Params devront être les paramètres d'une requête, de sorte que [{name: asc}, {category: desc}] par ex */
	public ArrayList<Recipe> allRecipesSorted(String ... params) {
		return getList(recipes.values(), Comparator.comparing(Recipe::getName));
	}

    
}
