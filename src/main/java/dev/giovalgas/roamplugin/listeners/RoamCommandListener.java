package dev.giovalgas.roamplugin.listeners;

import dev.giovalgas.roamplugin.RoamPlugin;
import dev.giovalgas.roamplugin.commands.roamcommand.RoamCommand;
import dev.giovalgas.roamplugin.data.permissions.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class RoamCommandListener implements Listener {

  private RoamPlugin plugin;

  public RoamCommandListener(RoamPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onCommand(PlayerCommandPreprocessEvent event) {

    Player player = event.getPlayer();
    String command = event.getMessage().split(" ")[0].replace("/", "");

    RoamCommand rc = new RoamCommand(plugin);

    if(
            command.equalsIgnoreCase(rc.getName()) ||
            rc.getAliases().contains(command.toLowerCase()) ||
            !plugin.getConfigManager().isDisableCommands()
    ) return;

    if(!plugin.getRoamerState(player).isRoaming()) return;

    if(!player.hasPermission(Permission.ADMIN) || !player.hasPermission(Permission.COMMAND_OVERWRITE + command)) {
      event.setCancelled(true);
      player.sendMessage(plugin.getLanguageManager().getCommandError());
    }

  }

}
