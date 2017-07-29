package me.virizion.armorstandeditor.gui.armorstand.rotation;

public enum IncrementType
{

	SMALL_INCREMENT(1),
	LARGE_INCREMENT(15),
	SMALL_DECREMENT(-1),
	LARGE_DECREMENT(-15);

	private double increment;

	IncrementType(double increment)
	{
		this.increment = increment;
	}

	public double getIncrement()
	{
		return this.increment;
	}

}
