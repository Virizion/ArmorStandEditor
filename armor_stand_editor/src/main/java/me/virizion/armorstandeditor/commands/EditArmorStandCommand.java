package me.virizion.armorstandeditor.commands;

import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import me.virizion.armorstandeditor.ArmorStandEditor;
import me.virizion.armorstandeditor.gui.armorstand.ArmorStandGUI;

public class EditArmorStandCommand implements CommandExecutor
{

	private ArmorStandEditor armorStandEditor;

	public EditArmorStandCommand(ArmorStandEditor armorStandEditor)
	{
		this.armorStandEditor = armorStandEditor;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args)
	{
		if (!(commandSender instanceof Player))
		{
			commandSender.sendMessage(ChatColor.RED + "You must be a player to run this command!");
			return true;
		}
		
		Player player = (Player) commandSender;
		
		if (!player.hasPermission("armorstandeditor.edit"))
		{
			player.sendMessage("You do not have permission to edit armor stands!");
			return true;
		}
		
		Optional<ArmorStand> optionalArmorStand = player.getWorld().getEntitiesByClass(ArmorStand.class).stream()
		.filter(armorStand -> armorStand.getLocation().distanceSquared(player.getLocation()) <= 5 * 5)
		.sorted((a1, a2) -> Double.compare(a1.getLocation().distanceSquared(player.getLocation()), a2.getLocation().distanceSquared(player.getLocation())))
		.findFirst();
		
		if (optionalArmorStand.isPresent())
		{
			//We don't filter out occupied armor stands because we want the player to know they are occupied
			if (this.armorStandEditor.getEditingArmorStands().containsValue(optionalArmorStand.get()))
			{
				player.sendMessage(ChatColor.RED + "Someone is currently editing this armor stand!");
			}
			else
			{
				new ArmorStandGUI(this.armorStandEditor, player, optionalArmorStand.get()).open();
			}
		}
		else
		{
			player.sendMessage(ChatColor.RED + "No nearby armor stands!");
		}
		
		return true;
	}

}
