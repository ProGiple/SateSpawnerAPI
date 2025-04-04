package org.satellite.dev.progiple.satespawnerapi.api;

import lombok.Getter;
import org.bukkit.Location;
import org.novasparkle.lunaspring.LunaPlugin;
import org.satellite.dev.progiple.satespawnerapi.self.Config;

import java.util.*;


@Getter
public final class APIComponent {
    private final Set<ASpawner> apiMap;
    private final String pluginName;
    private final boolean registerOnly;
    private final int priority;

    public APIComponent(String pluginName, int priority) {
        this.pluginName = pluginName;
        this.apiMap = new HashSet<>();
        this.registerOnly = this.pluginName.equals(Config.getString("registerOnly"));
        this.priority = priority;
    }

    public void register(ASpawner aSpawner) {
        if (!this.isRegistered(aSpawner.getSpawner().getLocation())) this.apiMap.add(aSpawner);
    }

    public void unregister(ASpawner component) {
        this.apiMap.remove(component);
    }

    public boolean isRegistered(Location location) {
        return this.apiMap.stream().anyMatch(s -> s.getSpawner().getLocation().equals(location));
    }

    public ASpawner getApiSpawner(Location location) {
        return this.apiMap.stream().filter(s -> s.getSpawner().getLocation().equals(location)).findFirst().orElse(null);
    }
}
