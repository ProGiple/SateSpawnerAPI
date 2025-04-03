package org.satellite.dev.progiple.satespawnerapi.self.menu;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.novasparkle.lunaspring.API.Menus.Items.Item;
import org.satellite.dev.progiple.satespawnerapi.SateSpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.api.ISAPIComponent;
import org.satellite.dev.progiple.satespawnerapi.api.SpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.api.menu.SpawnerMenu;
import org.satellite.dev.progiple.satespawnerapi.self.menu.buttons.ApiButton;
import org.satellite.dev.progiple.satespawnerapi.self.menu.buttons.CloseButton;

import java.util.Objects;

@Getter
public final class SSAPIMenu extends SpawnerMenu {
    public SSAPIMenu(Player player, ConfigurationSection section, Location location) {
        super(player, Objects.requireNonNull(section.getString("title")), (byte) (section.getInt("rows") * 9),
                Objects.requireNonNull(section.getConfigurationSection("items.decorations")), location);

        ConfigurationSection itemSections = section.getConfigurationSection("items.clickable");
        assert itemSections != null;
        for (String key : itemSections.getKeys(false)) {
            ConfigurationSection itemSection = itemSections.getConfigurationSection(key);

            Item button = null;
            if (key.startsWith("CLOSE")) button = new CloseButton(itemSection);
            else if (key.startsWith("API-")) {
                String id = key.replace("API-", "");
                ISAPIComponent isapiComponent = SateSpawnerAPI.getINSTANCE().getSpawnerAPI().getApi(id);
                if (isapiComponent == null) throw new SpawnerAPI.SpawnerAPIIsNullException(id);

                button = new ApiButton(itemSection, isapiComponent, this.getLocation());
            }

            if (button != null) this.addItems(button);
        }
    }

    @Override
    public void onOpen(InventoryOpenEvent e) {
        this.insertAllItems();
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        e.setCancelled(true);
        if (item != null) this.findFirstItem(item).onClick(e);
    }

    @Override
    public void onClose(InventoryCloseEvent e) {}
}
