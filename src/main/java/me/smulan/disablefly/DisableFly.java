package me.smulan.disablefly;

import me.smulan.disablefly.commands.DisableFlyCommand;
import me.smulan.disablefly.events.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableFly extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
    }

    private void registerCommands() {
        getCommand("disablefly").setExecutor(new DisableFlyCommand(this));
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
    }

}
