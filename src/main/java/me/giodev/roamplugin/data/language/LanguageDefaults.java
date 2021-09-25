package me.giodev.roamplugin.data.language;

import me.giodev.multiversion.MultiVersionSound;
import org.bukkit.ChatColor;

public class LanguageDefaults {

  //messages
  public static final String MESSAGE_PREFIX = ChatColor.RED + "[" + ChatColor.GOLD + "Template Plugin" + ChatColor.RED + "] ";
  public static final String NO_PERMISSION = MESSAGE_PREFIX + ChatColor.RED + "You don't have the permission needed to execute that command.";

  public static final String STILL_ON_COOLDOWN = MESSAGE_PREFIX +  ChatColor.RED + "Wait more " + ChatColor.YELLOW + "%cooldown_time% " + ChatColor.RED + "seconds before using that again.";
  public static final String SESSION_EXPIRED = MESSAGE_PREFIX + ChatColor.YELLOW + "Your roaming session has " + ChatColor.RED + "expired" + ChatColor.YELLOW + "!";

  public static final String FLYING_ERROR = MESSAGE_PREFIX + ChatColor.RED + "You cant use roam while flying.";
  public static final String INTERACTION_ERROR = MESSAGE_PREFIX + ChatColor.RED + "You can't do that while roaming.";
  public static final String COMMAND_ERROR = MESSAGE_PREFIX + ChatColor.RED + "You can't use that command while roaming.";
  public static final String MOVEMENT_ERROR = MESSAGE_PREFIX + ChatColor.RED + "You can't fly past that while roaming.";

  public static final String STARTED_ROAMING = MESSAGE_PREFIX + ChatColor.YELLOW + "You are now on roaming mode!";
  public static final String STOPPED_ROAMING = MESSAGE_PREFIX + ChatColor.YELLOW + "You are now out of roaming mode!";

  public static final String NO_MONEY = MESSAGE_PREFIX + ChatColor.RED + "You need " + ChatColor.YELLOW + "$%price%" + ChatColor.RED + "to enter roaming mode.";
  public static final String PAYED_AMOUNT = MESSAGE_PREFIX + ChatColor.YELLOW + "You have paid " + ChatColor.AQUA + "$%price%" + ChatColor.YELLOW + "to enter roaming mode!";


  //sounds

}
