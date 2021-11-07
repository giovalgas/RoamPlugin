package dev.giovalgas.roamplugin.listeners;

import dev.giovalgas.roamplugin.RoamPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class RoamActionListener implements Listener {

  private RoamPlugin plugin;

  public RoamActionListener(RoamPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onSpectate(PlayerTeleportEvent event) {

    if(plugin.getRoamerState(event.getPlayer()).isRoaming() && event.getCause() == TeleportCause.SPECTATE) {
      event.setCancelled(true);
      event.getPlayer().setSpectatorTarget(null);
      event.getPlayer().sendMessage(plugin.getLanguageManager().getInteractionError());
    }

  }

  @EventHandler
  public void onInteract(PlayerInteractEvent event) {

    if(plugin.getRoamerState(event.getPlayer()).isRoaming()){
      event.setCancelled(true);
    }

  }


}
