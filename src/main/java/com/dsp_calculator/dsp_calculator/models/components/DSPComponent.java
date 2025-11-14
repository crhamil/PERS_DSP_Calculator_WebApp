package com.dsp_calculator.dsp_calculator.models.components;

public class DSPComponent implements Comparable<DSPComponent> {
	
	protected String id; /* unique ID */
	protected String name; /* unique name */
	protected int row; /* row */
	protected int stack; /* stack */
	
	public DSPComponent(String _id, String _name, int _row, int _stack) {
		id = _id;
		name = _name;
		row = _row;
		stack = _stack;
	}
	
	@Override
	public String toString() { return getClassStringPretty() + " " + id + " : " + name; }
	public String getClassStringPretty() { return "Composant"; }
	
	/* Implémente Comparable par facilité (dans la majorité des cas, on voudra getName croissant)
	 * Un comparateur personnalisé sera utilisé en cas de besoin */
	@Override
	public int compareTo(DSPComponent c) { return name.compareTo(c.getName()); }
	
	public String getId() { return id; }
	public String getName() { return name; }
	
}
