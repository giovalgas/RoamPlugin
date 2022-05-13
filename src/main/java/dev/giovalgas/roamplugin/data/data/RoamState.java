package dev.giovalgas.roamplugin.data.data;

import dev.giovalgas.roamplugin.RoamPlugin;
import dev.giovalgas.roamplugin.timer.DurationTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class RoamState {

  private Player player;
  private boolean roaming;
  private RoamingPlayerEntity roamingPlayerEntity;
  private long lastUse;
  private RoamPlugin plugin;
  private BukkitTask runnable;
  private ItemStack[] playerInventoryContents;
  private GameMode originalGameMode;

  public RoamState(Player player, RoamPlugin plugin) {
    this.player = player;
    this.plugin = plugin;
    this.roaming = false;
  }

  public void setRoaming(boolean roaming) {
    this.roaming = roaming;
    GameMode gm;

    if(roaming) {
      gm = GameMode.SPECTATOR;
      originalGameMode = player.getGameMode();
      roamingPlayerEntity = new RoamingPlayerEntity(player);
      this.lastUse = System.currentTimeMillis();

      if(plugin.getConfigManager().getDuration() != -1) {
        this.runnable = Bukkit.getScheduler().runTaskTimer(plugin, new DurationTask(plugin, player), 0L, 20L);
      }

      this.playerInventoryContents = player.getInventory().getContents();

      player.getInventory().clear();
      player.sendMessage(plugin.getLanguageManager().getStartedRoaming());

    }else{
      gm = originalGameMode;
      player.teleport(roamingPlayerEntity.getLocation());
      roamingPlayerEntity.killEntity();

      player.getInventory().setContents(this.playerInventoryContents);
      player.sendMessage(plugin.getLanguageManager().getStoppedRoaming());

      if(runnable != null) {
        runnable.cancel();
      }

    }

    player.setGameMode(gm);
  }

  public boolean isRoaming() {
    return roaming;
  }

  public Player getPlayer() {
    return player;
  }

  public long getLastUse() {
    return lastUse;
  }

  public RoamingPlayerEntity getRoamingPlayerEntity() {
    return roamingPlayerEntity;
  }

}
