package dev.giovalgas.roamplugin.data.config;

import dev.giovalgas.roamplugin.RoamPlugin;
import org.bukkit.configuration.InvalidConfigurationException;

public class ConfigManager {

  //Plugin
  private final RoamPlugin plugin;

  //Config Values
  private String consolePrefix;
  private int maxDistance;
  private int duration;
  private int cooldown;
  private boolean disableCommands;
  private double price;
  private boolean vaultEnabled;

  public ConfigManager(RoamPlugin plugin) throws InvalidConfigurationException {
    this.plugin = plugin;
    load();
  }

  private void load() throws InvalidConfigurationException {
    ConfigFile config = new ConfigFile(plugin);
    config.load();

    this.consolePrefix = config.getString(ConfigKeys.CONSOLE_PREFIX, ConfigDefaults.CONSOLE_PREFIX);
    this.maxDistance = config.getInt(ConfigKeys.ROAM_MAX_DISTANCE, ConfigDefaults.ROAM_MAX_DISTANCE);
    this.duration = config.getInt(ConfigKeys.ROAM_DURATION, ConfigDefaults.ROAM_DURATION);
    this.cooldown = config.getInt(ConfigKeys.ROAM_COOLDOWN, ConfigDefaults.ROAM_COOLDOWN);
    this.disableCommands = config.getBoolean(ConfigKeys.ROAM_DISABLE_COMMANDS, ConfigDefaults.ROAM_DISABLE_COMMANDS);
    this.price = config.getDouble(ConfigKeys.VAULT_PRICE, ConfigDefaults.VAULT_PRICE);
    this.vaultEnabled = config.getBoolean(ConfigKeys.VAULT_ENABLED, ConfigDefaults.VAULT_ENABLED);

  }

  public void reload() throws InvalidConfigurationException {
    load();
  }

  public String getConsolePrefix() {
    return consolePrefix;
  }

  public int getMaxDistance() {
    return maxDistance;
  }

  public int getDuration() {
    return duration;
  }

  public int getCooldown() {
    return cooldown;
  }

  public boolean isDisableCommands() {
    return disableCommands;
  }

  public double getPrice() {
    return price;
  }

  public boolean isVaultEnabled() {
    return vaultEnabled;
  }

}
