package me.broxik.suro.main;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.broxik.suro.Main;

public class Zombie {

    private final Player player;
    private org.bukkit.entity.Zombie zombie;
    private final Inventory inventory;
    private boolean dead;

    public Zombie(Player player, Inventory inventory){
        this.player    = player;
        this.inventory = inventory;
        this.dead      = false;

        spawn();
    }

    public void spawn(){
        this.zombie = (org.bukkit.entity.Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);

        getZombie().setCustomName(Main.replace(Main.getConfigFile().getString("Name"), player.getName()));
        getZombie().setHealth(20.0);
        getZombie().setBaby(false);
        getZombie().setCustomNameVisible(true);

        new BukkitRunnable() {
            @Override
            public void run() {
                zombie.remove();
                Inventory inventory = ZombieManager.get(player.getName()).getInventory();
                setDead(true);

                for (ItemStack items : inventory.getContents()) {
                    if(items == null) continue;
                    zombie.getWorld().dropItemNaturally(zombie.getLocation(), items);
                    inventory.remove(items);
                }
            }
        }.runTaskLater(Main.getInstance(), Main.getConfigFile().getInt("Time") * 20L);
    }

    public void remove(){
        ZombieManager.remove(getPlayer());
        zombie.remove();
    }

    public org.bukkit.entity.Zombie getZombie() {
        return zombie;
    }

    public Player getPlayer() {
        return player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }
}
