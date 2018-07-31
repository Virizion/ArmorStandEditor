package me.virizion.armorstandeditor.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.virizion.armorstandeditor.ArmorStandEditor;

public class JoinUpdateMessageListener implements Listener
{

	private ArmorStandEditor armorStandEditor;

	public JoinUpdateMessageListener(ArmorStandEditor armorStandEditor)
	{
		this.armorStandEditor = armorStandEditor;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		if (event.getPlayer().hasPermission("armorstandeditor.updatemessage") && this.armorStandEditor.getConfig().getBoolean("check-updates") && this.armorStandEditor.getConfig().getBoolean("player-update-message"))
		{
			this.armorStandEditor.getUpdater().checkVersion(event.getPlayer());
		}
	}

}
