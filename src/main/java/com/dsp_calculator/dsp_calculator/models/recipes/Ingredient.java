package com.dsp_calculator.dsp_calculator.models.recipes;

import com.dsp_calculator.dsp_calculator.models.components.Building;
import com.dsp_calculator.dsp_calculator.models.components.DSPComponent;

public class Ingredient {
	private DSPComponent ingredient;
	private float quantity;
	
	public Ingredient(DSPComponent _ingredient, float _quantity) {
		ingredient = _ingredient;
		quantity = _quantity;
	}
	
	public DSPComponent getComponent() { return ingredient; }
	public float getQuantity() { return quantity; }
	public String getUrl() { return ingredient instanceof Building ? "/buildings/" +ingredient.getId() : "/components/" + ingredient.getId(); }
	
}
