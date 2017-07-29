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

public class GravityItem extends GUIItem<ArmorStandGUI>
{

	public GravityItem(ArmorStandGUI gui)
	{
		super(gui);
	}

	@Override
	public ItemStack getItemStack()
	{
		boolean hasGravity = this.gui.getArmorStand().hasGravity();
		
		ItemStack itemStack = new ItemStack(Material.SAND, 1, (short) (hasGravity ? 0 : 1));
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Gravity");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current Value: " + (hasGravity ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled"), "", ChatColor.GOLD + "Click to toggle!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		this.gui.getArmorStand().setGravity(!this.gui.getArmorStand().hasGravity());
		update();
	}

}
