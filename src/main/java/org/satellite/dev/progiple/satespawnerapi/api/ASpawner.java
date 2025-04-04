package org.satellite.dev.progiple.satespawnerapi.api;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.novasparkle.lunaspring.API.Menus.IMenu;
import org.novasparkle.lunaspring.API.Menus.MenuManager;

@Getter
public abstract class ASpawner {
    private final CreatureSpawner spawner;
    private final APIComponent component;

    public ASpawner(Location location, APIComponent component) {
        this.spawner = (CreatureSpawner) location.getBlock().getState();
        this.component = component;
    }
    public Location getLocation() {
        return this.spawner.getLocation();
    }

    public abstract void onPlaceSpawner(BlockPlaceEvent e);
    public abstract void onBreakSpawner(BlockBreakEvent e);
    public abstract void onBlockClick(PlayerInteractEvent e);

    public abstract void onInvClick(InventoryClickEvent e);
    public abstract void onClickWithEgg(Material egg);
}
