package me.virizion.armorstandeditor.gui.armorstand.rotation;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public enum RotatableType
{

	HEAD("Head")
	{
		@Override
		public EulerAngle getRotation(ArmorStand armorStand)
		{
			return armorStand.getHeadPose();
		}

		@Override
		public void setRotation(ArmorStand armorStand, EulerAngle eulerAngle)
		{
			armorStand.setHeadPose(eulerAngle);
		}
	},
	BODY("Body")
	{
		@Override
		public EulerAngle getRotation(ArmorStand armorStand)
		{
			return armorStand.getBodyPose();
		}

		@Override
		public void setRotation(ArmorStand armorStand, EulerAngle eulerAngle)
		{
			armorStand.setBodyPose(eulerAngle);
		}
	},
	LEFT_ARM("Left Arm")
	{
		@Override
		public EulerAngle getRotation(ArmorStand armorStand)
		{
			return armorStand.getLeftArmPose();
		}

		@Override
		public void setRotation(ArmorStand armorStand, EulerAngle eulerAngle)
		{
			armorStand.setLeftArmPose(eulerAngle);
		}
	},
	RIGHT_ARM("Right Arm")
	{
		@Override
		public EulerAngle getRotation(ArmorStand armorStand)
		{
			return armorStand.getRightArmPose();
		}

		@Override
		public void setRotation(ArmorStand armorStand, EulerAngle eulerAngle)
		{
			armorStand.setRightArmPose(eulerAngle);
		}
	},
	LEFT_LEG("Left Leg")
	{
		@Override
		public EulerAngle getRotation(ArmorStand armorStand)
		{
			return armorStand.getLeftLegPose();
		}

		@Override
		public void setRotation(ArmorStand armorStand, EulerAngle eulerAngle)
		{
			armorStand.setLeftLegPose(eulerAngle);
		}
	},
	RIGHT_LEG("Right Leg")
	{
		@Override
		public EulerAngle getRotation(ArmorStand armorStand)
		{
			return armorStand.getRightLegPose();
		}

		@Override
		public void setRotation(ArmorStand armorStand, EulerAngle eulerAngle)
		{
			armorStand.setRightLegPose(eulerAngle);
		}
	};

	private String simpleName;

	RotatableType(String simpleName)
	{
		this.simpleName = simpleName;
	}

	public abstract EulerAngle getRotation(ArmorStand armorStand);

	public abstract void setRotation(ArmorStand armorStand, EulerAngle eulerAngle);

	public String getSimpleName()
	{
		return this.simpleName;
	}

}
