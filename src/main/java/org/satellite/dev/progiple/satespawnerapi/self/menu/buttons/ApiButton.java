package org.satellite.dev.progiple.satespawnerapi.self.menu.buttons;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.novasparkle.lunaspring.API.Menus.Items.Item;
import org.satellite.dev.progiple.satespawnerapi.api.ASpawner;

public class ApiButton extends Item {
    private final ASpawner spawner;
    public ApiButton(ConfigurationSection section, ASpawner spawner) {
        super(section, section.getInt("slot"));
        this.spawner = spawner;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        this.spawner.onInvClick(event);
    }
}
