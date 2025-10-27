package com.dsp_calculator.dsp_calculator.models.components;

public class FuelComponent extends DSPComponent implements Fuel {
	private FuelType type; /* Origine du carburant */
	private int value; /* Rendement énergétique */

	public FuelComponent(String _id, String _name, int _row, int _stack, FuelType _type, int _value) {
		super(_id, _name, _row, _stack);
		type = _type;
		value = _value;
	}
	
	@Override
	public String toString() { return getClassStringPretty() + " " +  id + " : " + name; }
	@Override
	public String getClassStringPretty() { return "Carburant (composant)"; }
	public FuelType getType() { return type; }
	public int getValue() { return value; }

}
