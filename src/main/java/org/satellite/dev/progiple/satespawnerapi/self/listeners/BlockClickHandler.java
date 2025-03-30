package org.satellite.dev.progiple.satespawnerapi.self.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.novasparkle.lunaspring.API.Menus.MenuManager;
import org.satellite.dev.progiple.satespawnerapi.SateSpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.api.ISAPIComponent;
import org.satellite.dev.progiple.satespawnerapi.api.SpawnerAPI;
import org.satellite.dev.progiple.satespawnerapi.self.Config;
import org.satellite.dev.progiple.satespawnerapi.self.menu.SSAPIMenu;

public class BlockClickHandler implements Listener {
    @EventHandler
    public void onBlockClick(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        if (block == null || block.getType() != Material.SPAWNER) return;

        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        SpawnerAPI api = SateSpawnerAPI.getINSTANCE().getSpawnerAPI();

        String action_value = Config.getString("after_click_action.id");

        ISAPIComponent component = Config.getBoolean("after_click_action.registerOnly") ?
                api.getApi(action_value) : null;
        if (item.getType().name().contains("_SPAWNER_EGG")) {
            if (component == null) api.getValues().forEach(a -> a.onClickWithEgg(e));
            else component.onClickWithEgg(e);
            return;
        }

        if (component == null && (action_value == null || action_value.isEmpty() || action_value.contains("NONE"))) {
            if (api.getValues().stream().noneMatch(i -> i.onDefaultClick(e)))
                MenuManager.openInventory(player, new SSAPIMenu(player, Config.getSection("menu"), block.getLocation()));
            return;
        }

        if (component == null) throw new SpawnerAPI.SpawnerAPIIsNullException(action_value);
        component.onSuperClick(e);
    }
}
