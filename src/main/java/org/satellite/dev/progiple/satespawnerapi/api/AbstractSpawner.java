package org.satellite.dev.progiple.satespawnerapi.api;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.novasparkle.lunaspring.API.menus.MenuManager;
import org.satellite.dev.progiple.satespawnerapi.self.ConfigManager;
import org.satellite.dev.progiple.satespawnerapi.self.menu.SSAPIMenu;

@Getter
public abstract class AbstractSpawner {
    private final CreatureSpawner spawner;
    private final SpawnerApiComponent component;

    public AbstractSpawner(Location location, SpawnerApiComponent component) {
        this.spawner = (CreatureSpawner) location.getBlock().getState();
        this.component = component;
    }
    public Location getLocation() {
        return this.spawner.getLocation();

    }
    public boolean checkAPI(String plugin) {
        return plugin.equals(component.getPlugin());
    }
    public abstract void onPlaceSpawner(BlockPlaceEvent e);
    public abstract void onBreakSpawner(BlockBreakEvent e);
    public abstract void onBlockClick(PlayerInteractEvent e);
    public abstract void onInvClick(InventoryClickEvent e);
    public void openMainMenu(Player player) {
        MenuManager.openInventory(new SSAPIMenu(player, ConfigManager.getSection("menu"), this.getLocation()));
    }
}
