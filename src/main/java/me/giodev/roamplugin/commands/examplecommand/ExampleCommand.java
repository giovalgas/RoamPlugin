package me.giodev.roamplugin.commands.examplecommand;

import me.giodev.roamplugin.RoamPlugin;
import me.giodev.roamplugin.commands.BaseCommand;
import me.giodev.roamplugin.commands.examplecommand.subcommands.ExampleSubCommand;
import me.giodev.roamplugin.data.permissions.Permission;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.List;

public class ExampleCommand extends BaseCommand {

  public ExampleCommand(RoamPlugin plugin) {
    super(plugin);
    subCommands.put("TEST", new ExampleSubCommand());
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
    return "excommand";
  }


  @Override
  public @NotNull List<String> getAliases() { return Arrays.asList(new String[]{"excmd"}.clone()); }

  @Override
  public boolean isPlayerOnly() {
    return false;
  }


}
