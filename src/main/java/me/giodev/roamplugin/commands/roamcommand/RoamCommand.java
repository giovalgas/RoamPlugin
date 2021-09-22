package me.giodev.roamplugin.commands.roamcommand;

import me.giodev.roamplugin.RoamPlugin;
import me.giodev.roamplugin.commands.BaseCommand;
import me.giodev.roamplugin.data.permissions.Permission;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.List;

public class RoamCommand extends BaseCommand {

  public RoamCommand(RoamPlugin plugin) {
    super(plugin);
  }

  @Override
  public void executeStockSubCommand(CommandSender sender) {
    plugin.getLog().info("executing stock command!");
  }

  @Override
  public @NotNull String getPermission() {
    return Permission.TPCOMMAND;
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
    return false;
  }


}
