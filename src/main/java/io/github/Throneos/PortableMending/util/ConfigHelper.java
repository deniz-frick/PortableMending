package io.github.Throneos.PortableMending.util;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigHelper {
    private static ConfigHelper instance;

    private final float durabilityPerXp;

    private ConfigHelper(Configuration config) {
        durabilityPerXp = (float) config.getDouble("DurabilityPerXp");
    }

    public static void createInstance(JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        ConfigHelper.instance = new ConfigHelper(plugin.getConfig());
    }

    public static ConfigHelper getInstance() {
        return instance;
    }

    public float getDurabilityPerXp() {
        return durabilityPerXp;
    }
}
