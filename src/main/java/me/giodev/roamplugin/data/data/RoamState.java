package me.giodev.roamplugin.data.data;

import org.bukkit.entity.Player;

public class RoamState {

  private Player player;
  private boolean roaming;


  public RoamState(Player player) {
    this.player = player;
    this.roaming = false;
  }

  public void setRoaming(boolean bool) {
    this.roaming = bool;
  }

  public boolean isRoaming() {
    return roaming;
  }

}
