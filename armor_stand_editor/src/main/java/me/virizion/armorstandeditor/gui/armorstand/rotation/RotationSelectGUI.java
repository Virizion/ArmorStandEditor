package me.virizion.armorstandeditor.gui.armorstand.rotation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import me.virizion.armorstandeditor.ArmorStandEditor;
import me.virizion.armorstandeditor.gui.GUI;
import me.virizion.armorstandeditor.gui.PreviousGUIItem;
import me.virizion.armorstandeditor.gui.armorstand.ArmorStandGUI;
import me.virizion.armorstandeditor.gui.armorstand.rotation.items.PitchYawRotationMenuButton;
import me.virizion.armorstandeditor.gui.armorstand.rotation.items.PlaceholderArmorItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.items.RotationItem;

public class RotationSelectGUI extends GUI
{

	private ArmorStandGUI armorStandGUI;

	public RotationSelectGUI(ArmorStandEditor armorStandEditor, Player player, ArmorStandGUI armorStandGUI)
	{
		super(armorStandEditor, player, Bukkit.createInventory(null, 54, "Armor Stand Rotation"));
		
		this.armorStandGUI = armorStandGUI;
	}

	public ArmorStandGUI getArmorStandGUI()
	{
		return this.armorStandGUI;
	}

	@Override
	public void open()
	{
		super.open();
		
		this.armorStandEditor.getEditingArmorStands().put(this.player, this.armorStandGUI.getArmorStand());
	}

	@Override
	protected void unregister()
	{
		super.unregister();
		
		this.armorStandEditor.getEditingArmorStands().remove(this.player);
	}

	@Override
	public void build()
	{
		setItem(11, new RotationItem(this, RotatableType.HEAD));
		setItem(19, new RotationItem(this, RotatableType.LEFT_ARM));
		setItem(20, new RotationItem(this, RotatableType.BODY));
		setItem(21, new RotationItem(this, RotatableType.RIGHT_ARM));
		setItem(29, new PlaceholderArmorItem(this, EquipmentSlot.LEGS));
		setItem(37, new RotationItem(this, RotatableType.LEFT_LEG));
		setItem(38, new PlaceholderArmorItem(this, EquipmentSlot.FEET));
		setItem(39, new RotationItem(this, RotatableType.RIGHT_LEG));
		
		setItem(24, new PitchYawRotationMenuButton(this));
		
		setItem(42, new PreviousGUIItem<>(this, this.armorStandGUI));
	}

}
