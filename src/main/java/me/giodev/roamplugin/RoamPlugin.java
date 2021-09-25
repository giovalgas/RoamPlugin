package me.giodev.roamplugin;

import me.giodev.roamplugin.commands.BaseCommand;
import me.giodev.roamplugin.commands.roamcommand.RoamCommand;
import me.giodev.roamplugin.data.config.ConfigManager;
import me.giodev.roamplugin.data.data.RoamState;
import me.giodev.roamplugin.data.language.LanguageManager;
import me.giodev.roamplugin.listeners.RoamActionListener;
import me.giodev.roamplugin.listeners.RoamCommandListener;
import me.giodev.roamplugin.listeners.RoamEntityDamageListener;
import me.giodev.roamplugin.listeners.RoamMovementListener;
import me.giodev.roamplugin.utils.LoggerUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;


public final class RoamPlugin extends JavaPlugin {

  private ConfigManager configManager;
  private LanguageManager languageManager;
  private LoggerUtil log;
  private HashMap<UUID, RoamState> roamerState = new HashMap<>();
  private Economy econ = null;

  @Override
  public void onEnable(){
    //Load config, language & logger
    loadConfig();
    loadLang();

    log = new LoggerUtil(this);

    //Commands & Events
    loadCommands();
    loadEvents();

    if(this.getConfigManager().isVaultEnabled()) setupEconomy();

    log.info("Plugin fully started!");
  }

  public void flipRoamingState(@NotNull Player player) {

    RoamState rs = getRoamerState(player);

    int cooldown = (int) ((System.currentTimeMillis() - rs.getLastUse()) / 1000);

    if((cooldown < this.getConfigManager().getCooldown() && !rs.isRoaming())){
      player.sendMessage(this.getLanguageManager().getStillOnCooldown().replace("%cooldown_time%", String.valueOf(this.getConfigManager().getCooldown() - cooldown)));
      return;
    }

    rs.setRoaming(!rs.isRoaming());
    roamerState.put(player.getUniqueId(), rs);

  }

  public RoamState getRoamerState(Player player) {

    if(roamerState.get(player.getUniqueId()) == null) {
      roamerState.put(player.getUniqueId(), new RoamState(player, this));
    }

    return roamerState.get(player.getUniqueId());
  }

  private void setupEconomy() {
    if (getServer().getPluginManager().getPlugin("Vault") == null) {
      this.getLog().severe("No vault dependency found, disabling this plugin.");
      this.getLog().severe("If you wish to run this plugin without an economy plugin, set 'vault-hook.enabled' to 'false'");
      this.getServer().getPluginManager().disablePlugin(this);
    }
    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
    if (rsp == null) {
      this.getLog().severe("No economy plugins found, disabling this plugin.");
      this.getLog().severe("If you wish to run this plugin without an economy plugin, set 'vault-hook.enabled' to 'false'");
      this.getServer().getPluginManager().disablePlugin(this);
    }

    this.getLog().info("Loaded economy");
    this.econ = rsp.getProvider();
  }

  private void loadEvents() {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new RoamActionListener(this), this);
    pm.registerEvents(new RoamCommandListener(this), this);
    pm.registerEvents(new RoamMovementListener(this), this);
    pm.registerEvents(new RoamEntityDamageListener(this), this);
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

  public Economy getEcon() {
    return econ;
  }

  public LoggerUtil getLog() {
    return log;
  }

  public ConfigManager getConfigManager() {
    return configManager;
  }

  public LanguageManager getLanguageManager() {
    return languageManager;
  }
}
