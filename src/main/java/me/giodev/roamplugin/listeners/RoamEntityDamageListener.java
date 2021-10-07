package me.giodev.roamplugin.listeners;

import com.cryptomorin.xseries.SkullUtils;
import de.tr7zw.changeme.nbtapi.NBTItem;
import me.giodev.roamplugin.RoamPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
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

    plugin.getLog().info(event.getEntity().getType().toString());

    Entity entity = event.getEntity();

    if(entity.getCustomName() == null) return;

    if(entity.getCustomName().startsWith("Roaming")) {

      Skeleton roamerEntity = (Skeleton) entity;
      NBTItem nbtItem = new NBTItem(roamerEntity.getEquipment().getHelmet());

      plugin.getLog().info(nbtItem.getString("ROAMER_UUID"));

      if(!nbtItem.hasKey("ROAMER_UUID")) return;

      Player roamer = Bukkit.getPlayer(UUID.fromString(nbtItem.getString("ROAMER_UUID")));

      plugin.flipRoamingState(roamer);
      roamer.damage(event.getDamage());

    }

  }

}
