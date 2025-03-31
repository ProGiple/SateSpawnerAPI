package org.satellite.dev.progiple.satespawnerapi.api;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface ISAPIComponent {
    String getId();
    int getPriority();
    boolean isLoaded();

    void unloadComponent();
    void loadComponent();

    void loadSpawner(Location location);
    void unloadSpawner(Location location);

    void onPlaceSpawner(BlockPlaceEvent e);
    void onBreakSpawner(BlockBreakEvent e);
    boolean onDefaultClick(PlayerInteractEvent e);
    void onSuperClick(PlayerInteractEvent e);
    void onClickWithEgg(Material egg, Location spawnerLocation);
    void onInvClick(InventoryClickEvent e, Location spawnerLocation);
}
