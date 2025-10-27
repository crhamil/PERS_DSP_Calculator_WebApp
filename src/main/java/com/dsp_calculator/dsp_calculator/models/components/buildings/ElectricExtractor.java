package com.dsp_calculator.dsp_calculator.models.components.buildings;

public class ElectricExtractor extends Extractor {
	
	public ElectricExtractor(String _id, String _name, int _row, int _stack, float _speed, int _usage, int _drain) {
		super(_id, _name, _row, _stack, _speed, _usage, _drain);
	}
	
	@Override
	public String getClassStringPretty() { return super.getClassStringPretty() + " électrique"; }
	@Override
	public String toString() { return getClassStringPretty() + " " +  id + " : " + name; }

}
