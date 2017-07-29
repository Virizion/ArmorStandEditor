package me.virizion.armorstandeditor.gui.armorstand.rotation.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.virizion.armorstandeditor.gui.GUIItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotationSelectGUI;
import me.virizion.armorstandeditor.utils.Util;

public class PlaceholderArmorItem extends GUIItem<RotationSelectGUI>
{

	private EquipmentSlot equipmentSlot;

	public PlaceholderArmorItem(RotationSelectGUI gui, EquipmentSlot equipmentSlot)
	{
		super(gui);
		
		this.equipmentSlot = equipmentSlot;
	}

	@Override
	public ItemStack getItemStack()
	{
		ItemStack itemStack = this.equipmentSlot == EquipmentSlot.HAND ? this.gui.getArmorStandGUI().getArmorStand().getItemInHand() : (this.gui.getArmorStandEditor().isOffHandSupported() && this.equipmentSlot == EquipmentSlot.OFF_HAND ? this.gui.getArmorStandGUI().getArmorStand().getEquipment().getItemInOffHand() : this.gui.getArmorStandGUI().getArmorStand().getEquipment().getArmorContents()[this.equipmentSlot.ordinal() - (this.gui.getArmorStandEditor().isOffHandSupported() ? 2 : 1)]);
		
		if (itemStack == null || itemStack.getType() == Material.AIR)
		{
			itemStack = new ItemStack(Material.BARRIER);
		}
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + Util.getEquipmentSlotSimpleName(this.equipmentSlot));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType) {}

}
