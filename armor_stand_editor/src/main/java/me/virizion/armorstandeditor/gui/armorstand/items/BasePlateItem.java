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

public class BasePlateItem extends GUIItem<ArmorStandGUI>
{

	public BasePlateItem(ArmorStandGUI gui)
	{
		super(gui);
	}

	@Override
	public ItemStack getItemStack()
	{
		boolean hasBasePlate = this.gui.getArmorStand().hasBasePlate();
		
		ItemStack itemStack = new ItemStack(hasBasePlate ? Material.GOLD_PLATE : Material.STONE_PLATE);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Base Plate");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current Value: " + (hasBasePlate ? ChatColor.GREEN + "Visible" : ChatColor.RED + "Invisible"), "", ChatColor.GOLD + "Click to toggle!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		this.gui.getArmorStand().setBasePlate(!this.gui.getArmorStand().hasBasePlate());
		update();
	}

}
