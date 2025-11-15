package com.dsp_calculator.dsp_calculator.buildings;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsp_calculator.dsp_calculator.models.components.Building;

@Controller
@RequestMapping("/buildings")
public class BuildingController {
	@Value("${spring.application.name}")
	String appName;
	
	private final BuildingService service;
	public BuildingController(BuildingService s) { service = s; }
	
	@RequestMapping({"", "/"})
	public String buildingsListing(Model model) {
		HashMap<String, Building> buildings = service.getAllBuildings();
		int nbBuildings = service.getBuildingCount();
		System.out.println("....... " + nbBuildings + " bâtiments dans BuildingController");
		
		model.addAttribute("appName", appName);
		model.addAttribute("buildings", buildings);
		model.addAttribute("nbBuildings", nbBuildings);
		return "buildings";
	}
	
	@GetMapping({"/{id}", "/{id}/"})
    public String buildingDetails(Model model, @PathVariable String id) {
		// TODO: if building not found, return error page
		Building building = service.getBuilding(id);
		String buildingId = building.getId();
		String buildingName = building.getName();
		String buildingType = building.getClassStringPretty();
		String buildingTypeInfo = service.getBuildingClassInfo(building);
		
		model.addAttribute("appName", appName);
		model.addAttribute("b", building);
		model.addAttribute("bId", buildingId);
		model.addAttribute("bName", buildingName);
		model.addAttribute("bType", buildingType);
		model.addAttribute("infoFragment", buildingTypeInfo);
		return "building";
	}
	
}
