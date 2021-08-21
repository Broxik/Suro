package me.broxik.suro.main;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ZombieManager {

    public static Map<String, Zombie> zombie = new HashMap<>();

    public static void set(Player player){
        zombie.put(player.getName(), new Zombie(player, player.getInventory()));
    }

    public static Zombie get(String playerName){
        return zombie.get(playerName);
    }

    public static void remove(Player player){
        zombie.remove(player.getName());
    }

    public static boolean contains(String playerName){
        return zombie.containsKey(playerName);
    }
}
