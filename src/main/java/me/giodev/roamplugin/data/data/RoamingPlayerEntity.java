package me.giodev.roamplugin.data.data;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import me.giodev.multiversion.XMaterial;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class RoamingPlayerEntity {

  private Location loc;
  private Skeleton entity;
  private Player owner;

  public RoamingPlayerEntity(Player player) {
    this.loc = player.getLocation();
    this.owner = player;

    loc.setPitch(0);
    loc.setYaw(0);

    this.entity = (Skeleton) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);

    entity.setAI(false);
    entity.setInvulnerable(true);
    entity.setCustomName("Roaming - " + player.getName());
    entity.getEquipment().setArmorContents(player.getEquipment().getArmorContents());
    entity.getEquipment().setItemInMainHand(player.getItemInHand());
    entity.getEquipment().setHelmet(this.getPlayerSkull());

  }

  public void killEntity() {
    entity.remove();
  }

  public Location getLocation() {
    return loc;
  }

  private ItemStack getPlayerSkull() {
    ItemStack skull = new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial());
    SkullMeta meta = (SkullMeta) skull.getItemMeta();
    meta.setOwningPlayer(owner);
    skull.setItemMeta(meta);
    return skull;
  }

}
