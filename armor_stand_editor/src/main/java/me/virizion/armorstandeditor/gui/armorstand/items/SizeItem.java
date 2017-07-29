package me.virizion.armorstandeditor.gui.armorstand.items;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.virizion.armorstandeditor.gui.GUIItem;
import me.virizion.armorstandeditor.gui.armorstand.ArmorStandGUI;

public class SizeItem extends GUIItem<ArmorStandGUI>
{

	public SizeItem(ArmorStandGUI gui)
	{
		super(gui);
	}

	@Override
	public ItemStack getItemStack()
	{
		boolean isSmall = this.gui.getArmorStand().isSmall();
		
		ItemStack itemStack = new ItemStack(isSmall ? Material.DAYLIGHT_DETECTOR : Material.REDSTONE_LAMP_OFF);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Size");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current Value: " + (isSmall ? ChatColor.RED + "Small" : ChatColor.GREEN + "Large"), "", ChatColor.GOLD + "Click to toggle!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		this.gui.getArmorStand().setSmall(!this.gui.getArmorStand().isSmall());
		update();
	}

}
