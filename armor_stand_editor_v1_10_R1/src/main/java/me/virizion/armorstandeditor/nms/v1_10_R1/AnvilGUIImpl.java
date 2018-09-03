package me.virizion.armorstandeditor.nms.v1_10_R1;

import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.virizion.armorstandeditor.nms.AnvilGUI;
import net.minecraft.server.v1_10_R1.BlockPosition;
import net.minecraft.server.v1_10_R1.ChatMessage;
import net.minecraft.server.v1_10_R1.ContainerAnvil;
import net.minecraft.server.v1_10_R1.EntityHuman;
import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.PacketPlayInCloseWindow;
import net.minecraft.server.v1_10_R1.PacketPlayOutExperience;
import net.minecraft.server.v1_10_R1.PacketPlayOutOpenWindow;

public class AnvilGUIImpl extends ContainerAnvil implements AnvilGUI, Listener
{

	private Plugin plugin;
	private Player player;
	private String defaultText;
	private Inventory inventory;
	private Consumer<String> textConsumer;

	public AnvilGUIImpl(Plugin plugin, Player player, String defaultText, Consumer<String> textConsumer)
	{
		super(((CraftPlayer) player).getHandle().inventory, ((CraftPlayer) player).getHandle().world, new BlockPosition(0, 0, 0), ((CraftPlayer) player).getHandle());
		
		this.plugin = plugin;
		this.player = player;
		this.defaultText = defaultText;
		this.textConsumer = textConsumer;
	}

	@Override
	public boolean a(EntityHuman entityhuman)
	{
		return true;
	}

	@Override
	public Player getPlayer()
	{
		return this.player;
	}

	@Override
	public void open()
	{
		EntityPlayer entityPlayer = ((CraftPlayer) this.player).getHandle();
		
		if (entityPlayer.activeContainer == this)
		{
			return;
		}
		
		ItemStack itemStack = new ItemStack(Material.NAME_TAG);
		
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(this.defaultText);
		
		itemStack.setItemMeta(itemMeta);
		
		this.inventory = getBukkitView().getTopInventory();
		this.inventory.setItem(0, itemStack);
		
		//Close any inventories the player currently has open
		if (entityPlayer.activeContainer != entityPlayer.defaultContainer)
		{
			entityPlayer.playerConnection.a(new PacketPlayInCloseWindow(entityPlayer.activeContainer.windowId));
		}
		
		//Get a non conflicting inventory id
		int containerCount = entityPlayer.nextContainerCounter();
		this.windowId = containerCount;
		
		//Set the anvil inventory to the opened inventory
		entityPlayer.playerConnection.sendPacket(new PacketPlayOutOpenWindow(containerCount, "minecraft:anvil", new ChatMessage("container.repair")));
		entityPlayer.activeContainer = this;
		
		addSlotListener(entityPlayer);
		
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
	}

	@Override
	public void close()
	{
		if (((CraftPlayer) this.player).getHandle().activeContainer != this)
		{
			return;
		}
		
		unregister();
		this.player.closeInventory();
	}

	private void unregister()
	{
		String text = this.defaultText;
		
		if (this.inventory != null && this.inventory.getItem(2) != null && this.inventory.getItem(2).hasItemMeta())
		{
			if (this.inventory.getItem(2).getItemMeta().hasDisplayName())
			{
				text = this.inventory.getItem(2).getItemMeta().getDisplayName();
			}
			else
			{
				text = "";
			}
		}
		
		HandlerList.unregisterAll(this);
		
		this.textConsumer.accept(text);
		
		if (this.inventory != null)
		{
			this.inventory.clear();
		}
	}

	@EventHandler
	public void clickHandle(InventoryClickEvent event)
	{
		if (!event.getWhoClicked().equals(this.player))
		{
			return;
		}
		
		if (!event.getInventory().equals(this.inventory))
		{
			return;
		}
		
		event.setCancelled(true);
		close();
		
		this.player.updateInventory();
		
		//The client will automatically take away exp on click so we have to update it so the correct exp is shown
		EntityPlayer entityPlayer = ((CraftPlayer) this.player).getHandle();
		entityPlayer.playerConnection.sendPacket(new PacketPlayOutExperience(entityPlayer.exp, entityPlayer.expTotal, entityPlayer.expLevel));
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event)
	{
		if (!event.getPlayer().equals(this.player))
		{
			return;
		}
		
		if (!event.getInventory().equals(this.inventory))
		{
			return;
		}
		
		unregister();
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
