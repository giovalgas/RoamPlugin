package me.giodev.roamplugin.data.gui;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class PaginatedGUI extends BaseGUI{

  protected int page;
  private int pageIndex;

  public PaginatedGUI(Player player) {
    super(player);
  }

  @Override
  public void open() {
    inventory = Bukkit.createInventory(this, getSize(), getName());
    this.setupInventoryItems();
    player.openInventory(inventory);

    int loopIndex = 0;


    for(int i = 0; i < getSize(); i++) {

      pageIndex = getPageSize() * page + loopIndex;

      if(pageIndex >= getPageItems().size()) break;

      if(inventory.getItem(i) == null) {
        inventory.setItem(i, getPageItems().get(pageIndex));
        loopIndex++;
      }
    }

  }

  public abstract @NotNull ArrayList<ItemStack> getPageItems();

  public abstract int getPageSize();

  public void setPage(int page) {

    if(page >= 0 && !(page * getPageSize() + 1 >= getPageItems().size())) {
      this.page = page;
      open();
    }else {
      player.sendMessage("CAN'T GO TO THE NEXT/PREVIOUS PAGE");
    }

  }

}
