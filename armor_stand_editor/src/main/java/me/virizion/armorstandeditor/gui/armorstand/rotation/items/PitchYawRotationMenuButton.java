package me.virizion.armorstandeditor.gui.armorstand.rotation.items;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.virizion.armorstandeditor.gui.GUIItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.PitchYawRotationGUI;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotationSelectGUI;

public class PitchYawRotationMenuButton extends GUIItem<RotationSelectGUI>
{

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#", DecimalFormatSymbols.getInstance(Locale.US));

	public PitchYawRotationMenuButton(RotationSelectGUI gui)
	{
		super(gui);
	}

	@Override
	public ItemStack getItemStack()
	{
		Location location = this.gui.getArmorStandGUI().getArmorStand().getLocation();
		
		ItemStack itemStack = new ItemStack(Material.STRING);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Pitch & Yaw Rotation");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current Pitch: " + ChatColor.GREEN + DECIMAL_FORMAT.format(location.getPitch() + 90), ChatColor.AQUA + "Current Yaw: " + ChatColor.GREEN + DECIMAL_FORMAT.format(location.getYaw() + 180), "", ChatColor.GOLD + "Click to modify!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		new PitchYawRotationGUI(this.gui.getArmorStandEditor(), player, this.gui).open();
	}

}
