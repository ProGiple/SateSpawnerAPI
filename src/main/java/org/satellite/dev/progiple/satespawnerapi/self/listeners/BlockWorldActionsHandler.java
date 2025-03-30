package org.satellite.dev.progiple.satespawnerapi.self.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.satellite.dev.progiple.satespawnerapi.SateSpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.api.ISAPIComponent;
import org.satellite.dev.progiple.satespawnerapi.api.SpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.self.Config;

public class BlockWorldActionsHandler implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Block block = e.getBlockPlaced();
        if (block.getType() != Material.SPAWNER) return;

        SpawnerAPI api = SateSpawnerAPI.getINSTANCE().getSpawnerAPI();
        ISAPIComponent component = Config.getBoolean("after_click_action.registerOnly") ?
                api.getApi(Config.getString("after_click_action.id")) : null;

        if (component == null) api.getValues().forEach(a -> a.onPlaceSpawner(e));
        else component.onPlaceSpawner(e);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (block.getType() != Material.SPAWNER) return;

        SpawnerAPI api = SateSpawnerAPI.getINSTANCE().getSpawnerAPI();
        ISAPIComponent component = Config.getBoolean("after_click_action.registerOnly") ?
                api.getApi(Config.getString("after_click_action.id")) : null;

        if (component == null) api.getValues().forEach(a -> a.onBreakSpawner(e));
        else component.onBreakSpawner(e);
    }
}
