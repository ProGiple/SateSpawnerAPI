package org.satellite.dev.progiple.satespawnerapi.api.menu;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.novasparkle.lunaspring.API.Menus.AMenu;

@Getter
public abstract class SpawnerMenu extends AMenu {
    private final Location location;
    public SpawnerMenu(Player player, Location location) {
        super(player);
        this.location = location;
    }

    public SpawnerMenu(Player player, String title, byte size, ConfigurationSection decorSection, Location location) {
        super(player, title, size, decorSection);
        this.location = location;
    }

    public SpawnerMenu(Player player, ConfigurationSection menuSection, Location location) {
        super(player, menuSection);
        this.location = location;
    }

    public SpawnerMenu(Player player, String title, byte size, Location location) {
        super(player, title, size);
        this.location = location;
    }
}
