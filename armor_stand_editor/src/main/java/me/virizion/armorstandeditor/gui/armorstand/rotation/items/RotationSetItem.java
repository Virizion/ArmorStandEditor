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
import me.virizion.armorstandeditor.gui.armorstand.rotation.IncrementType;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotatableAxis;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotatableType;
import me.virizion.armorstandeditor.gui.armorstand.rotation.RotationSetGUI;

public class RotationSetItem extends GUIItem<RotationSetGUI>
{

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#", DecimalFormatSymbols.getInstance(Locale.US));
	
	private RotatableType rotatableType;
	private RotatableAxis rotatableAxis;
	private IncrementType incrementType;

	public RotationSetItem(RotationSetGUI gui, RotatableType rotatableType, RotatableAxis rotatableAxis, IncrementType incrementType)
	{
		super(gui);
		
		this.rotatableType = rotatableType;
		this.rotatableAxis = rotatableAxis;
		this.incrementType = incrementType;
	}

	@Override
	public ItemStack getItemStack()
	{
		EulerAngle eulerAngle = this.rotatableType.getRotation(this.gui.getRotationSelectGUI().getArmorStandGUI().getArmorStand());
		
		ItemStack itemStack = new ItemStack(Material.STAINED_CLAY, 1, (short) (this.incrementType == IncrementType.SMALL_INCREMENT ? 5 : (this.incrementType == IncrementType.LARGE_INCREMENT ? 13 : (this.incrementType == IncrementType.SMALL_DECREMENT ? 3 : 11))));
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.values());
		itemMeta.setDisplayName(ChatColor.GREEN + this.rotatableAxis.getSimpleName() + " Rotation " + ((this.incrementType.getIncrement() >= 0 ? ChatColor.GREEN + "+ " : ChatColor.RED + "- ") + DECIMAL_FORMAT.format(Math.abs(this.incrementType.getIncrement()))));
		itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Current X: " + ChatColor.GREEN + DECIMAL_FORMAT.format(Math.toDegrees(eulerAngle.getX()) + 180), ChatColor.AQUA + "Current Y: " + ChatColor.GREEN + DECIMAL_FORMAT.format(Math.toDegrees(eulerAngle.getY()) + 180), ChatColor.AQUA + "Current Z: " + ChatColor.GREEN + DECIMAL_FORMAT.format(Math.toDegrees(eulerAngle.getZ()) + 180)));
		
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}

	@Override
	public void click(Player player, ClickType clickType)
	{
		ArmorStand armorStand = this.gui.getRotationSelectGUI().getArmorStandGUI().getArmorStand();
		EulerAngle eulerAngle = this.rotatableType.getRotation(armorStand);
		this.rotatableType.setRotation(armorStand, this.rotatableAxis.setEulerAngleValue(eulerAngle, Math.toRadians((Math.toDegrees(this.rotatableAxis.getEulerAngleValue(eulerAngle)) + 180 + this.incrementType.getIncrement() + 360) % 360 - 180)));
		
		this.gui.clear();
		this.gui.build();
	}

}
