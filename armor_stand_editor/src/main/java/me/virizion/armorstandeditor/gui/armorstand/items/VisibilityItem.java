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

public class VisibilityItem extends GUIItem<ArmorStandGUI>
{

	public VisibilityItem(ArmorStandGUI gui)
	{
		super(gui);
	}

	@Override
	public ItemStack getItemStack()
	{
		boolean isVisible = this.gui.getArmorStand().isVisible();
		
		ItemStack itemStack = new ItemStack(isVisible ? Material.EXP_BOTTLE : Material.GLASS_BOTTLE);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Visibility");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current Value: " + (isVisible ? ChatColor.GREEN + "Visible" : ChatColor.RED + "Invisible"), "", ChatColor.GOLD + "Click to toggle!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		this.gui.getArmorStand().setVisible(!this.gui.getArmorStand().isVisible());
		update();
	}

}
