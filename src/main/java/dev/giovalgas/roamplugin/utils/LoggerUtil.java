package dev.giovalgas.roamplugin.utils;

import dev.giovalgas.roamplugin.RoamPlugin;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class LoggerUtil {

  private Logger BukkitLogger;
  private String consolePrefix;

  public LoggerUtil(RoamPlugin plugin) {
    this.consolePrefix = plugin.getConfigManager().getConsolePrefix();
    this.BukkitLogger = Bukkit.getServer().getLogger();
  }

  public void info(String message) {
    BukkitLogger.info(consolePrefix + message);
  }

  public void warning(String message) {
    BukkitLogger.warning(consolePrefix + message);
  }

  public void severe(String message) {
    BukkitLogger.severe(consolePrefix + message);
  }
}
