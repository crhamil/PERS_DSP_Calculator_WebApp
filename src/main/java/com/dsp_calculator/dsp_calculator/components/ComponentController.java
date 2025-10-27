package com.dsp_calculator.dsp_calculator.components;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsp_calculator.dsp_calculator.models.components.DSPComponent;

@Controller
public class ComponentController {
	@Value("${spring.application.name}")
    String appName;
	
	private final ComponentService service;
	public ComponentController(ComponentService s) { service = s; }
	
	@RequestMapping("/components")
    public String homePage(Model model) {
		HashMap<String, DSPComponent> components = service.getAllComponents();
		int nbComponents = service.getComponentCount();
		System.out.println("....... " + nbComponents + " composants et ressources dans ComponentController");
		
        model.addAttribute("appName", appName);
        model.addAttribute("components", components);
        model.addAttribute("nbComponents", nbComponents);
        return "components";
    }
}
