package com.dsp_calculator.dsp_calculator.models.components.buildings;

public class NonElectricExtractor extends Extractor  {

	public NonElectricExtractor(String _id, String _name, int _row, int _stack, float _speed, int _modules) {
		super(_id, _name, _row, _stack, _speed, 0, 0, _modules);
	}
	
	@Override
	public String getClassStringPretty() { return super.getClassStringPretty() + " non-électrique"; }
	@Override
	public String toString() { return getClassStringPretty() + " " +  id + " : " + name; }

}
