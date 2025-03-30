package org.satellite.dev.progiple.satespawnerapi.api.menu;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.novasparkle.lunaspring.API.Menus.Items.Item;
import org.novasparkle.lunaspring.API.Menus.MenuManager;
import org.satellite.dev.progiple.satespawnerapi.self.Config;
import org.satellite.dev.progiple.satespawnerapi.self.menu.SSAPIMenu;

import java.util.List;

public class BackSpawnerButton extends Item {
    private final Location location;
    public BackSpawnerButton(Material material,
                             String displayName,
                             List<String> lore,
                             int amount, byte slot,
                             Location location) {
        super(material, displayName, lore, amount, slot);
        this.location = location;
    }

    public BackSpawnerButton(ConfigurationSection section, int slot, Location location) {
        super(section, slot);
        this.location = location;
    }

    public BackSpawnerButton(Material material, int amount, Location location) {
        super(material, amount);
        this.location = location;
    }

    public BackSpawnerButton(Material material, Location location) {
        super(material);
        this.location = location;
    }

    public BackSpawnerButton(ConfigurationSection section, boolean rowCol, Location location) {
        super(section, rowCol);
        this.location = location;
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        MenuManager.openInventory(player, new SSAPIMenu(player, Config.getSection("menu"), this.location));
    }
}
