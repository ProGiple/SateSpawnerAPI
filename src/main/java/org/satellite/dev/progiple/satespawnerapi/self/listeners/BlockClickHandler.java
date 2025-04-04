package org.satellite.dev.progiple.satespawnerapi.self.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.novasparkle.lunaspring.API.Menus.MenuManager;
import org.satellite.dev.progiple.satespawnerapi.SateSpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.api.APIComponent;
import org.satellite.dev.progiple.satespawnerapi.api.ASpawner;
import org.satellite.dev.progiple.satespawnerapi.api.menu.SpawnerMenu;
import org.satellite.dev.progiple.satespawnerapi.self.Config;
import org.satellite.dev.progiple.satespawnerapi.self.menu.SSAPIMenu;

import java.awt.*;
import java.util.Objects;

public class BlockClickHandler implements Listener {
    @EventHandler
    public void onBlockClick(PlayerInteractEvent e) {
        Location location = Objects.requireNonNull(e.getClickedBlock()).getLocation();
        ASpawner spawner = SateSpawnerAPI.getInstance().getApiSpawner(location);
        if (spawner != null) {
            ItemStack item = e.getItem();
            if (spawner.getComponent().isRegisterOnly())
                spawner.onBlockClick(e);

            else if (item != null && item.getType().name().endsWith("_SPAWNER_EGG"))
                spawner.onClickWithEgg(item.getType());

            else
                MenuManager.openInventory(e.getPlayer(), new SSAPIMenu(e.getPlayer(), Config.getSection("menu"), location));
        }
    }
}
