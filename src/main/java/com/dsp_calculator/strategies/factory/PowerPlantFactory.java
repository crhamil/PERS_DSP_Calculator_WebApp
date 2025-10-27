package com.dsp_calculator.strategies.factory;

import com.dsp_calculator.dsp_calculator.models.components.FuelType;
import com.dsp_calculator.dsp_calculator.models.components.buildings.Burner;
import com.dsp_calculator.dsp_calculator.models.components.buildings.ElectricProduction;
import com.dsp_calculator.dsp_calculator.models.components.buildings.PowerPlant;
import com.dsp_calculator.dsp_calculator.utilities.FuelTypeException;

public class PowerPlantFactory {
	
	/* Factory design pattern */
	public static PowerPlant getPowerPlant(String plant_type, String id, String name, int row, int stack, float speed, int value, FuelType category) throws FuelTypeException {
		if (plant_type.equals("burner")) { return new Burner(id, name, row, stack, speed, value, category); }
		else if (plant_type.equals("electric-production")) { return new ElectricProduction(id, name, row, stack, speed, value); }
		else return null;
	}
}
