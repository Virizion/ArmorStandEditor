package me.virizion.armorstandeditor.gui.armorstand.rotation.items;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.virizion.armorstandeditor.gui.GUIItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.PitchYaw;
import me.virizion.armorstandeditor.gui.armorstand.rotation.PitchYawRotationGUI;

public class PitchYawResetItem extends GUIItem<PitchYawRotationGUI>
{

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#", DecimalFormatSymbols.getInstance(Locale.US));

	private PitchYaw pitchYaw;

	public PitchYawResetItem(PitchYawRotationGUI gui, PitchYaw pitchYaw)
	{
		super(gui);
		
		this.pitchYaw = pitchYaw;
	}

	@Override
	public ItemStack getItemStack()
	{
		Location location = this.gui.getRotationSelectGUI().getArmorStandGUI().getArmorStand().getLocation();
		
		ItemStack itemStack = new ItemStack(Material.TNT);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Reset " + this.pitchYaw.getSimpleName() + " Rotation");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current Pitch: " + ChatColor.GREEN + DECIMAL_FORMAT.format(location.getPitch() + 90), ChatColor.AQUA + "Current Yaw: " + ChatColor.GREEN + DECIMAL_FORMAT.format(location.getYaw() + 180)));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		ArmorStand armorStand = this.gui.getRotationSelectGUI().getArmorStandGUI().getArmorStand();
		armorStand.teleport(this.pitchYaw.setLocationValue(armorStand.getLocation(), 0));
		
		this.gui.clear();
		this.gui.build();
	}

}
