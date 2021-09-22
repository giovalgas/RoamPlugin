package me.giodev.roamplugin.commands.roamcommand;

import me.giodev.roamplugin.RoamPlugin;
import me.giodev.roamplugin.commands.BaseCommand;
import me.giodev.roamplugin.data.data.RoamingPlayerEntity;
import me.giodev.roamplugin.data.permissions.Permission;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.List;

public class RoamCommand extends BaseCommand {

  public RoamCommand(RoamPlugin plugin) {
    super(plugin);
  }

  @Override
  public void executeStockSubCommand(CommandSender sender) {
    Player player = (Player) sender;

    if(!((LivingEntity) player).isOnGround() && player.getGameMode() != GameMode.SPECTATOR) {
      //MESSAGE (CANNOT USE COMMAND WHILE FLYING)
      return;
    }

    plugin.flipRoamingState(player);

  }

  @Override
  public @NotNull String getPermission() {
    return Permission.ROAM;
  }

  @Override
  public @NotNull String getName() {
    return "roam";
  }


  @Override
  public @NotNull List<String> getAliases() {
    return Arrays.asList(new String[]{"freecam", "fc"}.clone());
  }

  @Override
  public boolean isPlayerOnly() {
    return true;
  }


}
