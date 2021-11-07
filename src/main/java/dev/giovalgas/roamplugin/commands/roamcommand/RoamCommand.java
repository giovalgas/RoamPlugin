package dev.giovalgas.roamplugin.commands.roamcommand;

import dev.giovalgas.roamplugin.RoamPlugin;
import dev.giovalgas.roamplugin.commands.BaseCommand;
import dev.giovalgas.roamplugin.data.permissions.Permission;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.List;

public class RoamCommand extends BaseCommand {

  public RoamCommand(RoamPlugin plugin) {
    super(plugin);
  }

  @Override
  public void executeStockSubCommand(CommandSender sender) {
    Player player = (Player) sender;

    //noinspection UnclearExpression
    if(!((LivingEntity) player).isOnGround() && player.getGameMode() != GameMode.SPECTATOR) {
      player.sendMessage(plugin.getLanguageManager().getFlyingError());
      return;
    }

    if(plugin.getConfigManager().isVaultEnabled() && !plugin.getRoamerState(player).isRoaming()){

      double price = plugin.getConfigManager().getPrice();

      if(plugin.getEcon().getBalance(player) < price){
        player.sendMessage(plugin.getLanguageManager().getNoMoney());
        return;
      }

      plugin.getEcon().withdrawPlayer(player, price);
      player.sendMessage(plugin.getLanguageManager().getPayedAmount());

    }

    plugin.flipRoamingState(player);
  }

  @Override
  public @NotNull String getPermission() {
    return Permission.ROAM;
  }

  @Override
  public @NotNull String getName() {
    return "roam";
  }


  @Override
  public @NotNull List<String> getAliases() {
    return Arrays.asList(new String[]{"freecam", "fc"}.clone());
  }

  @Override
  public boolean isPlayerOnly() {
    return true;
  }


}
