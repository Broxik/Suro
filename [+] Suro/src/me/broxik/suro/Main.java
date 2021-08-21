package me.broxik.suro;

import me.broxik.suro.config.Config;
import me.broxik.suro.listeners.EntityEvent;
import me.broxik.suro.listeners.PlayerEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;
    public static Config configFile;

    @Override
    public void onEnable() {
        instance = this;

        configFile = new Config(this, getDataFolder() + "/config.yml", "config.yml");

        registerListeners();
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new EntityEvent(), this);
        pluginManager.registerEvents(new PlayerEvent(), this);
    }

    public static String replace(String message, String playerName){
        return ChatColor.translateAlternateColorCodes('&', message).replace("%player%", playerName);
    }

    public static YamlConfiguration getConfigFile() {
        return configFile.get();
    }

    public static Main getInstance() {
        return instance;
    }
}
