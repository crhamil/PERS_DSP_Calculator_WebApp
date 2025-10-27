package com.dsp_calculator.dsp_calculator.models.components.buildings;

public abstract class PowerPlant extends Factory {
	private int value;
	
	public PowerPlant(String _id, String _name, int _row, int _stack, float _speed, int _value) {
		super(_id, _name, _row, _stack, _speed, 0, 0, 0);
		value = _value;
	}
	
	@Override
	public abstract String toString();
	public int getValue() { return value; }
	public String getClassStringPretty() { return "Centrale électrique"; }

}
