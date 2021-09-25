package me.giodev.roamplugin.listeners;

import me.giodev.roamplugin.RoamPlugin;
import me.giodev.roamplugin.data.data.RoamState;
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

    if(player.getLocation().distance(initialLocation) > plugin.getConfigManager().getMaxDistance()) {
      player.teleport(initialLocation);
      player.sendMessage(plugin.getLanguageManager().getMovementError());
    }

  }

}
