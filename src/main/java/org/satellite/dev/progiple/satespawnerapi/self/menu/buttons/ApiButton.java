package org.satellite.dev.progiple.satespawnerapi.self.menu.buttons;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.novasparkle.lunaspring.API.Menus.Items.Item;
import org.satellite.dev.progiple.satespawnerapi.api.ISAPIComponent;

public class ApiButton extends Item {
    private final ISAPIComponent component;
    private final Location location;
    public ApiButton(ConfigurationSection section, ISAPIComponent component, Location location) {
        super(section, section.getInt("slot"));
        this.component = component;
        this.location = location;
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        this.component.onInvClick(e, this.location);
    }
}
