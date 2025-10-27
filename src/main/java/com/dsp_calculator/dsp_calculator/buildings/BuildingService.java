package com.dsp_calculator.dsp_calculator.buildings;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.dsp_calculator.dsp_calculator.models.components.Building;
import com.dsp_calculator.dsp_calculator.utilities.Game;

@Service
public class BuildingService {
	
	public HashMap<String, Building> getAllBuildings() { return Game.getInstance().getBuildings(); }
	public int getBuildingCount() { return getAllBuildings().size(); }
	
}