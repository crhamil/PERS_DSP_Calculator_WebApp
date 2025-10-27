package com.dsp_calculator.strategies.factory;

import com.dsp_calculator.dsp_calculator.models.components.buildings.ElectricExtractor;
import com.dsp_calculator.dsp_calculator.models.components.buildings.Extractor;
import com.dsp_calculator.dsp_calculator.models.components.buildings.NonElectricExtractor;

public class ExtractorFactory {
	
	/* Factory design pattern */
	public static Extractor getExtractor(String id, String name, int row, int stack, float speed, int usage, int drain) {
		if (usage > 0) { return new ElectricExtractor(id, name, row, stack, speed, usage, drain); }
		else { return new NonElectricExtractor(id, name, row, stack, speed); }
	}
	
}
