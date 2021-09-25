package me.giodev.roamplugin.listeners;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import me.giodev.multiversion.XMaterial;
import me.giodev.roamplugin.RoamPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class RoamEntityDamageListener implements Listener {


  private final RoamPlugin plugin;

  public RoamEntityDamageListener(RoamPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onEntityDamage(EntityDamageEvent event) {

    Entity entity = event.getEntity();

    if(entity.getCustomName() == null) return;

    if(entity.getCustomName().startsWith("Roaming")) {

      Skeleton roamerEntity = (Skeleton) entity;
      SkullMeta sm = (SkullMeta) roamerEntity.getEquipment().getHelmet().getItemMeta();
      Player roamer = sm.getOwningPlayer().getPlayer();

      plugin.flipRoamingState(roamer);
      roamer.damage(event.getDamage());

    }

  }

}
