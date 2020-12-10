package fr.gamingdays.minihub.events;

import fr.gamingdays.minihub.utils.CoolDownManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.UUID;
import java.util.concurrent.Callable;

public class PlayerInteractEvents implements Listener {

    private final CoolDownManager coolDown;

    public PlayerInteractEvents(Plugin plugin) {
        this.coolDown = new CoolDownManager(plugin);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent ev) {
        Action action = ev.getAction();
        Material item = ev.getPlayer().getInventory().getItemInMainHand().getType();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (item == Material.BLAZE_ROD) {
                onUseBlazeRod(ev);
            }
        }
    }

    @EventHandler
    public void onHit(EntityDamageEvent ev) {
        ev.setCancelled(true);

        if (ev.getEntity() instanceof Player && ev.getCause() == DamageCause.ENTITY_ATTACK) {
            Player player = (Player) ev.getEntity();
            PotionEffect potion = new PotionEffect(PotionEffectType.LEVITATION, 80, 2, false, false, Color.RED);
            player.addPotionEffect(potion);
        }
    }

    @EventHandler
    public void onBlazeRodInHand(PlayerItemHeldEvent ev) {
        int slot = ev.getNewSlot();
        Player player = ev.getPlayer();
        UUID uuid = player.getUniqueId();
        ItemStack itemInHand = player.getInventory().getItem(slot);

        double timeLeft = coolDown.getPlayerCoolDown(uuid);

        if (itemInHand == null) {
            TextComponent text = new TextComponent("");
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, text);
            return;
        }

        if (itemInHand.getType() == Material.BLAZE_ROD && timeLeft == 0) {
            TextComponent text_READY = new TextComponent(ChatColor.DARK_GREEN + "■■ READY ■■");
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, text_READY);
        }
    }

    private void onUseBlazeRod(PlayerInteractEvent ev) {
        Player player = ev.getPlayer();
        UUID uuid = player.getUniqueId();

        float timeLeft = coolDown.getPlayerCoolDown(uuid);

        if (timeLeft == 0) {
            Vector direction = player.getLocation().getDirection();
            player.launchProjectile(Snowball.class, direction.multiply(1.2));

            coolDown.setPlayerCoolDown(uuid, 5f);
            coolDown.run(uuid, (Callable<Void>) () -> {
                callBackBlazeRod(player);
                return null;
            });
        }
    }

    private void callBackBlazeRod(Player player) {
        player = player.getServer().getPlayer(player.getUniqueId());
        float timeLeft = coolDown.getPlayerCoolDown(player.getUniqueId());
        String timeLeftFormatted = coolDown.getFormattedCoolDown(player.getUniqueId());
        Material itemInHand = player.getInventory().getItemInMainHand().getType();


        if (timeLeft > 0.1 && itemInHand == Material.BLAZE_ROD) {
            TextComponent text_TimeLeft = new TextComponent(ChatColor.DARK_RED + "TimeLeft: " + ChatColor.GOLD + timeLeftFormatted);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, text_TimeLeft);

        } else if (itemInHand == Material.BLAZE_ROD) {
            TextComponent text_READY = new TextComponent(ChatColor.DARK_GREEN + "■■ READY ■■");
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, text_READY);
        }

    }
}

