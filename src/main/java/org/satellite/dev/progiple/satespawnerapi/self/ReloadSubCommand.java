package org.satellite.dev.progiple.satespawnerapi.self;

import org.bukkit.command.CommandSender;
import org.novasparkle.lunaspring.API.commands.Invocation;
import org.novasparkle.lunaspring.API.commands.annotations.Check;
import org.novasparkle.lunaspring.API.commands.annotations.SubCommand;

@SubCommand(appliedCommand = "satespawnerapi", commandIdentifiers = "reload")
@Check(permissions = "ssapi.reload", flags = {})
public class ReloadSubCommand implements Invocation {
    @Override
    public void invoke(CommandSender commandSender, String[] strings) {
        ConfigManager.reload();
        ConfigManager.sendMessage(commandSender, "reload");
    }
}
