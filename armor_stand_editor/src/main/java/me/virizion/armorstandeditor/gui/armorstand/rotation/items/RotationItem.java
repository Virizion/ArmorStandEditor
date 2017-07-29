package me.virizion.armorstandeditor.gui.armorstand.rotation.items;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;

import me.virizion.armorstandeditor.gui.GUIItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotatableType;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotationSelectGUI;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotationSetGUI;

public class RotationItem extends GUIItem<RotationSelectGUI>
{

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#", DecimalFormatSymbols.getInstance(Locale.US));

	private RotatableType rotatableType;

	public RotationItem(RotationSelectGUI gui, RotatableType rotatableType)
	{
		super(gui);
		
		this.rotatableType = rotatableType;
	}

	@Override
	public ItemStack getItemStack()
	{
		EulerAngle eulerAngle = this.rotatableType.getRotation(this.gui.getArmorStandGUI().getArmorStand());
		
		ItemStack itemStack = this.rotatableType == RotatableType.HEAD ? this.gui.getArmorStandGUI().getArmorStand().getHelmet() : (this.rotatableType == RotatableType.BODY ? this.gui.getArmorStandGUI().getArmorStand().getChestplate() : (this.rotatableType == RotatableType.LEFT_ARM ? (this.gui.getArmorStandEditor().isOffHandSupported() ? this.gui.getArmorStandGUI().getArmorStand().getEquipment().getItemInOffHand() : new ItemStack(Material.STICK)) : (this.rotatableType == RotatableType.RIGHT_ARM ? this.gui.getArmorStandGUI().getArmorStand().getItemInHand() : new ItemStack(Material.STICK))));
		
		if (itemStack == null || itemStack.getType() == Material.AIR)
		{
			itemStack = new ItemStack(Material.BARRIER);
		}
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + this.rotatableType.getSimpleName() + " Rotation");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current X: " + ChatColor.GREEN + DECIMAL_FORMAT.format(Math.toDegrees(eulerAngle.getX()) + 180), ChatColor.AQUA + "Current Y: " + ChatColor.GREEN + DECIMAL_FORMAT.format(Math.toDegrees(eulerAngle.getY()) + 180), ChatColor.AQUA + "Current Z: " + ChatColor.GREEN + DECIMAL_FORMAT.format(Math.toDegrees(eulerAngle.getZ()) + 180), "", ChatColor.GOLD + "Click to modify!"));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		new RotationSetGUI(this.gui.getArmorStandEditor(), player, this.gui, this.rotatableType).open();
	}

}
