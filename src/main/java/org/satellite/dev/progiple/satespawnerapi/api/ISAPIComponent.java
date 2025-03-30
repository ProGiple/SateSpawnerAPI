package org.satellite.dev.progiple.satespawnerapi.api;

import org.bukkit.Location;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.novasparkle.lunaspring.LunaPlugin;

public interface ISAPIComponent {
    String getId();
    boolean isLoaded();
    LunaPlugin getLunaPlugin();

    void unload(Location location);
    void load(Location location);

    void onPlaceSpawner(BlockPlaceEvent e);
    void onBreakSpawner(BlockBreakEvent e);
    void onDefaultClick(PlayerInteractEvent e);
    void onClickWithEgg(PlayerInteractEvent e);
    void onInvClick(InventoryClickEvent e, Location spawnerLocation);
}
