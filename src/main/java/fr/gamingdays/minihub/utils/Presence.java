package fr.gamingdays.minihub.utils;

import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Server;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class Presence {

    private static long presence;
    private static long maxPlayer;

    public static void initialize(Plugin plugin) {
        Server server = plugin.getServer();

        server.getWorld("world").setDifficulty(Difficulty.PEACEFUL);

        presence = server.getOnlinePlayers().size();
        maxPlayer = server.getMaxPlayers();

        System.out.println(ColorsConsole.GREEN + counter() + ColorsConsole.RESET);
    }

    public static void join(PlayerJoinEvent ev) {
        presence += 1;

        ev.setJoinMessage(ChatColor.GREEN + counter());
        System.out.println(ColorsConsole.GREEN + counter() + ColorsConsole.RESET);
    }

    public static void leave(PlayerQuitEvent ev) {
        presence -= 1;

        ev.setQuitMessage(ChatColor.RED + counter());
    }

    public static long getPresence() {
        return presence;
    }

    public static long getMaxPlayer() {
        return maxPlayer;
    }

    private static String counter() {
        String s = getPresence() > 1 ? "s" : "";
        return "[" + getPresence() + "/" + getMaxPlayer() + "] joueur" + s + " connecter";
    }
}