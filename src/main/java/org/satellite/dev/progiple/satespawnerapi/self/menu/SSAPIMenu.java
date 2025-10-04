package org.satellite.dev.progiple.satespawnerapi.self.menu;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.satellite.dev.progiple.satespawnerapi.SateSpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.api.AbstractSpawner;
import org.satellite.dev.progiple.satespawnerapi.api.menu.SpawnerMenu;
import org.satellite.dev.progiple.satespawnerapi.self.menu.buttons.ApiButton;
import org.satellite.dev.progiple.satespawnerapi.self.menu.buttons.CloseButton;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

@Getter
public final class SSAPIMenu extends SpawnerMenu {
    private final ConfigurationSection section;

    public SSAPIMenu(Player player, ConfigurationSection section, Location location) {
        super(player, section, location);
        this.section = section;
    }


    @Override
    public void onOpen(InventoryOpenEvent e) {

        ConfigurationSection itemSections = section.getConfigurationSection("items.clickable");
        assert itemSections != null;
        Set<AbstractSpawner> spawners = SateSpawnerAPI.getInstance().getRegisteredSpawners(location);
        for (String key : itemSections.getKeys(false)) {
            ConfigurationSection itemSection = itemSections.getConfigurationSection(key);

            if (key.startsWith("CLOSE")) {
                this.addItems(false, new CloseButton(itemSection));

            } else if (key.startsWith("API-")) {
                String plugin = key.replace("API-", "");
                AbstractSpawner pluginSpawner = spawners.stream().filter(s -> s.checkAPI(plugin)).findFirst().orElse(null);
                if (pluginSpawner == null) throw new NoSuchElementException(String.format("Компонента с идентификатором %s не существует!", plugin));

                this.addItems(false, new ApiButton(itemSection, pluginSpawner));
            }
        }
        this.insertAll();
    }
}
