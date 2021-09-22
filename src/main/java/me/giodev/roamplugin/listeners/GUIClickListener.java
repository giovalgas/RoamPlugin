package me.giodev.roamplugin.listeners;

import me.giodev.roamplugin.RoamPlugin;
import me.giodev.roamplugin.data.gui.BaseGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class GUIClickListener implements Listener {

  private RoamPlugin plugin;

  public GUIClickListener(RoamPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {

    InventoryHolder holder = event.getInventory().getHolder();

    if(holder instanceof BaseGUI) {

      event.setCancelled(true);
      plugin.getLanguageManager().getClickSound().playSound((Player) event.getWhoClicked());

      if(event.getCurrentItem() == null) return;

      BaseGUI gui = (BaseGUI) holder;

      gui.handleClick(event);

    }

  }


}
