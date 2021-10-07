package me.giodev.roamplugin.data.data;

import com.cryptomorin.xseries.SkullUtils;
import com.cryptomorin.xseries.XEntity;
import com.cryptomorin.xseries.XItemStack;
import com.cryptomorin.xseries.XMaterial;
import de.tr7zw.changeme.nbtapi.NBTContainer;
import de.tr7zw.changeme.nbtapi.NBTEntity;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
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

    NBTEntity nbtEntity = new NBTEntity(entity);
    nbtEntity.mergeCompound(new NBTContainer("{Invulnerable:1,NoAI:1}"));

    entity.setCustomName("Roaming - " + player.getName());
    entity.getEquipment().setArmorContents(player.getEquipment().getArmorContents());
    entity.getEquipment().setItemInHand(player.getItemInHand());
    entity.getEquipment().setHelmet(SkullUtils.getSkull(this.owner.getUniqueId()));

  }

  public void killEntity() {
    entity.remove();
  }

  public Location getLocation() {
    return loc;
  }

}
