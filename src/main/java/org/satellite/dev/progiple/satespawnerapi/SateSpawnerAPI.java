package org.satellite.dev.progiple.satespawnerapi;

import lombok.Getter;
import org.bukkit.Location;
import org.novasparkle.lunaspring.LunaPlugin;
import org.satellite.dev.progiple.satespawnerapi.api.APIComponent;
import org.satellite.dev.progiple.satespawnerapi.api.ASpawner;
import org.satellite.dev.progiple.satespawnerapi.self.SSAPICommand;
import org.satellite.dev.progiple.satespawnerapi.self.listeners.BlockClickHandler;
import org.satellite.dev.progiple.satespawnerapi.self.listeners.BlockWorldActionsHandler;

import java.util.HashSet;
import java.util.Set;

public final class SateSpawnerAPI extends LunaPlugin {
    @Getter private static SateSpawnerAPI instance;
    @Getter private Set<APIComponent> apis;

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        this.apis = new HashSet<>();

        this.initialize();
        this.registerTabExecutor(new SSAPICommand(), "satespawnerapi");
        this.registerListeners(new BlockClickHandler(), new BlockWorldActionsHandler());
    }

    public void registerApi(APIComponent api) {
        apis.add(api);
    }
    public ASpawner getApiSpawner(Location location) {
        APIComponent component = this.apis.stream().filter(api -> api.isRegistered(location)).findFirst().orElse(null);
        if (component != null)
            return component.getApiSpawner(location);
        return null;
    }
}
