package me.virizion.armorstandeditor.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CloseItem<G extends GUI> extends GUIItem<G>
{

	public CloseItem(G gui)
	{
		super(gui);
	}

	@Override
	public ItemStack getItemStack()
	{
		ItemStack itemStack = new ItemStack(Material.BARRIER);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.RED + "Close");
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		this.gui.close();
		player.updateInventory();
	}

}
