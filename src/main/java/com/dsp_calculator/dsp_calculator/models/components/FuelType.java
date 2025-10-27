package com.dsp_calculator.dsp_calculator.models.components;


public enum FuelType {
	CHEMICAL("Chimique"), NUCLEAR("Nucléaire"), ANTIMATTER("Antimatière");
	
	final private String customName;
	
	FuelType(String _customName) { customName = _customName; }

	@Override
	public String toString() { return customName; }
}
