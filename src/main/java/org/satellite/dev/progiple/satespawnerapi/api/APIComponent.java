package org.satellite.dev.progiple.satespawnerapi.api;

import lombok.Getter;
import org.bukkit.Location;
import org.satellite.dev.progiple.satespawnerapi.self.Config;

import java.util.*;


@Getter
public final class APIComponent {
    private final Set<ASpawner> spawners;
    private final String pluginName;
    private final boolean registerOnly;
    private final int priority;

    public APIComponent(String pluginName, int priority) {
        this.pluginName = pluginName;
        this.spawners = new HashSet<>();
        this.registerOnly = this.pluginName.equals(Config.getString("registerOnly"));
        this.priority = priority;
    }

    public void register(ASpawner aSpawner) {
        if (!this.hasSpawner(aSpawner.getSpawner().getLocation())) this.spawners.add(aSpawner);
    }

    public void unregister(ASpawner component) {
        this.spawners.remove(component);
    }

    public boolean hasSpawner(Location location) {
        return this.spawners.stream().anyMatch(s -> s.getSpawner().getLocation().equals(location));
    }

    public ASpawner getApiSpawner(Location location) {
        return this.spawners.stream().filter(s -> s.getSpawner().getLocation().equals(location)).findFirst().orElse(null);
    }
}
