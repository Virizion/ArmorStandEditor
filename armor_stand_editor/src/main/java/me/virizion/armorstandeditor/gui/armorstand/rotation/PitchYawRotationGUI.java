package me.virizion.armorstandeditor.gui.armorstand.rotation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.virizion.armorstandeditor.ArmorStandEditor;
import me.virizion.armorstandeditor.gui.GUI;
import me.virizion.armorstandeditor.gui.PreviousGUIItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.items.PitchYawResetItem;
import me.virizion.armorstandeditor.gui.armorstand.rotation.items.PitchYawSetItem;

public class PitchYawRotationGUI extends GUI
{

	private RotationSelectGUI rotationSelectGUI;

	public PitchYawRotationGUI(ArmorStandEditor armorStandEditor, Player player, RotationSelectGUI rotationSelectGUI)
	{
		super(armorStandEditor, player, Bukkit.createInventory(null, 54, "Set Pitch & Yaw"));
		
		this.rotationSelectGUI = rotationSelectGUI;
	}

	public RotationSelectGUI getRotationSelectGUI()
	{
		return this.rotationSelectGUI;
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
		setItem(11, new PitchYawSetItem(this, PitchYaw.PITCH, IncrementType.LARGE_DECREMENT));
		setItem(12, new PitchYawSetItem(this, PitchYaw.PITCH, IncrementType.SMALL_DECREMENT));
		setItem(14, new PitchYawSetItem(this, PitchYaw.PITCH, IncrementType.SMALL_INCREMENT));
		setItem(15, new PitchYawSetItem(this, PitchYaw.PITCH, IncrementType.LARGE_INCREMENT));
		setItem(29, new PitchYawSetItem(this, PitchYaw.YAW, IncrementType.LARGE_DECREMENT));
		setItem(30, new PitchYawSetItem(this, PitchYaw.YAW, IncrementType.SMALL_DECREMENT));
		setItem(32, new PitchYawSetItem(this, PitchYaw.YAW, IncrementType.SMALL_INCREMENT));
		setItem(33, new PitchYawSetItem(this, PitchYaw.YAW, IncrementType.LARGE_INCREMENT));
		
		setItem(13, new PitchYawResetItem(this, PitchYaw.PITCH));
		setItem(31, new PitchYawResetItem(this, PitchYaw.YAW));
		
		setItem(49, new PreviousGUIItem<>(this, this.rotationSelectGUI));
	}

}
