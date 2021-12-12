package uk.asheiou.bungeecompass;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BungeeCompass extends JavaPlugin {

    @Override
    public void onEnable() {
        if(!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
            getLogger().info("Config file not found! Creating one.");
        }
        // Register BungeeCord Plugin Channel
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getLogger().info("BungeeCord PluginChannel registered.");
        // Register events
        getServer().getPluginManager().registerEvents(new EventListeners(this), this);
        getLogger().info("Events registered.");
        // Register commands
        this.getCommand("givecompass").setExecutor(new GiveCompassCommandExecutor());
        this.getCommand("menu").setExecutor(new MenuCommandExecutor());
        getLogger().info("Load complete.");

    }
    @Override
    public void onDisable() {
        // Unregister BungeeCord Plugin Channel
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }
}
