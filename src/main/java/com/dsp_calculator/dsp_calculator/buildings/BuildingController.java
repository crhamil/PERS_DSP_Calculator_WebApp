package com.dsp_calculator.dsp_calculator.buildings;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsp_calculator.dsp_calculator.models.components.Building;

@Controller
public class BuildingController {
	@Value("${spring.application.name}")
    String appName;
	
	private final BuildingService service;
	public BuildingController(BuildingService s) { service = s; }
	
	@RequestMapping("/buildings")
    public String homePage(Model model) {
		HashMap<String, Building> buildings = service.getAllBuildings();
		int nbBuildings = service.getBuildingCount();
		System.out.println("....... " + nbBuildings + " bâtiments dans BuildingController");
		
        model.addAttribute("appName", appName);
        model.addAttribute("buildings", buildings);
        model.addAttribute("nbBuildings", nbBuildings);
        return "buildings";
    }
}
