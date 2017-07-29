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

public class ArmsItem extends GUIItem<ArmorStandGUI>
{

	public ArmsItem(ArmorStandGUI gui)
	{
		super(gui);
	}

	@Override
	public ItemStack getItemStack()
	{
		boolean hasArms = this.gui.getArmorStand().hasArms();
		
		ItemStack itemStack = new ItemStack(hasArms ? Material.BLAZE_ROD : Material.STICK);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Arms");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current Value: " + (hasArms ? ChatColor.GREEN + "Visible" : ChatColor.RED + "Invisible"), "", ChatColor.GOLD + "Click to toggle!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		this.gui.getArmorStand().setArms(!this.gui.getArmorStand().hasArms());
		update();
	}

}
