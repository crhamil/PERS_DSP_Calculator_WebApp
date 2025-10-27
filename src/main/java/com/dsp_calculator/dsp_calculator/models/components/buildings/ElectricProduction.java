package com.dsp_calculator.dsp_calculator.models.components.buildings;

public class ElectricProduction extends PowerPlant {

	public ElectricProduction(String _id, String _name, int _row, int _stack, float _speed, int _value) {
		super(_id, _name, _row, _stack, _speed, _value);
	}
	
	@Override
	public String getClassStringPretty() { return super.getClassStringPretty() + " à électricité"; }
	@Override
	public String toString() { return getClassStringPretty() + " " +  id + " : " + name; }

}
