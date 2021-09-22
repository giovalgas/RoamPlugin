package me.giodev.roamplugin.data.gui.menus;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.giodev.multiversion.XMaterial;
import me.giodev.roamplugin.data.gui.GUIButton;
import me.giodev.roamplugin.data.gui.PaginatedGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ExamplePaginatedMenu extends PaginatedGUI {

  public ExamplePaginatedMenu(Player player) {
    super(player);
  }

  @Override
  public void handleClick(InventoryClickEvent event) {

    NBTItem nbtItem = new NBTItem(event.getCurrentItem());

    if(nbtItem.hasKey(GUIButton.IDENTIFIER_KEY)) {
      switch(nbtItem.getString(GUIButton.IDENTIFIER_KEY)) {
        case "NEXT_PAGE":
          setPage(page + 1);
          break;
        case "PREVIOUS_PAGE":
          setPage(page - 1);
          break;
      }
    }
  }

  @Override
  public void setupInventoryItems() {
    inventory.setItem(47, new GUIButton(new ItemStack(XMaterial.STONE_BUTTON.parseMaterial()), "PREVIOUS_PAGE").getItemStack());
    inventory.setItem(51, new GUIButton(new ItemStack(XMaterial.STONE_BUTTON.parseMaterial()), "NEXT_PAGE").getItemStack());
    fillInventory(45, getSize());
  }

  @Override
  public @NotNull String getName() {
    return "Example Paginated Menu";
  }

  @Override
  public int getSize() {
    return 54;
  }

  @Override
  public @NotNull ArrayList<ItemStack> getPageItems() {

    ArrayList<ItemStack> items = new ArrayList<>();
    for(int i = 0; i < 128; i++) {
      if(i % 2 == 0) {
        items.add(new ItemStack(Material.DIAMOND));
      }else{
        items.add(new ItemStack(Material.EMERALD));
      }
    }
    return items;

  }

  @Override
  public int getPageSize() {
    return 45;
  }
}
