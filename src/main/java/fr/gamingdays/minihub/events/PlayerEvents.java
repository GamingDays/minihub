package fr.gamingdays.minihub.events;

import fr.gamingdays.minihub.utils.Presence;
import fr.gamingdays.minihub.utils.WorldConfig;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;


public class PlayerEvents implements Listener {

    private final ItemStack blazeRod;
    private final Location location;

    public PlayerEvents() {
        this.location = new Location(WorldConfig.getWorld(), 0.5, 6, 0.5);

        this.blazeRod = new ItemStack(Material.BLAZE_ROD);
        ItemMeta blazeRodC = blazeRod.getItemMeta();
        blazeRodC.setDisplayName("woof-inator");
        blazeRodC.setLore(Arrays.asList("woof", "woof", "INATOR"));
        blazeRodC.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        blazeRodC.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        blazeRod.setItemMeta(blazeRodC);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent ev) {
        Presence.join(ev);

        Player player = ev.getPlayer();

        setPlayerInventory(player);
        player.teleport(location);
        player.setGameMode(GameMode.ADVENTURE);
        player.sendTitle(ChatColor.GOLD + "Welcome to the world", ChatColor.LIGHT_PURPLE + "Gaming Days", 0, 80, 10);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent ev) {
        Presence.leave(ev);
    }

    @EventHandler
    public void onExitBorderY(PlayerMoveEvent ev) {
        Player player = ev.getPlayer();
        double y = player.getLocation().getY();

        if (y <= 0 || y >= 255 ) player.teleport(location);
    }

    private void setPlayerInventory(Player player) {
        Inventory inventory = player.getInventory();

        inventory.clear();
        inventory.setItem(8, blazeRod);
    }

}
