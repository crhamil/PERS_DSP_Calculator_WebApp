package com.dsp_calculator.dsp_calculator.models.components.buildings;

public abstract class Extractor extends Factory {

	public Extractor(String _id, String _name, int _row, int _stack, float _speed, int _usage, int _drain, int _modules) {
		super(_id, _name, _row, _stack, _speed, _usage, _drain, _modules);
	}
	
	@Override
	public abstract String toString();
	public String getClassStringPretty() { return "Extracteur"; }
}
