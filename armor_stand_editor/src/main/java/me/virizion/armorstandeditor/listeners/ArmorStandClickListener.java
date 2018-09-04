package me.virizion.armorstandeditor.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import me.virizion.armorstandeditor.ArmorStandEditor;
import me.virizion.armorstandeditor.gui.armorstand.ArmorStandGUI;

public class ArmorStandClickListener implements Listener
{

	private ArmorStandEditor armorStandEditor;

	public ArmorStandClickListener(ArmorStandEditor armorStandEditor)
	{
		this.armorStandEditor = armorStandEditor;
		
		Bukkit.getScheduler().runTaskTimer(armorStandEditor, () ->
		{
			new HashMap<>(this.armorStandEditor.getEditingArmorStands()).entrySet().forEach(entry ->
			{
				if (!entry.getValue().isValid())
				{
					entry.getKey().closeInventory();
					entry.getKey().sendMessage(ChatColor.RED + "The armor stand you were editing was destroyed!");
				}
			});
		}, 0, 20);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onArmorStandClick(PlayerInteractAtEntityEvent event)
	{
		if (!this.armorStandEditor.getConfig().getBoolean("shift-right-click-enabled"))
		{
			return;
		}
		
		if (!event.getPlayer().isSneaking())
		{
			return;
		}
		
		if (event.getRightClicked().getType() != EntityType.ARMOR_STAND)
		{
			return;
		}
		
		if (!event.getPlayer().hasPermission("armorstandeditor.edit"))
		{
			return;
		}
		
		event.setCancelled(true);
		
		if (this.armorStandEditor.getEditingArmorStands().containsValue(event.getRightClicked()))
		{
			event.getPlayer().sendMessage(ChatColor.RED + "Someone is currently editing this armor stand!");
			return;
		}
		
		new ArmorStandGUI(this.armorStandEditor, event.getPlayer(), (ArmorStand) event.getRightClicked()).open();
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event)
	{
		if (this.armorStandEditor.getEditingArmorStands().containsValue(event.getRightClicked()))
		{
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.RED + "Someone is currently editing this armor stand!");
		}
	}

}
