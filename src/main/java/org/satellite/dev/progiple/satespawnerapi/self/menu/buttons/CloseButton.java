package org.satellite.dev.progiple.satespawnerapi.self.menu.buttons;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.novasparkle.lunaspring.API.Menus.Items.Item;

public class CloseButton extends Item {
    public CloseButton(ConfigurationSection section) {
        super(section, section.getInt("slot"));
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        e.getWhoClicked().closeInventory();
    }
}
