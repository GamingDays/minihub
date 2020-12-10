package fr.gamingdays.minihub;

import fr.gamingdays.minihub.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventsManager {

    private static final PluginManager manager = Bukkit.getPluginManager();

    public static void registerEvent(Plugin plugin) {
        manager.registerEvents(new BlockEvents(), plugin);
        manager.registerEvents(new ProjectileEvents(), plugin);
        manager.registerEvents(new PlayerEvents(), plugin);
        manager.registerEvents(new PlayerInventoryEvents(), plugin);
        manager.registerEvents(new PlayerInteractEvents(plugin), plugin);
    }
}
