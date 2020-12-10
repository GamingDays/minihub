package fr.gamingdays.minihub.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;


public class PlayerInventoryEvents implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent ev) {
        ev.setCancelled(true);
    }

    @EventHandler
    public void onMoveItem(InventoryClickEvent ev) {
        if (ev.getWhoClicked().getGameMode() != GameMode.CREATIVE) ev.setCancelled(true);
    }

    @EventHandler
    public void onSwapItem(PlayerSwapHandItemsEvent ev) {
        ev.setCancelled(true);
    }

}
