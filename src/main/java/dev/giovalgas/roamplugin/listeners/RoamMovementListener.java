package dev.giovalgas.roamplugin.listeners;

import dev.giovalgas.roamplugin.RoamPlugin;
import dev.giovalgas.roamplugin.data.data.RoamState;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class RoamMovementListener implements Listener {

  private RoamPlugin plugin;

  public RoamMovementListener(RoamPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {

    Player player = event.getPlayer();
    RoamState rm = plugin.getRoamerState(player);

    if(!rm.isRoaming()) return;

    Location initialLocation = rm.getRoamingPlayerEntity().getLocation();

    // Teleport the player back if they go into another world.
    if(!player.getLocation().getWorld().equals(initialLocation.getWorld())) {
      player.teleport(initialLocation);
      player.setGameMode(GameMode.SPECTATOR); // Some plugins might update the gamemode when switching worlds.
      player.sendMessage(plugin.getLanguageManager().getMovementError());
      return;
    }

    if(player.getLocation().distance(initialLocation) > plugin.getConfigManager().getMaxDistance()) {
      player.teleport(initialLocation);
      player.sendMessage(plugin.getLanguageManager().getMovementError());
    }

  }

}
