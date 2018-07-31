package me.virizion.armorstandeditor.updater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.virizion.armorstandeditor.ArmorStandEditor;

public class Updater
{

	private static final String API_URL = "https://api.spigotmc.org/legacy/update.php?resource=44752";

	private ArmorStandEditor armorStandEditor;

	public Updater(ArmorStandEditor armorStandEditor)
	{
		this.armorStandEditor = armorStandEditor;
	}

	public void checkVersion(CommandSender commandSender)
	{
		String currentVersion = this.armorStandEditor.getDescription().getVersion();
		String[] currentVersionParts = currentVersion.split("\\.");
		
		Bukkit.getScheduler().runTaskAsynchronously(this.armorStandEditor, () ->
		{
			try
			{
				String newVersion = fetchVersion();
				String[] newVersionParts = newVersion.split("\\.");
				
				for (int i = 0; i < newVersionParts.length; i++)
				{
					if (Integer.parseInt(newVersionParts[i]) != Integer.parseInt(currentVersionParts[i]))
					{
						Bukkit.getScheduler().runTask(this.armorStandEditor, () -> commandSender.sendMessage(ChatColor.BLUE + "[" + this.armorStandEditor.getDescription().getName() + "] There is an update available! You are running " + currentVersion + " and the new version is " + newVersion + "!"));
						return;
					}
				}
				
				Bukkit.getScheduler().runTask(this.armorStandEditor, () -> commandSender.sendMessage(ChatColor.BLUE + "[" + this.armorStandEditor.getDescription().getName() + "] You are running the latest version!"));
			}
			catch (IOException e)
			{
				Bukkit.getScheduler().runTask(this.armorStandEditor, () -> commandSender.sendMessage(ChatColor.BLUE + "[" + this.armorStandEditor.getDescription().getName() + "] Unable to check for updates!"));
			}
		});
	}

	private String fetchVersion() throws IOException
	{
		HttpURLConnection con = (HttpURLConnection) new URL(API_URL).openConnection();
		con.setRequestMethod("GET");
		
		return new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
	}

}
