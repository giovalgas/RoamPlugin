package dev.giovalgas.roamplugin.listeners;

import dev.giovalgas.roamplugin.RoamPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

  private RoamPlugin plugin;

  public PlayerQuitListener(RoamPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onLeave(PlayerQuitEvent event) {
    Player player = event.getPlayer();

    if(plugin.getRoamerState(player).isRoaming()) {
      plugin.getRoamerState(player).setRoaming(false);
    }

    plugin.removeRoamerState(player);

  }


}
