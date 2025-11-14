package com.dsp_calculator.dsp_calculator.buildings;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.dsp_calculator.dsp_calculator.models.components.Building;
import com.dsp_calculator.dsp_calculator.models.components.buildings.ConveyorBelt;
import com.dsp_calculator.dsp_calculator.models.components.buildings.Extractor;
import com.dsp_calculator.dsp_calculator.models.components.buildings.Factory;
import com.dsp_calculator.dsp_calculator.utilities.Game;

@Service
public class BuildingService {
	
	public HashMap<String, Building> getAllBuildings() { return Game.getInstance().getBuildings(); }
	public int getBuildingCount() { return getAllBuildings().size(); }
	public Building getBuilding(String id) { return Game.getInstance().getBuilding(id); }
	public String getBuildingClassInfo(Building b) {
		if (b instanceof ConveyorBelt) {
			return "conveyor-belt";
		} else if (b instanceof Extractor) {
			return "extractor";
		} else if (b instanceof Factory) {
			return "factory";
		} else {
			return null;
		}
	}
	
}