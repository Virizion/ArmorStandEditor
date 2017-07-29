package me.virizion.armorstandeditor.utils;

import org.bukkit.ChatColor;
import org.bukkit.inventory.EquipmentSlot;

public final class Util
{

	private Util() {}

	public static String getEquipmentSlotSimpleName(EquipmentSlot equipmentSlot)
	{
		switch (equipmentSlot)
		{
			case HEAD:
				return "Helmet";
			case CHEST:
				return "Chestplate";
			case HAND:
				return "Main Hand";
			case OFF_HAND:
				return "Off Hand";
			case LEGS:
				return "Leggings";
			case FEET:
				return "Boots";
			default:
				return "Unknown";
		}
	}

	public static String reverseTranslateAlternateColorCodes(char alternateColorCode, String textToReverseTranslate)
	{
		char[] chars = textToReverseTranslate.toCharArray();
		
		for (int i = 0; i < chars.length - 1; i++)
		{
			if (chars[i] == ChatColor.COLOR_CHAR && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(chars[i + 1]) > -1)
			{
				chars[i] = alternateColorCode;
				chars[i + 1] = Character.toLowerCase(chars[i + 1]);
			}
		}
		
		return new String(chars);
	}

}
