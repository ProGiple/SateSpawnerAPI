package org.satellite.dev.progiple.satespawnerapi.self.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.novasparkle.lunaspring.API.Menus.MenuManager;
import org.satellite.dev.progiple.satespawnerapi.SateSpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.api.ISAPIComponent;
import org.satellite.dev.progiple.satespawnerapi.api.SpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.api.menu.SpawnerMenu;
import org.satellite.dev.progiple.satespawnerapi.self.Config;

import java.util.HashMap;
import java.util.HashSet;

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

        new HashSet<>(MenuManager.getActiveInventories().values())
                .forEach(i -> {
                    if (i instanceof SpawnerMenu spawnerMenu &&
                            spawnerMenu.getLocation().equals(block.getLocation())) spawnerMenu.getPlayer().closeInventory();});

        if (component == null) api.getValues().forEach(a -> a.onBreakSpawner(e));
        else component.onBreakSpawner(e);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().removeIf(block -> block.getType() == Material.SPAWNER);
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.WITHER && event.getBlock().getType() == Material.SPAWNER) {
            event.setCancelled(true);
        }
    }
}
