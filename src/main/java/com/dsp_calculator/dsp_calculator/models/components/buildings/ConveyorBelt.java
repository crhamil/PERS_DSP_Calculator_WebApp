package com.dsp_calculator.dsp_calculator.models.components.buildings;

import com.dsp_calculator.dsp_calculator.models.components.Building;

public class ConveyorBelt extends Building {
	private int speed;
	
	public ConveyorBelt(String _id, String _name, int _row, int _stack, int _speed) {
		super(_id, _name, _row, _stack);
		speed = _speed;
	}
	
	public String getClassStringPretty() { return "Tapis roulant"; }
	@Override
	public String toString() { return "Tapis roulant " +  id + " : " + name; }
	public int getSpeed() { return speed; }
	
}
