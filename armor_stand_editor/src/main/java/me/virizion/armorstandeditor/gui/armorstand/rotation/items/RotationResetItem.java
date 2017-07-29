package me.virizion.armorstandeditor.gui.armorstand.rotation.items;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;

import me.virizion.armorstandeditor.gui.GUIItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotatableAxis;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotatableType;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotationSetGUI;

public class RotationResetItem extends GUIItem<RotationSetGUI>
{

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#", DecimalFormatSymbols.getInstance(Locale.US));

	private RotatableType rotatableType;
	private RotatableAxis rotatableAxis;

	public RotationResetItem(RotationSetGUI gui, RotatableType rotatableType, RotatableAxis rotatableAxis)
	{
		super(gui);
		
		this.rotatableType = rotatableType;
		this.rotatableAxis = rotatableAxis;
	}

	@Override
	public ItemStack getItemStack()
	{
		EulerAngle eulerAngle = this.rotatableType.getRotation(this.gui.getRotationSelectGUI().getArmorStandGUI().getArmorStand());
		
		ItemStack itemStack = new ItemStack(Material.TNT);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + "Reset " + this.rotatableAxis.getSimpleName() + " Rotation");
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current X: " + ChatColor.GREEN + DECIMAL_FORMAT.format(Math.toDegrees(eulerAngle.getX()) + 180), ChatColor.AQUA + "Current Y: " + ChatColor.GREEN + DECIMAL_FORMAT.format(Math.toDegrees(eulerAngle.getY()) + 180), ChatColor.AQUA + "Current Z: " + ChatColor.GREEN + DECIMAL_FORMAT.format(Math.toDegrees(eulerAngle.getZ()) + 180)));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		ArmorStand armorStand = this.gui.getRotationSelectGUI().getArmorStandGUI().getArmorStand();
		this.rotatableType.setRotation(armorStand, this.rotatableAxis.setEulerAngleValue(this.rotatableType.getRotation(armorStand), 0));
		
		this.gui.clear();
		this.gui.build();
	}

}
