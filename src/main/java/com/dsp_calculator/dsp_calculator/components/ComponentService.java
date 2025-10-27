package com.dsp_calculator.dsp_calculator.components;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.dsp_calculator.dsp_calculator.models.components.DSPComponent;
import com.dsp_calculator.dsp_calculator.utilities.Game;

@Service
public class ComponentService {
	public HashMap<String, DSPComponent> getAllComponents() { return Game.getInstance().getComponents(); }
	public int getComponentCount() { return getAllComponents().size(); }
}
