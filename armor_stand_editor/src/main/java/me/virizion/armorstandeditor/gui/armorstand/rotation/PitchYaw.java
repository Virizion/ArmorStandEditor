package me.virizion.armorstandeditor.gui.armorstand.rotation;

import org.bukkit.Location;

public enum PitchYaw
{

	PITCH("Pitch")
	{
		@Override
		public float getLocationValue(Location location)
		{
			return location.getPitch();
		}

		@Override
		public Location setLocationValue(Location location, float value)
		{
			location.setPitch(value);
			return location;
		}
	},
	YAW("Yaw")
	{
		@Override
		public float getLocationValue(Location location)
		{
			return location.getYaw();
		}

		@Override
		public Location setLocationValue(Location location, float value)
		{
			location.setYaw(value);
			return location;
		}
	};

	private String simpleName;

	PitchYaw(String simpleName)
	{
		this.simpleName = simpleName;
	}

	public abstract float getLocationValue(Location location);

	public abstract Location setLocationValue(Location location, float value);

	public String getSimpleName()
	{
		return this.simpleName;
	}

}
