package me.giodev.roamplugin.data.gui.menus;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.giodev.multiversion.XMaterial;
import me.giodev.roamplugin.data.gui.BaseGUI;
import me.giodev.roamplugin.data.gui.GUIButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ExampleMenu extends BaseGUI {

  public ExampleMenu(Player player) {
    super(player);
  }

  @Override
  public void handleClick(InventoryClickEvent event) {
    NBTItem nbtItem = new NBTItem(event.getCurrentItem());

    if(nbtItem.hasKey(GUIButton.IDENTIFIER_KEY)) {
      switch(nbtItem.getString(GUIButton.IDENTIFIER_KEY)) {
        case "TEST":
          player.sendMessage("BTN = TEST");
          break;
      }
    }
    else player.sendMessage("BTN = NULL");

  }

  @Override
  public void setupInventoryItems() {
    inventory.setItem(0, new GUIButton(new ItemStack(XMaterial.COAL_BLOCK.parseMaterial()), "TEST").getItemStack());
    fillInventory(0, getSize());
  }

  @Override
  public @NotNull String getName() {
    return "Example menu";
  }

  @Override
  public int getSize() {
    return 9;
  }
}
