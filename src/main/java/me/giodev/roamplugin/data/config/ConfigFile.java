package me.giodev.roamplugin.data.config;

import me.giodev.roamplugin.RoamPlugin;
import me.giodev.roamplugin.utils.FileManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigFile {

  File configFile;
  RoamPlugin plugin;
  FileConfiguration config;

  public ConfigFile(RoamPlugin plugin){
    this.plugin = plugin;
  }

  public void load(){
    this.configFile = new File(plugin.getDataFolder(), "config.yml");

    if(!configFile.exists()){
      FileManager.loadResource(plugin, "config.yml");
    }

    this.config = plugin.getConfig();
  }

  public void save() throws IOException {
    try{
      config.save(this.configFile);
    } catch (IOException e) {
      throw new IOException("Failed to load config.yml", e);
    }
  }

  public boolean getBoolean(String p, boolean defaultBoolean) throws InvalidConfigurationException {
    if(config.isBoolean(p)) {
      return config.getBoolean(p);
    } else if(config.contains(p)){
      throw new InvalidConfigurationException("'" + configFile.getName() + "' at path: '" + p +"' is not a boolean");
    }else {
      config.set(p, defaultBoolean);
      return defaultBoolean;
    }
  }

  public long getLong(String p, long defaultLong) throws InvalidConfigurationException{
    if(config.isLong(p)) {
      return config.getLong(p);
    } else if(config.contains(p)){
      throw new InvalidConfigurationException("'" + configFile.getName() + "' at path: '" + p +"' is not a long");
    }else {
      config.set(p, defaultLong);
      return defaultLong;
    }
  }

  public int getInt(String p, int defaultInt) throws InvalidConfigurationException {
    if(config.isInt(p)) {
      return config.getInt(p);
    } else if(config.contains(p)){
      throw new InvalidConfigurationException("'" + configFile.getName() + "' at path: '" + p +"' is not an int");
    }else {
      config.set(p, defaultInt);
      return defaultInt;
    }
  }

  public String getString(String p, String defaultString) throws InvalidConfigurationException {
    if(config.isString(p)) {
      return config.getString(p);
    } else if(config.contains(p)){
      throw new InvalidConfigurationException("'" + configFile.getName() + "' at path: '" + p +"' is not a string");
    }else {
      config.set(p, defaultString);
      return defaultString;
    }
  }

  public String[] getStringList(String p, String[] defaultList) throws InvalidConfigurationException {
    if (config.isList(p)) {
      List<?> unknownList = config.getList(p);
      ArrayList<String> stringList = new ArrayList<>(unknownList.size());
      for (Object obj : unknownList) {
        if (obj instanceof String) {
          stringList.add((String) obj);
        } else if (obj instanceof Double || obj instanceof Integer || obj instanceof Boolean) {
          stringList.add(obj.toString());
        } else {
          throw new InvalidConfigurationException("'" + configFile.getName() + "' at path: '" + p +"' is not a string list");
        }
      }
      return stringList.toArray(new String[0]);
    } else if (config.contains(p)) {
      throw new InvalidConfigurationException("'" + configFile.getName() + "' at path: '" + p +"' is not a list");
    } else {
      config.set(p, defaultList);
      return defaultList;
    }
  }

  public void rewriteValue(String p, Object newValue){
    config.set(p, newValue);
  }


}
