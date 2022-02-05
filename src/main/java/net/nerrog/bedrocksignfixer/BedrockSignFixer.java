package net.nerrog.bedrocksignfixer;

import net.nerrog.bedrocksignfixer.listener.BlockPlaceEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.geysermc.floodgate.api.FloodgateApi;

public final class BedrockSignFixer extends JavaPlugin {

    public static FloodgateApi floodgateApi;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        config = getConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), this);
        //FloodGateAPIのインスタンスの取得
        floodgateApi = FloodgateApi.getInstance();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
