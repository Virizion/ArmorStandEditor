package me.virizion.armorstandeditor.nms.v1_8_R3;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.EquipmentSlot;

import me.virizion.armorstandeditor.nms.DisabledSlots;
import net.minecraft.server.v1_8_R3.EntityArmorStand;

public class DisabledSlotsImpl implements DisabledSlots
{

	@Override
	public Map<EquipmentSlot, Collection<InteractionType>> getDisabledSlots(ArmorStand armorStand)
	{
		Map<EquipmentSlot, Collection<InteractionType>> disabledSlots = new HashMap<>();
		
		try
		{
			Field disabledSlotsField = EntityArmorStand.class.getDeclaredField("bi");
			disabledSlotsField.setAccessible(true);
			
			int mask = disabledSlotsField.getInt(((CraftArmorStand) armorStand).getHandle());
			
			Arrays.stream(EquipmentSlot.values()).forEach(equipmentSlot ->
			{
				disabledSlots.put(equipmentSlot, new HashSet<>());
				
				int maskedValue = equipmentSlot.ordinal();
				
				Arrays.stream(InteractionType.values()).forEach(interactionType ->
				{
					if ((mask & 1 << (maskedValue + interactionType.ordinal() * 8)) != 0)
					{
						disabledSlots.get(equipmentSlot).add(interactionType);
					}
				});
			});
		}
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		return disabledSlots;
	}

	@Override
	public void setDisabledSlots(ArmorStand armorStand, Map<EquipmentSlot, Collection<InteractionType>> equipmentSlots)
	{
		try
		{
			Field disabledSlotsField = EntityArmorStand.class.getDeclaredField("bi");
			disabledSlotsField.setAccessible(true);
			
			AtomicInteger mask = new AtomicInteger();
			
			Arrays.stream(EquipmentSlot.values()).forEach(equipmentSlot ->
			{
				int maskedValue = equipmentSlot.ordinal();
				
				Arrays.stream(InteractionType.values()).forEach(interactionType ->
				{
					mask.addAndGet(equipmentSlots.containsKey(equipmentSlot) && equipmentSlots.get(equipmentSlot).contains(interactionType) ? 1 << (maskedValue + interactionType.ordinal() * 8) : 0);
				});
			});
			
			disabledSlotsField.setInt(((CraftArmorStand) armorStand).getHandle(), mask.get());
		}
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean isSlotDisabled(ArmorStand armorStand, EquipmentSlot equipmentSlot, InteractionType interactionType)
	{
		return getDisabledSlots(armorStand).get(equipmentSlot).contains(interactionType);
	}

	@Override
	public void setSlotDisabled(ArmorStand armorStand, EquipmentSlot equipmentSlot, InteractionType interactionType, boolean disabled)
	{
		Map<EquipmentSlot, Collection<InteractionType>> disabledSlots = getDisabledSlots(armorStand);
		
		if (disabled)
		{
			disabledSlots.get(equipmentSlot).add(interactionType);
		}
		else
		{
			disabledSlots.get(equipmentSlot).remove(interactionType);
		}
		
		setDisabledSlots(armorStand, disabledSlots);
	}

}
