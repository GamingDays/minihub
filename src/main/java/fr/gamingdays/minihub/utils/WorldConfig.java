package fr.gamingdays.minihub.utils;

import org.bukkit.Difficulty;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class WorldConfig {

    private static World world;

    public static void initialize(Plugin plugin) {
        Server server = plugin.getServer();
        world = server.getWorld("world");

        if (world != null) {
            world.setDifficulty(Difficulty.PEACEFUL);
            world.setPVP(true);
            world.setGameRuleValue("doWeatherCycle", "false");
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setTime(6000L);
        } else {
            server.shutdown();
            System.out.println(ColorsConsole.RED + "====================================" + ColorsConsole.RESET);
            System.out.println(ColorsConsole.RED + "[MiniHub] Aucun monde n'a ete trouve" + ColorsConsole.RESET);
            System.out.println(ColorsConsole.RED + "====================================" + ColorsConsole.RESET);
        }

    }

    public static World getWorld() {
        return world;
    }
}
