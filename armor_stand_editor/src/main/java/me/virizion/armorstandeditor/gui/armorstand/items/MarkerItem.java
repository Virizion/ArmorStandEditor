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

public class MarkerItem extends GUIItem<ArmorStandGUI>
{

	public MarkerItem(ArmorStandGUI gui)
	{
		super(gui);
	}

	@Override
	public ItemStack getItemStack()
	{
		boolean isMarker = this.gui.getArmorStand().isMarker();
		
		ItemStack itemStack = new ItemStack(isMarker ? Material.REDSTONE_TORCH_ON : Material.LEVER);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Marker");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current Value: " + (isMarker ? ChatColor.GREEN + "True" : ChatColor.RED + "False"), "", ChatColor.GOLD + "Click to toggle!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		this.gui.getArmorStand().setMarker(!this.gui.getArmorStand().isMarker());
		update();
	}

}
