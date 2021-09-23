package me.giodev.roamplugin.listeners;

import me.giodev.roamplugin.RoamPlugin;
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
      //MESSAGE (You cant do that while roaming)
    }

  }

  @EventHandler
  public void onInteract(PlayerInteractEvent event) {

    if(plugin.getRoamerState(event.getPlayer()).isRoaming()){
      event.setCancelled(true);
    }

  }


}
