package com.dsp_calculator.dsp_calculator.components;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.dsp_calculator.dsp_calculator.models.components.DSPComponent;
import com.dsp_calculator.dsp_calculator.models.components.FuelComponent;
import com.dsp_calculator.dsp_calculator.models.components.FuelResource;
import com.dsp_calculator.dsp_calculator.models.components.Resource;
import com.dsp_calculator.dsp_calculator.utilities.Game;

@Service
public class ComponentService {
	public HashMap<String, DSPComponent> getAllComponents() { return Game.getInstance().getComponents(); }
	public int getComponentCount() { return getAllComponents().size(); }
	public DSPComponent getComponent(String id) { return Game.getInstance().getComponent(id); }
	public String getComponentClassInfo(DSPComponent c) {
		if (c instanceof FuelResource) return "fuel-resource";
		else if (c instanceof FuelComponent) return "fuel-component";
		else if (c instanceof Resource)	return "resource";
		else return null; /* Nothing for DSPComponent, since there's no additional info to display */
	}
}
