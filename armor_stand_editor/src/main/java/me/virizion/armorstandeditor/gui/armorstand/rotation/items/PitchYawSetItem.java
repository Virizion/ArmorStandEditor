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
import me.virizion.armorstandeditor.gui.armorstand.rotation.IncrementType;
import me.virizion.armorstandeditor.gui.armorstand.rotation.PitchYaw;
import me.virizion.armorstandeditor.gui.armorstand.rotation.PitchYawRotationGUI;

public class PitchYawSetItem extends GUIItem<PitchYawRotationGUI>
{

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#", DecimalFormatSymbols.getInstance(Locale.US));
	
	private PitchYaw pitchYaw;
	private IncrementType incrementType;

	public PitchYawSetItem(PitchYawRotationGUI gui, PitchYaw pitchYaw, IncrementType incrementType)
	{
		super(gui);
		
		this.pitchYaw = pitchYaw;
		this.incrementType = incrementType;
	}

	@Override
	public ItemStack getItemStack()
	{
		Location location = this.gui.getRotationSelectGUI().getArmorStandGUI().getArmorStand().getLocation();
		
		ItemStack itemStack = new ItemStack(Material.STAINED_CLAY, 1, (short) (this.incrementType == IncrementType.SMALL_INCREMENT ? 5 : (this.incrementType == IncrementType.LARGE_INCREMENT ? 13 : (this.incrementType == IncrementType.SMALL_DECREMENT ? 3 : 11))));
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + this.pitchYaw.getSimpleName() + " Rotation " + ((this.incrementType.getIncrement() >= 0 ? ChatColor.GREEN + "+ " : ChatColor.RED + "- ") + DECIMAL_FORMAT.format(Math.abs(this.incrementType.getIncrement()))));
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current Pitch: " + ChatColor.GREEN + DECIMAL_FORMAT.format(location.getPitch() + 90), ChatColor.AQUA + "Current Yaw: " + ChatColor.GREEN + DECIMAL_FORMAT.format(location.getYaw() + 180)));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		ArmorStand armorStand = this.gui.getRotationSelectGUI().getArmorStandGUI().getArmorStand();
		Location location = armorStand.getLocation();
		armorStand.teleport(this.pitchYaw.setLocationValue(location, (float) ((this.pitchYaw.getLocationValue(location) + (this.pitchYaw == PitchYaw.PITCH ? 90 : 180) + this.incrementType.getIncrement() + (this.pitchYaw == PitchYaw.PITCH ? 180 : 360)) % (this.pitchYaw == PitchYaw.PITCH ? 180 : 360) - (this.pitchYaw == PitchYaw.PITCH ? 90 : 180))));
		
		this.gui.clear();
		this.gui.build();
	}

}
