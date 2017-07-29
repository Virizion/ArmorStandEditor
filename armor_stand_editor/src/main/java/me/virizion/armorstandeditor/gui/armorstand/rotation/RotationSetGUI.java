package me.virizion.armorstandeditor.gui.armorstand.rotation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.virizion.armorstandeditor.ArmorStandEditor;
import me.virizion.armorstandeditor.gui.GUI;
import me.virizion.armorstandeditor.gui.PreviousGUIItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.items.RotationResetItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.items.RotationSetItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.items.RotationTypeItem;

public class RotationSetGUI extends GUI
{

	private RotationSelectGUI rotationSelectGUI;
	private RotatableType rotatableType;

	public RotationSetGUI(ArmorStandEditor armorStandEditor, Player player, RotationSelectGUI rotationSelectGUI, RotatableType rotatableType)
	{
		super(armorStandEditor, player, Bukkit.createInventory(null, 45, "Set " + rotatableType.getSimpleName() + " Rotation"));
		
		this.rotationSelectGUI = rotationSelectGUI;
		this.rotatableType = rotatableType;
	}

	public RotationSelectGUI getRotationSelectGUI()
	{
		return this.rotationSelectGUI;
	}

	public RotatableType getRotatableType()
	{
		return this.rotatableType;
	}

	@Override
	public void open()
	{
		super.open();
		
		this.armorStandEditor.getEditingArmorStands().put(this.player, this.rotationSelectGUI.getArmorStandGUI().getArmorStand());
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
		setItem(16, new RotationTypeItem(this, this.rotatableType));
		
		setItem(1, new RotationSetItem(this, this.rotatableType, RotatableAxis.X, IncrementType.LARGE_INCREMENT));
		setItem(3, new RotationSetItem(this, this.rotatableType, RotatableAxis.Y, IncrementType.LARGE_INCREMENT));
		setItem(5, new RotationSetItem(this, this.rotatableType, RotatableAxis.Z, IncrementType.LARGE_INCREMENT));
		setItem(10, new RotationSetItem(this, this.rotatableType, RotatableAxis.X, IncrementType.SMALL_INCREMENT));
		setItem(12, new RotationSetItem(this, this.rotatableType, RotatableAxis.Y, IncrementType.SMALL_INCREMENT));
		setItem(14, new RotationSetItem(this, this.rotatableType, RotatableAxis.Z, IncrementType.SMALL_INCREMENT));
		setItem(28, new RotationSetItem(this, this.rotatableType, RotatableAxis.X, IncrementType.SMALL_DECREMENT));
		setItem(30, new RotationSetItem(this, this.rotatableType, RotatableAxis.Y, IncrementType.SMALL_DECREMENT));
		setItem(32, new RotationSetItem(this, this.rotatableType, RotatableAxis.Z, IncrementType.SMALL_DECREMENT));
		setItem(10, new RotationSetItem(this, this.rotatableType, RotatableAxis.X, IncrementType.SMALL_INCREMENT));
		setItem(37, new RotationSetItem(this, this.rotatableType, RotatableAxis.X, IncrementType.LARGE_DECREMENT));
		setItem(39, new RotationSetItem(this, this.rotatableType, RotatableAxis.Y, IncrementType.LARGE_DECREMENT));
		setItem(41, new RotationSetItem(this, this.rotatableType, RotatableAxis.Z, IncrementType.LARGE_DECREMENT));
		
		setItem(19, new RotationResetItem(this, this.rotatableType, RotatableAxis.X));
		setItem(21, new RotationResetItem(this, this.rotatableType, RotatableAxis.Y));
		setItem(23, new RotationResetItem(this, this.rotatableType, RotatableAxis.Z));
		
		setItem(34, new PreviousGUIItem<>(this, this.rotationSelectGUI));
	}

}
