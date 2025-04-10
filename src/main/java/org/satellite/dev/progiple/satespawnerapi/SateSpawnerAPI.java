package org.satellite.dev.progiple.satespawnerapi;

import lombok.Getter;
import org.bukkit.Location;
import org.novasparkle.lunaspring.LunaPlugin;
import org.satellite.dev.progiple.satespawnerapi.api.APIComponent;
import org.satellite.dev.progiple.satespawnerapi.api.ASpawner;
import org.satellite.dev.progiple.satespawnerapi.self.SSAPICommand;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class SateSpawnerAPI extends LunaPlugin {
    @Getter private static SateSpawnerAPI instance;
    @Getter private Set<APIComponent> apiComponents;

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        this.apiComponents = new HashSet<>();

        this.initialize();
        this.registerTabExecutor(new SSAPICommand(), "satespawnerapi");
    }

    public void registerApi(APIComponent api) {
        apiComponents.add(api);
    }
    public Set<ASpawner> getRegisteredSpawners(Location location) {
        return this.apiComponents.stream().filter(api -> api.hasSpawner(location)).map(api -> api.getApiSpawner(location)).collect(Collectors.toSet());
    }
}
