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
import me.virizion.armorstandeditor.utils.Util;

public class NameItem extends GUIItem<ArmorStandGUI>
{

	public NameItem(ArmorStandGUI gui)
	{
		super(gui);
	}

	@Override
	public ItemStack getItemStack()
	{
		boolean anvilGUIEnabled = this.gui.getArmorStandEditor().isAnvilGUIEnabled();
		
		String name = this.gui.getArmorStand().getCustomName();
		
		ItemStack itemStack = new ItemStack(anvilGUIEnabled ? Material.NAME_TAG : Material.BARRIER);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName((anvilGUIEnabled ? ChatColor.GREEN : ChatColor.RED) + "Name");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current Value: " + ChatColor.RESET + (name == null || name.isEmpty() ? "Armor Stand" : name), "", anvilGUIEnabled ? ChatColor.GOLD + "Click to rename!" : ChatColor.RED + "Cannot rename because anvil GUI is not enabled!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		if (!this.gui.getArmorStandEditor().isAnvilGUIEnabled())
		{
			player.sendMessage(ChatColor.RED + "Anvil GUI is disabled because the server version is not supported!");
			return;
		}
		
		this.gui.getArmorStandEditor().createAnvilGUI(player, Util.reverseTranslateAlternateColorCodes('&', this.gui.getArmorStand().getCustomName() != null ? this.gui.getArmorStand().getCustomName() : "Input Name Here..."), name ->
		{
			this.gui.getArmorStand().setCustomName(ChatColor.translateAlternateColorCodes('&', name));
			this.gui.getArmorStandEditor().getEditingArmorStands().remove(player);
		}).open();
		
		this.gui.getArmorStandEditor().getEditingArmorStands().put(player, this.gui.getArmorStand());
	}

}
