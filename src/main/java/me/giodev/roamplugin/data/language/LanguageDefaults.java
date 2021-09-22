package me.giodev.roamplugin.data.language;

import me.giodev.multiversion.MultiVersionSound;
import org.bukkit.ChatColor;

public class LanguageDefaults {

  //messages
  public static final String MESSAGE_PREFIX = ChatColor.RED + "[" + ChatColor.GOLD + "Template Plugin" + ChatColor.RED + "] ";
  public static final String HELLO_WORLD = MESSAGE_PREFIX + ChatColor.GREEN + "Hello World!";
  public static final String NO_PERMISSION = MESSAGE_PREFIX + ChatColor.RED + "You don't have the permission needed to execute that command.";

  //sounds
  public static final MultiVersionSound CLICK_SOUND = MultiVersionSound.CLICK;

}
