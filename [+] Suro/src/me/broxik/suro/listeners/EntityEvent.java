package me.broxik.suro.listeners;

import me.broxik.suro.main.ZombieManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EntityEvent implements Listener {

    @EventHandler
    public void onEntityCombust(EntityCombustEvent e){
        if(e.getEntity() instanceof Zombie) e.setCancelled(true);
    }

    @EventHandler
    public void deathZombies(EntityDeathEvent e){
        Entity entity = e.getEntity();
        String name = ChatColor.stripColor(entity.getName());

        if(entity instanceof Zombie && ZombieManager.contains(name)) {
            Inventory inventory = ZombieManager.get(name).getInventory();
            ZombieManager.get(name).setDead(true);

            for (ItemStack items : inventory.getContents()) {
                if(items == null) continue;
                entity.getWorld().dropItemNaturally(entity.getLocation(), items);
                inventory.remove(items);
            }
        }
    }
}
