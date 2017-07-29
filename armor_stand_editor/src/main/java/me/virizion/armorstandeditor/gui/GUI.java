package me.virizion.armorstandeditor.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import me.virizion.armorstandeditor.ArmorStandEditor;

public abstract class GUI implements Listener
{

	protected ArmorStandEditor armorStandEditor;
	protected Player player;
	private Inventory inventory;
	private GUIItem<?>[] guiItems;

	public GUI(ArmorStandEditor armorStandEditor, Player player, Inventory inventory)
	{
		this.armorStandEditor = armorStandEditor;
		this.player = player;
		this.inventory = inventory;
		this.guiItems = new GUIItem[inventory.getSize()];
	}

	public ArmorStandEditor getArmorStandEditor()
	{
		return this.armorStandEditor;
	}

	public Player getPlayer()
	{
		return this.player;
	}

	public boolean isOpen()
	{
		return this.player.getOpenInventory().getTopInventory().equals(this.inventory);
	}

	public void open()
	{
		if (isOpen())
		{
			return;
		}
		
		clear();
		build();
		
		this.player.openInventory(this.inventory);
		
		Bukkit.getPluginManager().registerEvents(this, this.armorStandEditor);
	}

	public void close()
	{
		if (!isOpen())
		{
			return;
		}
		
		unregister();
		
		this.player.closeInventory();
	}

	public GUIItem<?> getItem(int index)
	{
		return this.guiItems[index];
	}

	public void setItem(int index, GUIItem<?> item)
	{
		this.guiItems[index] = item;
		item.itemStack = item.getItemStack();
		item.index = index;
		this.inventory.setItem(index, item.itemStack);
	}

	public void clear()
	{
		Arrays.fill(this.guiItems, null);
		this.inventory.clear();
	}

	protected void unregister()
	{
		HandlerList.unregisterAll(this);
	}

	public abstract void build();

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onInventoryClick(InventoryClickEvent event)
	{
		if (event.getInventory().equals(this.inventory) && event.getWhoClicked().equals(this.player))
		{	
			event.setCancelled(true);
			
			if (event.getRawSlot() < 0 || event.getRawSlot() >= this.guiItems.length)
			{
				event.setCancelled(false); //Allow free movement of items outside the GUI
				return;
			}
			
			GUIItem<?> item = getItem(event.getRawSlot());
			
			if (item != null)
			{
				item.click((Player) event.getWhoClicked(), event.getClick());
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event)
	{
		if (event.getInventory().equals(this.inventory) && event.getPlayer().equals(this.player))
		{
			unregister();
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		if (event.getPlayer().equals(this.player))
		{
			close();
		}
	}

}
