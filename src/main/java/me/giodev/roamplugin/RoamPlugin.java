package me.giodev.roamplugin;

import me.giodev.roamplugin.commands.BaseCommand;
import me.giodev.roamplugin.commands.roamcommand.RoamCommand;
import me.giodev.roamplugin.data.config.ConfigManager;
import me.giodev.roamplugin.data.data.RoamState;
import me.giodev.roamplugin.data.language.LanguageManager;
import me.giodev.roamplugin.utils.LoggerUtil;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;


public final class RoamPlugin extends JavaPlugin {

  private ConfigManager configManager;
  private LanguageManager languageManager;
  private LoggerUtil log;
  private HashMap<UUID, RoamState> roamerState = new HashMap<>();

  @Override
  public void onEnable(){
    //Load config, language & logger
    loadConfig();
    loadLang();
    log = new LoggerUtil(this);

    //Commands & Events
    loadCommands();
    loadEvents();

    log.info("Plugin fully started!");
  }

  public void flipRoamingState(@NotNull Player player) {

    RoamState rs = getRoamerState(player);
    rs.setRoaming(!rs.isRoaming());

    roamerState.put(player.getUniqueId(), rs);
  }

  public RoamState getRoamerState(Player player) {
    return roamerState.get(player.getUniqueId()) != null ? roamerState.get(player.getUniqueId()) : new RoamState(player);
  }

  private void loadEvents() {
    PluginManager pm = getServer().getPluginManager();
  }

  private void loadCommands() {
    loadCommand(new RoamCommand(this));
  }

  private void loadCommand(BaseCommand command) {
    getCommand(command.getName()).setExecutor(command);
    getCommand(command.getName()).setTabCompleter(command);
    getCommand(command.getName()).setAliases(command.getAliases());
  }

  private void loadConfig(){
    try {
      this.configManager = new ConfigManager(this);
    } catch (InvalidConfigurationException e) {
      e.printStackTrace();
    }
  }

  private void loadLang(){
    try {
      this.languageManager = new LanguageManager(this);
    } catch (InvalidConfigurationException e) {
      e.printStackTrace();
    }
  }

  public LoggerUtil getLog() { return log; }
  public ConfigManager getConfigManager() { return configManager; }
  public LanguageManager getLanguageManager() { return languageManager; }

}