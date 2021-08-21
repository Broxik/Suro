package me.broxik.suro.listeners;

import me.broxik.suro.main.Zombie;
import me.broxik.suro.main.ZombieManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        if(!ZombieManager.contains(player.getName())) return;

        Zombie zombie = ZombieManager.get(player.getName());

        if(zombie.isDead()) {
            player.getInventory().clear();
            zombie.setDead(false);
        }

        player.teleport(zombie.getZombie().getLocation());
        zombie.remove();

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        ZombieManager.set(e.getPlayer());
    }
}
