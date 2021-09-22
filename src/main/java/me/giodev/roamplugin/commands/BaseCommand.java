package me.giodev.roamplugin.commands;

import me.giodev.roamplugin.RoamPlugin;
import me.giodev.roamplugin.data.permissions.Permission;
import org.bukkit.command.*;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseCommand implements TabExecutor, CommandExecutor {

  protected HashMap<String, SubCommand> subCommands = new HashMap<>();
  protected RoamPlugin plugin;

  public BaseCommand(RoamPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

    if(!sender.hasPermission(getPermission())) {
      sender.sendMessage(plugin.getLanguageManager().getNoPermission());
      return true;
    }

    if(!(isPlayerOnly()) || sender instanceof Player) {
      if(args.length > 0 && subCommands.get(args[0].toUpperCase()) != null) {

        SubCommand cmd = subCommands.get(args[0].toUpperCase());

        if(sender.hasPermission(cmd.getPermission())) {
          cmd.executeCommand(sender, args, plugin);
        }else{
          sender.sendMessage(plugin.getLanguageManager().getNoPermission());
        }

      }else{
        executeStockSubCommand(sender);
      }
    }

    return true;
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
    if(args.length == 1) {
      ArrayList<String> subCommands = new ArrayList<>(this.subCommands.keySet());

      if(!sender.hasPermission(Permission.ADMIN)) {
        subCommands.removeIf(string -> this.subCommands.get(string).getPermission().equals(Permission.ADMIN));
      }

      return subCommands.stream().map(String::toLowerCase).collect(Collectors.toList());

    }else {

      String[] arguments = this.subCommands.get(args[0].toUpperCase()).getArguments();

      if(arguments == null || arguments.length < args.length - 1) return null;

      List<String> argList = new ArrayList<>();
      argList.add(arguments[args.length - 2]);

      return argList;

    }
  }

  public abstract @NotNull String getName();

  public abstract @NotNull String getPermission();

  public abstract @NotNull List<String> getAliases();

  public abstract boolean isPlayerOnly();

  public abstract void executeStockSubCommand(CommandSender sender);

}
