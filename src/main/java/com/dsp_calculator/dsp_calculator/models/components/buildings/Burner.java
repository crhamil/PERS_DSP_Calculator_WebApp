package com.dsp_calculator.dsp_calculator.models.components.buildings;

import java.util.ArrayList;

import com.dsp_calculator.dsp_calculator.models.components.Fuel;
import com.dsp_calculator.dsp_calculator.models.components.FuelType;
import com.dsp_calculator.dsp_calculator.utilities.FuelTypeException;
import com.dsp_calculator.dsp_calculator.utilities.Game;

public class Burner extends PowerPlant {
	private FuelType category;

	public Burner(String _id, String _name, int _row, int _stack, float _speed, int _value, FuelType _category) throws FuelTypeException {
		super(_id, _name, _row, _stack, _speed, _value);
		category = _category;
		if (_category == null) throw new FuelTypeException("La centrale à carburant " + id + " ne possède pas de type de carburant");
	}
	
	@Override
	public String getClassStringPretty() { return super.getClassStringPretty() + " à carburant"; }
	@Override
	public String toString() { return getClassStringPretty() + " " +  id + " : " + name; }
	public FuelType getCategory() { return category; }
	public ArrayList<Fuel> getPossibleFuels() {
		Game game = Game.getInstance();
		ArrayList<Fuel> gameFuels = game.getList(game.getFuels().values(), null);
		ArrayList<Fuel> bFuels = new ArrayList<>();
		for (Fuel f : gameFuels) {
			if (f.getType().equals(category))
				bFuels.add(f);
		}
		return bFuels;
	}

}
