package org.satellite.dev.progiple.satespawnerapi;

import lombok.Getter;
import org.bukkit.Location;
import org.novasparkle.lunaspring.LunaPlugin;
import org.satellite.dev.progiple.satespawnerapi.api.SpawnerApiComponent;
import org.satellite.dev.progiple.satespawnerapi.api.AbstractSpawner;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class SateSpawnerAPI extends LunaPlugin {
    @Getter private static SateSpawnerAPI instance;
    @Getter private Set<SpawnerApiComponent> spawnerApiComponents;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        this.saveDefaultConfig();
        this.spawnerApiComponents = new HashSet<>();
    }

    public void registerApi(SpawnerApiComponent component) {
        spawnerApiComponents.add(component);
    }

    public Set<AbstractSpawner> getRegisteredSpawners(Location location) {
        return this.spawnerApiComponents.stream().filter(api -> api.hasSpawner(location)).map(api -> api.getPluginSpawner(location)).collect(Collectors.toSet());
    }
}
