package org.satellite.dev.progiple.satespawnerapi;

import lombok.Getter;
import org.novasparkle.lunaspring.LunaPlugin;
import org.satellite.dev.progiple.satespawnerapi.api.SpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.self.SSAPICommand;
import org.satellite.dev.progiple.satespawnerapi.self.listeners.BlockClickHandler;
import org.satellite.dev.progiple.satespawnerapi.self.listeners.BlockWorldActionsHandler;

public final class SateSpawnerAPI extends LunaPlugin {
    @Getter private static SateSpawnerAPI INSTANCE;
    @Getter private SpawnerAPI spawnerAPI;

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.saveDefaultConfig();
        this.spawnerAPI = new SpawnerAPI();

        this.initialize();
        this.registerTabExecutor(new SSAPICommand(), "satespawnerapi");
        this.registerListeners(new BlockClickHandler(), new BlockWorldActionsHandler());
    }
}
