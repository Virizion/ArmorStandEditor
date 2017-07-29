package me.virizion.armorstandeditor.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.google.common.base.Joiner;

import me.virizion.armorstandeditor.ArmorStandEditor;

public class ArmorStandEditorCommand implements CommandExecutor
{

	private ArmorStandEditor armorStandEditor;

	public ArmorStandEditorCommand(ArmorStandEditor armorStandEditor)
	{
		this.armorStandEditor = armorStandEditor;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String commandLabel, String[] args)
	{
		commandSender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "=====================================================");
		commandSender.sendMessage("");
		
		commandSender.sendMessage(ChatColor.BLUE + "Name: " + ChatColor.YELLOW + this.armorStandEditor.getDescription().getName());
		commandSender.sendMessage(ChatColor.BLUE + "Authors: " + ChatColor.YELLOW + Joiner.on(", ").join(this.armorStandEditor.getDescription().getAuthors()));
		commandSender.sendMessage(ChatColor.BLUE + "Version: " + ChatColor.YELLOW + this.armorStandEditor.getDescription().getVersion());
		commandSender.sendMessage(ChatColor.BLUE + "Description: " + ChatColor.YELLOW + this.armorStandEditor.getDescription().getDescription());
		
		commandSender.sendMessage("");
		commandSender.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "=====================================================");
		
		return true;
	}

}
