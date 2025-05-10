package org.satellite.dev.progiple.satespawnerapi.self;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.novasparkle.lunaspring.API.commands.Invocation;
import org.novasparkle.lunaspring.API.commands.annotations.Check;
import org.novasparkle.lunaspring.API.commands.annotations.SubCommand;

import java.util.List;

@SubCommand(appliedCommand = "satespawnerapi", commandIdentifiers = "reload")
@Check(permissions = "ssapi.reload", flags = {})
public class ReloadSubCommand implements Invocation {
    @Override
    public void invoke(CommandSender commandSender, String[] strings) {
        Config.reload();
        Config.sendMessage(commandSender, "reload");
    }
}
