package com.dsp_calculator.dsp_calculator.models.components;

import java.util.ArrayList;

public class Resource extends DSPComponent {
	private ArrayList<Building> minedBy = new ArrayList<>();
	
	public Resource(String _id, String _name, int _row, int _stack, ArrayList<Building> _miner) {
		super(_id, _name, _row, _stack);
		minedBy = _miner;
	}
	
	@Override
	public String toString() { return getClassStringPretty() + " " +  id + " : " + name; }
	@Override
	public String getClassStringPretty() { return "Ressource"; }
	public ArrayList<Building> getMiners() { return minedBy; }

}
