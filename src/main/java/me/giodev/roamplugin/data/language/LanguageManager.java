package me.giodev.roamplugin.data.language;

import me.giodev.multiversion.MultiVersionSound;
import me.giodev.roamplugin.RoamPlugin;
import me.giodev.roamplugin.utils.FileManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LanguageManager {

  RoamPlugin plugin;
  YamlConfiguration langFileConfig;

  //Messages
  private String chatPrefix;
  private String noPermission;
  private String stillOnCooldown;
  private String sessionExpired;
  private String flyingError;
  private String interactionError;
  private String commandError;
  private String movementError;
  private String startedRoaming;
  private String stoppedRoaming;
  private String noMoney;
  private String payedAmount;

  //Sounds

  public LanguageManager(RoamPlugin plugin) throws InvalidConfigurationException {
    this.plugin = plugin;
    load();
  }

  private void load() throws InvalidConfigurationException {
    File langFile = new File(plugin.getDataFolder(), "language.yml");
    this.langFileConfig = new YamlConfiguration();

    if (!langFile.exists()) {
      FileManager.loadResource(plugin, "language.yml");
    }

    try {
      langFileConfig.load(langFile);
    } catch (IOException | InvalidConfigurationException e) {
      e.printStackTrace();
    }

    loadValues();

  }

  private void loadValues() throws InvalidConfigurationException {
    //Messages
    this.chatPrefix = getString(LanguageKeys.MESSAGE_PREFIX, LanguageDefaults.MESSAGE_PREFIX);
    this.noPermission = chatPrefix + getString(LanguageKeys.NO_PERMISSION, LanguageDefaults.NO_PERMISSION);
    this.stillOnCooldown = chatPrefix + getString(LanguageKeys.STILL_ON_COOLDOWN, LanguageDefaults.STILL_ON_COOLDOWN);
    this.sessionExpired = chatPrefix + getString(LanguageKeys.SESSION_EXPIRED, LanguageDefaults.SESSION_EXPIRED);
    this.flyingError = chatPrefix + getString(LanguageKeys.FLYING_ERROR, LanguageDefaults.FLYING_ERROR);
    this.interactionError = chatPrefix + getString(LanguageKeys.INTERACTION_ERROR, LanguageDefaults.INTERACTION_ERROR);
    this.commandError = chatPrefix + getString(LanguageKeys.COMMAND_ERROR, LanguageDefaults.COMMAND_ERROR);
    this.movementError = chatPrefix + getString(LanguageKeys.MOVEMENT_ERROR, LanguageDefaults.MOVEMENT_ERROR);
    this.startedRoaming = chatPrefix + getString(LanguageKeys.STARTED_ROAMING, LanguageDefaults.STARTED_ROAMING);
    this.stoppedRoaming = chatPrefix + getString(LanguageKeys.STOPPED_ROAMING, LanguageDefaults.STARTED_ROAMING);
    this.noMoney = chatPrefix + getString(LanguageKeys.NO_MONEY, LanguageDefaults.NO_MONEY);
    this.payedAmount = chatPrefix + getString(LanguageKeys.PAYED_AMOUNT, LanguageDefaults.PAYED_AMOUNT);
    //Sounds

  }

  private String getString(String p, String defaultString) throws InvalidConfigurationException {
    if(langFileConfig.isString(p)) {
      return langFileConfig.getString(p).replace('&', '§');
    } else if(langFileConfig.contains(p)){
      throw new InvalidConfigurationException("'language.yml' at path:" + p + "' is not a string");
    }else {
      langFileConfig.set(p, defaultString);
      return defaultString;
    }
  }

  @SuppressWarnings("unchecked")
  public String[] getStringList(String p, String[] defaultList) throws InvalidConfigurationException {
    if (langFileConfig.isList(p)) {
      List<?> unknownList = langFileConfig.getList(p);

      ArrayList<String> stringList = new ArrayList(unknownList.size());
      Iterator it = unknownList.iterator();

      while(it.hasNext()) {
        Object obj = it.next();
        if (obj instanceof String) {
          stringList.add((String) ((String) obj).replace("&", "§"));
        } else {
          if (!(obj instanceof Double) && !(obj instanceof Integer) && !(obj instanceof Boolean)) {
            throw new InvalidConfigurationException("'" + langFileConfig.getName() + "' at path: '" + p + "' is not a string list");
          }

          stringList.add(obj.toString());
        }
      }

      return (String[])stringList.toArray(new String[0]);
    } else if (langFileConfig.contains(p)) {
      throw new InvalidConfigurationException("'" + langFileConfig.getName() + "' at path: '" + p + "' is not a list");
    } else {
      langFileConfig.set(p, defaultList);
      return defaultList;
    }
  }

  private MultiVersionSound getSound(String p, MultiVersionSound defaultSound) throws InvalidConfigurationException {
    try{
      return MultiVersionSound.valueOf(langFileConfig.getString(p));
    }catch (IllegalArgumentException e){
      throw new InvalidConfigurationException("'Language.yml' the value specified in " + p + "is not a sound", e);
    }
  }

  public String getNoPermission() {
    return noPermission;
  }

  public String getChatPrefix() {
    return chatPrefix;
  }

  public String getStillOnCooldown() {
    return stillOnCooldown;
  }

  public String getSessionExpired() {
    return sessionExpired;
  }

  public String getFlyingError() {
    return flyingError;
  }

  public String getInteractionError() {
    return interactionError;
  }

  public String getCommandError() {
    return commandError;
  }

  public String getMovementError() {
    return movementError;
  }

  public String getStartedRoaming() {
    return startedRoaming;
  }

  public String getStoppedRoaming() {
    return stoppedRoaming;
  }

  public String getNoMoney() {
    return noMoney.replace("%price%", String.valueOf(plugin.getConfigManager().getPrice()));
  }

  public String getPayedAmount() {
    return payedAmount.replace("%price%", String.valueOf(plugin.getConfigManager().getPrice()));
  }

}
