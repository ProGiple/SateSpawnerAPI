package org.satellite.dev.progiple.satespawnerapi.self;

import lombok.experimental.UtilityClass;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.novasparkle.lunaspring.API.configuration.IConfig;
import org.satellite.dev.progiple.satespawnerapi.SateSpawnerAPI;

@UtilityClass
public class ConfigManager {
    private final IConfig config;

    static {
        config = new IConfig(SateSpawnerAPI.getInstance());
    }


    public void reload() {
        config.reload(SateSpawnerAPI.getInstance());
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public ConfigurationSection getSection(String path) {
        return config.getSection(path);
    }

    public void sendMessage(CommandSender sender, String id, String... replacements) {
        config.sendMessage(sender, id, replacements);
    }
}
