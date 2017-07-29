package me.virizion.armorstandeditor.nms;

import java.util.Collection;
import java.util.Map;

import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.EquipmentSlot;

public interface DisabledSlots
{

	Map<EquipmentSlot, Collection<InteractionType>> getDisabledSlots(ArmorStand armorStand);

	void setDisabledSlots(ArmorStand armorStand, Map<EquipmentSlot, Collection<InteractionType>> equipmentSlots);

	boolean isSlotDisabled(ArmorStand armorStand, EquipmentSlot equipmentSlot, InteractionType interactionType);

	void setSlotDisabled(ArmorStand armorStand, EquipmentSlot equipmentSlot, InteractionType interactionType, boolean disabled);

	public enum InteractionType
	{
		REMOVE,
		REPLACE,
		PLACE
	}

}
