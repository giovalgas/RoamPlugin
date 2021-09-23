package me.giodev.roamplugin.data.data;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class RoamState {

  private Player player;
  private boolean roaming;
  private RoamingPlayerEntity roamingPlayerEntity;
  private long lastUse;

  public RoamState(Player player) {
    this.player = player;
    this.roaming = false;
  }

  public void setRoaming(boolean roaming) {
    this.roaming = roaming;
    GameMode gm;

    if(roaming) {
      gm = GameMode.SPECTATOR;
      roamingPlayerEntity = new RoamingPlayerEntity(player);
      this.lastUse = System.currentTimeMillis();

    }else{
      gm = GameMode.SURVIVAL;
      player.teleport(roamingPlayerEntity.getLocation());
      roamingPlayerEntity.killEntity();
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
