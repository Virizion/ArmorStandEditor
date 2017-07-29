package me.virizion.armorstandeditor.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PreviousGUIItem<G extends GUI> extends GUIItem<G>
{

	private GUI previousGUI;

	public PreviousGUIItem(G gui, GUI previousGUI)
	{
		super(gui);
		
		this.previousGUI = previousGUI;
	}

	@Override
	public ItemStack getItemStack()
	{
		ItemStack itemStack = new ItemStack(Material.BED);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Previous Menu");
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		this.previousGUI.open();
	}

}
