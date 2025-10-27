package com.dsp_calculator.dsp_calculator.models.components;

public class Building extends DSPComponent {

	public Building(String _id, String _name, int _row, int _stack) { super(_id, _name, _row, _stack); }
	
	@Override
	public String toString() { return getClassStringPretty() + " " +  id + " : " + name; }
	@Override
	public String getClassStringPretty() { return "Bâtiment"; }
	
}
