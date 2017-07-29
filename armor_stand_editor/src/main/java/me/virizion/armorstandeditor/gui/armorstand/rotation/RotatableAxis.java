package me.virizion.armorstandeditor.gui.armorstand.rotation;

import org.bukkit.util.EulerAngle;

public enum RotatableAxis
{

	X("X")
	{
		@Override
		public double getEulerAngleValue(EulerAngle eulerAngle)
		{
			return eulerAngle.getX();
		}

		@Override
		public EulerAngle setEulerAngleValue(EulerAngle eulerAngle, double value)
		{
			return eulerAngle.setX(value);
		}
	},
	Y("Y")
	{
		@Override
		public double getEulerAngleValue(EulerAngle eulerAngle)
		{
			return eulerAngle.getY();
		}

		@Override
		public EulerAngle setEulerAngleValue(EulerAngle eulerAngle, double value)
		{
			return eulerAngle.setY(value);
		}
	},
	Z("Z")
	{
		@Override
		public double getEulerAngleValue(EulerAngle eulerAngle)
		{
			return eulerAngle.getZ();
		}

		@Override
		public EulerAngle setEulerAngleValue(EulerAngle eulerAngle, double value)
		{
			return eulerAngle.setZ(value);
		}
	};

	private String simpleName;

	RotatableAxis(String simpleName)
	{
		this.simpleName = simpleName;
	}

	public abstract double getEulerAngleValue(EulerAngle eulerAngle);

	public abstract EulerAngle setEulerAngleValue(EulerAngle eulerAngle, double value);

	public String getSimpleName()
	{
		return this.simpleName;
	}

}
