package me.virizion.armorstandeditor.gui.armorstand;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import me.virizion.armorstandeditor.ArmorStandEditor;
import me.virizion.armorstandeditor.gui.CloseItem;
import me.virizion.armorstandeditor.gui.GUI;
import me.virizion.armorstandeditor.gui.armorstand.items.ArmorItem;
import me.virizion.armorstandeditor.gui.armorstand.items.ArmsItem;
import me.virizion.armorstandeditor.gui.armorstand.items.BasePlateItem;
import me.virizion.armorstandeditor.gui.armorstand.items.GravityItem;
import me.virizion.armorstandeditor.gui.armorstand.items.InvulnerabilityItem;
import me.virizion.armorstandeditor.gui.armorstand.items.MarkerItem;
import me.virizion.armorstandeditor.gui.armorstand.items.NameItem;
import me.virizion.armorstandeditor.gui.armorstand.items.NameVisibilityItem;
import me.virizion.armorstandeditor.gui.armorstand.items.RotationMenuButton;
import me.virizion.armorstandeditor.gui.armorstand.items.SizeItem;
import me.virizion.armorstandeditor.gui.armorstand.items.VisibilityItem;

public class ArmorStandGUI extends GUI
{

	private ArmorStand armorStand;

	public ArmorStandGUI(ArmorStandEditor armorStandEditor, Player player, ArmorStand armorStand)
	{
		super(armorStandEditor, player, Bukkit.createInventory(null, 54, "Armor Stand Editor"));
		
		this.armorStand = armorStand;
	}

	public ArmorStand getArmorStand()
	{
		return this.armorStand;
	}

	@Override
	public void open()
	{
		super.open();
		
		this.armorStandEditor.getEditingArmorStands().put(this.player, this.armorStand);
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
		setItem(22, new RotationMenuButton(this));
		
		setItem(10, new ArmorItem(this, EquipmentSlot.HEAD));
		
		if (this.armorStandEditor.isOffHandSupported())
		{
			setItem(18, new ArmorItem(this, EquipmentSlot.OFF_HAND));
		}
		
		setItem(19, new ArmorItem(this, EquipmentSlot.CHEST));
		setItem(20, new ArmorItem(this, EquipmentSlot.HAND));
		setItem(28, new ArmorItem(this, EquipmentSlot.LEGS));
		setItem(37, new ArmorItem(this, EquipmentSlot.FEET));
		
		setItem(15, new BasePlateItem(this));
		setItem(16, new SizeItem(this));
		setItem(17, new GravityItem(this));
		setItem(24, new VisibilityItem(this));
		setItem(25, new NameVisibilityItem(this));
		setItem(26, new NameItem(this));
		setItem(33, new ArmsItem(this));
		
		if (this.armorStandEditor.isMarkerSupported())
		{
			setItem(34, new MarkerItem(this));
		}
		
		setItem(35, new InvulnerabilityItem(this));
		
		setItem(49, new CloseItem<>(this));
	}

}
