package org.satellite.dev.progiple.satespawnerapi.self;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SSAPICommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.hasPermission("satespawnerapi.reload")) {
            if (strings.length >= 1 && strings[0].equalsIgnoreCase("reload")) {
                Config.reload();
                Config.sendMessage(commandSender, "reload");
            } else return false;
        } else Config.sendMessage(commandSender, "noPermission");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return strings.length == 1 ? List.of("reload") : List.of();
    }
}
