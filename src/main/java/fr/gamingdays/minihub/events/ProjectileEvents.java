package fr.gamingdays.minihub.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileEvents implements Listener {

    @EventHandler
    public void onSnowballHit(ProjectileHitEvent ev) {
        Projectile entity = ev.getEntity();

        if (entity instanceof Snowball) {
            Location location;

            if (ev.getHitEntity() != null) {
                location = ev.getHitEntity().getLocation();
            } else if (ev.getHitBlock() != null) {
                location = ev.getHitBlock().getLocation();
            } else {
                Player player = (Player) ev.getEntity().getShooter();
                player.sendMessage(ChatColor.GOLD + "You hit something, but i dunno what is");
                return;
            }
            location.add(0, 1, 0);

            World world = entity.getWorld();

            world.playSound(location, Sound.ENTITY_WOLF_AMBIENT, 0.8f, 1f);
            world.spawnParticle(Particle.VILLAGER_HAPPY, location, 80, 0.5, 0.8, 0.5);
        }
    }
}
