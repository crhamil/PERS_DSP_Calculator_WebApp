package com.dsp_calculator.dsp_calculator.models.components;

import java.util.ArrayList;

public class FuelResource extends Resource implements Fuel {
	private FuelType type; /* Origine du carburant */
	private int value; /* Rendement énergétique */

	public FuelResource(String _id, String _name, int _row, int _stack, FuelType _type, int _value, ArrayList<Building> _miners) {
		super(_id, _name, _row, _stack, _miners);
		type = _type;
		value = _value;
	}
	
	@Override
	public String toString() { return getClassStringPretty() + " " +  id + " : " + name; }
	@Override
	public String getClassStringPretty() { return "Carburant (ressource)"; }
	public FuelType getType() { return type; }
	public int getValue() { return value; }

}
