package me.giodev.roamplugin.timer;

import me.giodev.roamplugin.RoamPlugin;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class DurationTask implements Runnable {

  private Player player;
  private RoamPlugin plugin;
  private int secondsRemaining;

  public DurationTask(RoamPlugin plugin, Player player) {
    this.plugin = plugin;
    this.player = player;
    this.secondsRemaining = plugin.getConfigManager().getDuration();
  }

  @Override
  public void run() {

    if(secondsRemaining == 0){
      this.onDisable();
    }

    secondsRemaining--;
  }

  public void onDisable() {
    plugin.flipRoamingState(player);
    player.sendMessage(plugin.getLanguageManager().getSessionExpired());
  }

}


