package com.dsp_calculator.dsp_calculator.components;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsp_calculator.dsp_calculator.models.components.DSPComponent;

@Controller
@RequestMapping("/components")
public class ComponentController {
	@Value("${spring.application.name}")
    String appName;
	
	private final ComponentService service;
	public ComponentController(ComponentService s) { service = s; }
	
	@RequestMapping({"", "/"})
    public String componentsListing(Model model) {
		HashMap<String, DSPComponent> components = service.getAllComponents();
		int nbComponents = service.getComponentCount();
		System.out.println("....... " + nbComponents + " composants et ressources dans ComponentController");
		
		model.addAttribute("appName", appName);
		model.addAttribute("components", components);
		model.addAttribute("nbComponents", nbComponents);
		return "components";
	}
	
	@GetMapping({"/{id}", "/{id}/"})
    public String componentDetails(Model model, @PathVariable String id) {
		// TODO: if component not found, return error page
		DSPComponent component = service.getComponent(id);
		String componentId = component.getId();
		String componentName = component.getName();
		String componentType = component.getClassStringPretty();
		String componentTypeInfo = service.getComponentClassInfo(component);
		
		model.addAttribute("appName", appName);
		model.addAttribute("c", component);
		model.addAttribute("cId", componentId);
		model.addAttribute("cName", componentName);
		model.addAttribute("cType", componentType);
		model.addAttribute("infoFragment", componentTypeInfo);
		return "component";
    }
}
