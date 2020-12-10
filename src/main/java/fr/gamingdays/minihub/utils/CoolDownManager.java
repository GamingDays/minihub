package fr.gamingdays.minihub.utils;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

public class CoolDownManager {

    private final Map<UUID, Float> coolDowns = new HashMap<>();
    private final Plugin plugin;
    private final DecimalFormat decimal;

    public CoolDownManager(Plugin plugin) {
        this.plugin = plugin;
        decimal = new DecimalFormat("0.0");
    }

    public void setPlayerCoolDown(UUID player, float time) {
        if (time < 0.1f) {
            coolDowns.remove(player);
        } else {
            coolDowns.put(player, time);
        }
    }

    public float getPlayerCoolDown(UUID player) {
        return coolDowns.getOrDefault(player, 0f);
    }

    public String getFormattedCoolDown(UUID player) {
        float coolDown = coolDowns.getOrDefault(player, 0f);
        return decimal.format(coolDown);
    }

    public void run(UUID player) {
        new BukkitRunnable() {

            @Override
            public void run() {
                float timeLeft = getPlayerCoolDown(player);
                setPlayerCoolDown(player, timeLeft - 0.1f);

                if (timeLeft == 0) {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 1, 1);
    }

    public <T> void run(UUID player, Callable<T> callback) {
        new BukkitRunnable() {

            @Override
            public void run() {
                try {
                    callback.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                float timeLeft = getPlayerCoolDown(player);
                setPlayerCoolDown(player, timeLeft - 0.1f);

                if (timeLeft == 0) {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 1, 1);
    }

}
