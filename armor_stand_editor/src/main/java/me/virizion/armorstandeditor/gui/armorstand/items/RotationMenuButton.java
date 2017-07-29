package me.virizion.armorstandeditor.gui.armorstand.items;

import java.util.Collections;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.virizion.armorstandeditor.gui.GUIItem;
import me.virizion.armorstandeditor.gui.armorstand.ArmorStandGUI;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotationSelectGUI;

public class RotationMenuButton extends GUIItem<ArmorStandGUI>
{

	public RotationMenuButton(ArmorStandGUI gui)
	{
		super(gui);
	}

	@Override
	public ItemStack getItemStack()
	{
		ItemStack itemStack = new ItemStack(Material.STRING);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Rotation Menu");
		itemMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Click to open!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		new RotationSelectGUI(this.gui.getArmorStandEditor(), player, this.gui).open();
	}

}
