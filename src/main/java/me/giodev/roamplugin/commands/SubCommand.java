package me.giodev.roamplugin.commands;


import me.giodev.roamplugin.RoamPlugin;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SubCommand {
  void executeCommand(CommandSender sender, String[] args, RoamPlugin plugin);

  @NotNull String getPermission();

  @Nullable String[] getArguments();

}
