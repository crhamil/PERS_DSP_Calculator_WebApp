package com.dsp_calculator.strategies.factory;

import java.util.ArrayList;

import com.dsp_calculator.dsp_calculator.models.components.Building;
import com.dsp_calculator.dsp_calculator.models.components.DSPComponent;
import com.dsp_calculator.dsp_calculator.models.components.FuelComponent;
import com.dsp_calculator.dsp_calculator.models.components.FuelResource;
import com.dsp_calculator.dsp_calculator.models.components.FuelType;
import com.dsp_calculator.dsp_calculator.models.components.Resource;

public class ComponentFactory {

	/* Factory design pattern */
	public static DSPComponent getComponent(String category, String id, String name, int row, int stack, FuelType fuelType, int value, ArrayList<Building> minedBy) {
		if (fuelType == null) {
			if (category.equals("components")) {
				return new DSPComponent(id, name, row, stack);
			} else if (category.equals("resource")) {
				return new Resource(id, name, row, stack, minedBy);
			}
		} else {
			if (category.equals("components")) {
				return new FuelComponent(id, name, row, stack, fuelType, value);
			} else if (category.equals("resource")) {
				return new FuelResource(id, name, row, stack, fuelType, value, minedBy);
			}
		}
		return null;
	}
	
}
