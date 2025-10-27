package com.dsp_calculator.dsp_calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsp_calculator.dsp_calculator.utilities.Game;

@Controller
public class IndexController {
	@Value("${spring.application.name}")
    String appName;

	@RequestMapping("/")
    public String homePage(Model model) {
		int nbBuildings = Game.getInstance().getBuildings().size();
		int nbComponents = Game.getInstance().getComponents().size();
		int nbRecipes = Game.getInstance().getRecipes().size();
		System.out.println("....... " + nbRecipes + " recettes dans IndexController");
		
        model.addAttribute("appName", appName);
        model.addAttribute("nbBuildings", nbBuildings);
        model.addAttribute("nbComponents", nbComponents);
        model.addAttribute("nbRecipes", nbRecipes);
        return "index";
    }

}