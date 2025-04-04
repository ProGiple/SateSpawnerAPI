package org.satellite.dev.progiple.satespawnerapi.self.listeners;

import org.bukkit.Location;
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
import org.satellite.dev.progiple.satespawnerapi.api.APIComponent;
import org.satellite.dev.progiple.satespawnerapi.api.ASpawner;
import org.satellite.dev.progiple.satespawnerapi.api.menu.SpawnerMenu;
import org.satellite.dev.progiple.satespawnerapi.self.Config;

import java.util.HashSet;

public class BlockWorldActionsHandler implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Block block = e.getBlockPlaced();
        if (block.getType() != Material.SPAWNER) return;

        ASpawner spawner = SateSpawnerAPI.getInstance().getApiSpawner(block.getLocation());
        if (spawner != null)
            spawner.onPlaceSpawner(e);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (block.getType() != Material.SPAWNER) return;

        ASpawner spawner = SateSpawnerAPI.getInstance().getApiSpawner(block.getLocation());
        if (spawner != null) {
            new HashSet<>(MenuManager.getActiveInventories().values()).forEach(i -> {
                if (i instanceof SpawnerMenu spawnerMenu && spawnerMenu.getLocation().equals(block.getLocation()))
                    spawnerMenu.getPlayer().closeInventory();
            });

            spawner.onBreakSpawner(e);
        }
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
