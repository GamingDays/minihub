package fr.gamingdays.minihub;

import fr.gamingdays.minihub.utils.ColorsConsole;
import fr.gamingdays.minihub.utils.Presence;
import fr.gamingdays.minihub.utils.WorldConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minihub extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        Presence.initialize(this);
        WorldConfig.initialize(this);
        EventsManager.registerEvent(this);
        System.out.println(ColorsConsole.PURPLE + "[Minihub] " + ColorsConsole.GREEN + "Enable" + ColorsConsole.RESET);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(ColorsConsole.PURPLE + "[Minihub] " + ColorsConsole.RED + "Disable" + ColorsConsole.RESET);
    }
}
