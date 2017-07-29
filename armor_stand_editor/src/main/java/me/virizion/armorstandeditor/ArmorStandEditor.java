package me.virizion.armorstandeditor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.virizion.armorstandeditor.commands.ArmorStandEditorCommand;
import me.virizion.armorstandeditor.commands.EditArmorStandCommand;
import me.virizion.armorstandeditor.listeners.ArmorStandClickListener;
import me.virizion.armorstandeditor.nms.AnvilGUI;
import me.virizion.armorstandeditor.nms.DisabledSlots;
import me.virizion.armorstandeditor.nms.Invulnerability;

public class ArmorStandEditor extends JavaPlugin
{

	private DisabledSlots disabledSlots;
	private Class<?> anvilGUIClass;
	private Invulnerability invulnerability;
	private boolean isOffHandSupported;
	private boolean isMarkerSupported;
	private Map<Player, ArmorStand> editingArmorStands = new HashMap<>();

	@Override
	public void onEnable()
	{
		loadDisabledSlots();
		loadAnvilGUIClass();
		loadInvulnerability();
		this.isOffHandSupported = !getServer().getClass().getName().split(Pattern.quote("."))[3].startsWith("v1_8");
		this.isMarkerSupported = this.isOffHandSupported || getServer().getClass().getName().split(Pattern.quote("."))[3].equals("v1_8_R3");
		
		getCommand("armorstandeditor").setExecutor(new ArmorStandEditorCommand(this));
		getCommand("editarmorstand").setExecutor(new EditArmorStandCommand(this));
		
		getServer().getPluginManager().registerEvents(new ArmorStandClickListener(this), this);
	}

	private void loadDisabledSlots()
	{
		String version = getServer().getClass().getName().split(Pattern.quote("."))[3];
		
		try
		{
			Class<?> clazz = Class.forName(getClass().getPackage().getName() + ".nms." + version + ".DisabledSlotsImpl");
			this.disabledSlots = (DisabledSlots) clazz.newInstance();
			
			getLogger().info("Loaded equipment slot locker for bukkit version " + version + "!");
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
		{
			getLogger().warning("Your bukkit version " + version + " isn't supported so equipment slot locking will be disabled!");
		}
	}

	private void loadAnvilGUIClass()
	{
		String version = getServer().getClass().getName().split(Pattern.quote("."))[3];
		
		try
		{
			this.anvilGUIClass = Class.forName(getClass().getPackage().getName() + ".nms." + version + ".AnvilGUIImpl");
			
			getLogger().info("Loaded anvil gui class for bukkit version " + version + "!");
		}
		catch (ClassNotFoundException e)
		{
			getLogger().warning("Your bukkit version " + version + " isn't supported so renaming armor stands with anvil gui will be disabled!");
		}
	}

	private void loadInvulnerability()
	{
		String version = getServer().getClass().getName().split(Pattern.quote("."))[3];
		
		try
		{
			Class<?> clazz = Class.forName(getClass().getPackage().getName() + ".nms." + version + ".InvulnerabilityImpl");
			this.invulnerability = (Invulnerability) clazz.newInstance();
			
			getLogger().info("Loaded invulnerability toggler for bukkit version " + version + "!");
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
		{
			getLogger().warning("Your bukkit version " + version + " isn't supported so invulnerability toggling will be disabled!");
		}
	}

	public boolean isEquipmentSlotLockingEnabled()
	{
		return this.disabledSlots != null;
	}

	public DisabledSlots getDisabledSlots()
	{
		return this.disabledSlots;
	}

	public boolean isAnvilGUIEnabled()
	{
		return this.anvilGUIClass != null;
	}

	public AnvilGUI createAnvilGUI(Player player, String defaultText, Consumer<String> textConsumer)
	{
		try
		{
			return (AnvilGUI) this.anvilGUIClass.getConstructor(Plugin.class, Player.class, String.class, Consumer.class).newInstance(this, player, defaultText, textConsumer);
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public boolean isInvulnerabilityEnabled()
	{
		return this.invulnerability != null;
	}

	public Invulnerability getInvulnerability()
	{
		return this.invulnerability;
	}

	public boolean isOffHandSupported()
	{
		return this.isOffHandSupported;
	}

	public boolean isMarkerSupported()
	{
		return this.isMarkerSupported;
	}

	public Map<Player, ArmorStand> getEditingArmorStands()
	{
		return this.editingArmorStands;
	}

}
