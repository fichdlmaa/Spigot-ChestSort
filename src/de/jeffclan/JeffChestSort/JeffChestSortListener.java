package de.jeffclan.JeffChestSort;

import java.util.UUID;
import java.io.File;

import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.ShulkerBox;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JeffChestSortListener implements Listener {

	JeffChestSortPlugin plugin;

	JeffChestSortListener(JeffChestSortPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		UUID uniqueId = event.getPlayer().getUniqueId();
		if (!plugin.PerPlayerSettings.containsKey(uniqueId.toString())) {

			File playerFile = new File(plugin.getDataFolder() + File.separator + "playerdata",
					event.getPlayer().getUniqueId().toString() + ".yml");
			YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);

			boolean activeForThisPlayer;

			if (!playerFile.exists()) {
				activeForThisPlayer = plugin.getConfig().getBoolean("sorting-enabled-by-default");
			} else {
				activeForThisPlayer = playerConfig.getBoolean("sortingEnabled");
			}

			JeffChestSortPlayerSetting newSettings = new JeffChestSortPlayerSetting(activeForThisPlayer);
			if (!plugin.getConfig().getBoolean("show-message-again-after-logout")) {
				newSettings.hasSeenMessage = playerConfig.getBoolean("hasSeenMessage");
			}
			plugin.PerPlayerSettings.put(uniqueId.toString(), newSettings);

		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		plugin.unregisterPlayer(event.getPlayer());
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {

		if (!(event.getPlayer() instanceof Player)) {
			return;
		}

		Player p = (Player) event.getPlayer();
		
		if(!p.hasPermission("chestsort.use")) {
			return;
		}
		
		JeffChestSortPlayerSetting setting = plugin.PerPlayerSettings.get(p.getUniqueId().toString());

		if (!(event.getInventory().getHolder() instanceof Chest)
				&& !(event.getInventory().getHolder() instanceof DoubleChest)
				&& !(event.getInventory().getHolder() instanceof ShulkerBox)) {
			return;
		}

		if (!plugin.sortingEnabled(p)) {
			if (!setting.hasSeenMessage) {
				setting.hasSeenMessage = true;
				if (plugin.getConfig().getBoolean("show-message-when-using-chest")) {
					p.sendMessage(plugin.messages.MSG_COMMANDMESSAGE);
				}
			}
			return;
		} else {
			if (!setting.hasSeenMessage) {
				setting.hasSeenMessage = true;
				if (plugin.getConfig().getBoolean("show-message-when-using-chest-and-sorting-is-enabled")) {
					p.sendMessage(plugin.messages.MSG_COMMANDMESSAGE2);
				}
			}
		}

		JeffChestSortOrganizer.sortInventory(event.getInventory(),plugin.sortingMethod);
	}
}
