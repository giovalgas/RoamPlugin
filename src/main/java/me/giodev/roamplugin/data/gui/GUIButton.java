package me.giodev.roamplugin.data.gui;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class GUIButton {

  public static final String IDENTIFIER_KEY = "BTN_IDENTIFIER";
  private ItemStack itemStack;
  private String identifier;

  public GUIButton(ItemStack itemStack, String identifier) {

    NBTItem item = new NBTItem(itemStack);
    item.setString(IDENTIFIER_KEY, identifier);

    this.itemStack = item.getItem();
    this.identifier = identifier;
  }

  public ItemStack getItemStack() {
    return itemStack;
  }

  public String getIdentifier() {
    return identifier;
  }

}
