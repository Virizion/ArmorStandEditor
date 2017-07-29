package me.virizion.armorstandeditor.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class GUIItem<G extends GUI>
{

	protected G gui;
	protected int index;
	protected ItemStack itemStack;

	public GUIItem(G gui)
	{
		this.gui = gui;
	}

	public void update()
	{
		this.gui.setItem(this.index, this);
	}

	public abstract ItemStack getItemStack();

	public abstract void click(Player player, ClickType clickType);

}
