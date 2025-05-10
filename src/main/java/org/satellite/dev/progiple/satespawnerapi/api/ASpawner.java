package org.satellite.dev.progiple.satespawnerapi.api;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.novasparkle.lunaspring.API.menus.IMenu;
import org.novasparkle.lunaspring.API.menus.MenuManager;
import org.satellite.dev.progiple.satespawnerapi.self.Config;
import org.satellite.dev.progiple.satespawnerapi.self.menu.SSAPIMenu;

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
    public boolean checkAPI(String plugin) {
        return plugin.equals(this.component.getPluginName());
    }
    public abstract void onPlaceSpawner(BlockPlaceEvent e);
    public abstract void onBreakSpawner(BlockBreakEvent e);
    public abstract void onBlockClick(PlayerInteractEvent e);
    public abstract void onInvClick(InventoryClickEvent e);
    public void openMainMenu(Player player) {
        MenuManager.openInventory(player, new SSAPIMenu(player, Config.getSection("menu"), this.getLocation()));
    }
}
