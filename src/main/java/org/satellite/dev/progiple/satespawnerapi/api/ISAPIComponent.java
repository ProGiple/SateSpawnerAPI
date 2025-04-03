package org.satellite.dev.progiple.satespawnerapi.api;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

@Getter
@Setter
public abstract class ISAPIComponent {
    private boolean loaded;
    public abstract String getId();
    public abstract int getPriority();

    public abstract void unloadComponent();
    public abstract void loadComponent();

    public abstract void loadSpawner(Location location);
    public abstract void unloadSpawner(Location location);

    public abstract void onPlaceSpawner(BlockPlaceEvent e);
    public abstract void onBreakSpawner(BlockBreakEvent e);
    public abstract boolean onDefaultClick(PlayerInteractEvent e);
    public abstract void onSuperClick(PlayerInteractEvent e);
    public abstract void onClickWithEgg(Material egg, Location spawnerLocation);
    public abstract void onInvClick(InventoryClickEvent e, Location spawnerLocation);
}
