package me.virizion.armorstandeditor.gui.armorstand.items;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.virizion.armorstandeditor.gui.GUIItem;
import me.virizion.armorstandeditor.gui.armorstand.ArmorStandGUI;
import me.virizion.armorstandeditor.nms.DisabledSlots.InteractionType;
import me.virizion.armorstandeditor.utils.Util;

public class ArmorItem extends GUIItem<ArmorStandGUI>
{

	private EquipmentSlot equipmentSlot;

	public ArmorItem(ArmorStandGUI gui, EquipmentSlot equipmentSlot)
	{
		super(gui);
		
		this.equipmentSlot = equipmentSlot;
	}

	@Override
	public ItemStack getItemStack()
	{
		boolean equipmentLock = false;
		
		if (this.gui.getArmorStandEditor().isEquipmentSlotLockingEnabled())
		{
			equipmentLock = !this.gui.getArmorStandEditor().getDisabledSlots().getDisabledSlots(this.gui.getArmorStand()).get(this.equipmentSlot).isEmpty();
		}
		
		ItemStack itemStack = this.equipmentSlot == EquipmentSlot.HAND ? this.gui.getArmorStand().getItemInHand() : (this.gui.getArmorStandEditor().isOffHandSupported() && this.equipmentSlot == EquipmentSlot.OFF_HAND ? this.gui.getArmorStand().getEquipment().getItemInOffHand() : this.gui.getArmorStand().getEquipment().getArmorContents()[this.equipmentSlot.ordinal() - (this.gui.getArmorStandEditor().isOffHandSupported() ? 2 : 1)]);
		
		if (itemStack == null || itemStack.getType() == Material.AIR)
		{
			itemStack = new ItemStack(Material.BARRIER);
		}
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + Util.getEquipmentSlotSimpleName(this.equipmentSlot));
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Equipment Lock: " + (this.gui.getArmorStandEditor().isEquipmentSlotLockingEnabled() ? (equipmentLock ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled") : ChatColor.RED + "Unavailable"), "", ChatColor.GOLD + "Click with item to put it in this slot!", ChatColor.GOLD + "Shift click to toggle equipment lock!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		ItemStack newArmor = player.getItemOnCursor();
		
		if (clickType == ClickType.SHIFT_LEFT || clickType == ClickType.SHIFT_RIGHT)
		{
			if (this.gui.getArmorStandEditor().isEquipmentSlotLockingEnabled())
			{
				Map<EquipmentSlot, Collection<InteractionType>> disabledSlots = this.gui.getArmorStandEditor().getDisabledSlots().getDisabledSlots(this.gui.getArmorStand());
				
				if (disabledSlots.get(this.equipmentSlot).isEmpty())
				{
					disabledSlots.get(this.equipmentSlot).addAll(Arrays.asList(InteractionType.values()));
					this.gui.getArmorStandEditor().getDisabledSlots().setDisabledSlots(this.gui.getArmorStand(), disabledSlots);
				}
				else
				{
					disabledSlots.get(this.equipmentSlot).clear();
					this.gui.getArmorStandEditor().getDisabledSlots().setDisabledSlots(this.gui.getArmorStand(), disabledSlots);
				}
				
				update();
			}
			else
			{
				player.sendMessage(ChatColor.RED + "Equipment slot locking is disabled because the server version is not supported!");
			}
		}
		else
		{
			switch (this.equipmentSlot)
			{
				case HEAD:
					player.setItemOnCursor(this.gui.getArmorStand().getHelmet());
					this.gui.getArmorStand().setHelmet(newArmor);
					break;
				case CHEST:
					player.setItemOnCursor(this.gui.getArmorStand().getChestplate());
					this.gui.getArmorStand().setChestplate(newArmor);
					break;
				case HAND:
					player.setItemOnCursor(this.gui.getArmorStand().getItemInHand());
					this.gui.getArmorStand().setItemInHand(newArmor);
					break;
				case OFF_HAND:
					player.setItemOnCursor(this.gui.getArmorStand().getEquipment().getItemInOffHand());
					this.gui.getArmorStand().getEquipment().setItemInOffHand(newArmor);
					break;
				case LEGS:
					player.setItemOnCursor(this.gui.getArmorStand().getLeggings());
					this.gui.getArmorStand().setLeggings(newArmor);
					break;
				case FEET:
					player.setItemOnCursor(this.gui.getArmorStand().getBoots());
					this.gui.getArmorStand().setBoots(newArmor);
					break;
			}
			
			update();
		}
	}

}
