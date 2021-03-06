package de.jeffclan.JeffChestSort;

import org.bukkit.ChatColor;

public class JeffChestSortMessages {

	// Messages can be customized in the config.yml
	// To avoid problems with missing messages in the config, the default messages
	// are
	// hardcoded.
	// When creating pull requests that feature a message to the player, please
	// stick to this scheme

	JeffChestSortPlugin plugin;

	final String MSG_ACTIVATED, MSG_DEACTIVATED, MSG_COMMANDMESSAGE, MSG_COMMANDMESSAGE2, MSG_PLAYERSONLY,
			MSG_PLAYERINVSORTED, MSG_INVALIDOPTIONS;
	
	final String MSG_GUI_ENABLED, MSG_GUI_DISABLED;
	
	final String MSG_GUI_MIDDLECLICK, MSG_GUI_SHIFTCLICK, MSG_GUI_DOUBLECLICK, MSG_GUI_SHIFTRIGHTCLICK;

	JeffChestSortMessages(JeffChestSortPlugin plugin) {
		this.plugin = plugin;

		MSG_ACTIVATED = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("message-sorting-enabled", "&7Automatic chest sorting has been &aenabled&7.&r"));

		MSG_DEACTIVATED = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("message-sorting-disabled", "&7Automatic chest sorting has been &cdisabled&7.&r"));

		MSG_COMMANDMESSAGE = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(
				"message-when-using-chest", "&7Hint: Type &6/chestsort&7 to enable automatic chest sorting."));

		MSG_COMMANDMESSAGE2 = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(
				"message-when-using-chest2", "&7Hint: Type &6/chestsort&7 to disable automatic chest sorting."));

		MSG_PLAYERSONLY = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("message-error-players-only", "&cError: This command can only be run by players.&r"));

		MSG_PLAYERINVSORTED = ChatColor.translateAlternateColorCodes('&',
				plugin.getConfig().getString("message-player-inventory-sorted", "&7Your inventory has been sorted."));

		MSG_INVALIDOPTIONS = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("message-error-invalid-options", "&cError: Unknown option %s. Valid options are %s."));
		
		MSG_GUI_ENABLED = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("message-gui-enabled","&aEnabled"));
		
		MSG_GUI_DISABLED = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("message-gui-disabled","&cDisabled"));
		
		MSG_GUI_MIDDLECLICK = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("message-gui-middle-click","Middle-Click"));
		
		MSG_GUI_SHIFTCLICK = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("message-gui-shift-click","Shift + Click"));
		
		MSG_GUI_DOUBLECLICK = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("message-gui-double-click","Double-Click"));
		
		MSG_GUI_SHIFTRIGHTCLICK = ChatColor.translateAlternateColorCodes('&', plugin.getConfig()
				.getString("message-gui-shift-right-click","Shift + Right-Click"));
		
		//MSG_ERR_HOTKEYSDISABLED = ChatColor.RED + "[ChestSort] Hotkeys are only available for Minecraft 1.9 and later.";
	}

}
