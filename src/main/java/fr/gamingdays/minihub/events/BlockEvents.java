package fr.gamingdays.minihub.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvents implements Listener {

    @EventHandler
    public void onDestroy(BlockBreakEvent ev) {
        if (ev.getPlayer().getGameMode() != GameMode.CREATIVE) ev.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent ev) {

        if (ev.getPlayer().getGameMode() != GameMode.CREATIVE) ev.setCancelled(true);
    }
}
