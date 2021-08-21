package io.github.Throneos.PortableMending;

import io.github.Throneos.PortableMending.commands.CommandMend;
import io.github.Throneos.PortableMending.util.ConfigHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class PortableMending extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigHelper.createInstance(this);
        getCommand("mend").setExecutor(new CommandMend());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
