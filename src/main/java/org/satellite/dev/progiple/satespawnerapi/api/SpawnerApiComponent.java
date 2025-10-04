package org.satellite.dev.progiple.satespawnerapi.api;

import lombok.Getter;
import org.bukkit.Location;
import org.novasparkle.lunaspring.LunaPlugin;
import org.satellite.dev.progiple.satespawnerapi.self.ConfigManager;

import javax.annotation.Nullable;
import java.util.*;


@Getter
public final class SpawnerApiComponent {
    private final Set<AbstractSpawner> spawners;
    private final LunaPlugin plugin;
    private final boolean registerOnly;
    private final int priority;

    public SpawnerApiComponent(LunaPlugin plugin, int priority) {
        this.plugin = plugin;
        this.spawners = new HashSet<>();
        this.registerOnly = this.plugin.getName().equals(ConfigManager.getString("registerOnly"));
        this.priority = priority;
    }

    public SpawnerApiComponent(LunaPlugin plugin) {
        this.plugin = plugin;
        this.spawners = new HashSet<>();
        this.registerOnly = this.plugin.getName().equals(ConfigManager.getString("registerOnly"));
        this.priority = plugin.getConfig().getInt("SSAPI.priority");
    }


    public void register(AbstractSpawner abstractSpawner) {
        if (!this.hasSpawner(abstractSpawner.getSpawner().getLocation())) {
            this.spawners.add(abstractSpawner);
        }
    }

    public void unregister(AbstractSpawner spawner) {
        spawners.remove(spawner);
    }

    public boolean hasSpawner(Location location) {
        return this.spawners.stream().anyMatch(s -> s.getSpawner().getLocation().equals(location));
    }

    public boolean hasSpawner(AbstractSpawner spawner) {
        return this.spawners.contains(spawner);
    }

    @Nullable
    public AbstractSpawner getPluginSpawner(Location location) {
        return this.spawners.stream().filter(s -> s.getSpawner().getLocation().equals(location)).findFirst().orElse(null);
    }
}
