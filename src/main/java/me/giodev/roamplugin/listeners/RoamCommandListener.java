package me.giodev.roamplugin.listeners;

import me.giodev.roamplugin.RoamPlugin;
import me.giodev.roamplugin.commands.roamcommand.RoamCommand;
import me.giodev.roamplugin.data.permissions.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Locale;

public class RoamCommandListener implements Listener {

  private RoamPlugin plugin;

  public RoamCommandListener(RoamPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onCommand(PlayerCommandPreprocessEvent event) {

    Player player = event.getPlayer();
    String command = event.getMessage().split(" ")[0].replace("/", "");

    plugin.getLog().info(command);

    RoamCommand rc = new RoamCommand(plugin);

    if(command.equalsIgnoreCase(rc.getName()) || rc.getAliases().contains(command.toLowerCase())) return;

    if(!plugin.getRoamerState(player).isRoaming()) return;

    if(!player.hasPermission(Permission.ADMIN) || !player.hasPermission(Permission.COMMAND_OVERWRITE + command)) {
      event.setCancelled(true);
      //MESSAGE (can't use this command while roaming)
    }

  }

}
