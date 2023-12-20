package me.smulan.disablefly.commands;

import me.smulan.disablefly.DisableFly;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class DisableFlyCommand implements CommandExecutor {

    DisableFly plugin;
    public DisableFlyCommand(DisableFly plugin) {
        this.plugin = plugin;
    }

    private boolean active;
    private int time;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!commandSender.hasPermission("disablefly.admin")) {
            commandSender.sendMessage(ChatColor.RED + "Permission denied.");
            return true;
        }

        if (args.length != 2) {
            commandSender.sendMessage("Usage: /disablefly <player> <time>");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        if (player == null) {
            commandSender.sendMessage("Player is null.");
            return true;
        }

        this.time = Integer.parseInt(args[1]);

        runnable(player);

        return false;
    }

    private void runnable(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (time == 0) {
                    cancel();
                    active = false;

                    player.setFlying(false);
                    player.setAllowFlight(false);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bDin Fly är nu avstängd! Hoppas du haft kul! ;)"));
                } else {
                    int[] notificationTimes = {60, 30, 10, 3, 2, 1};

                    for (int notificationTime : notificationTimes) {
                        if (time == notificationTime) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bDin Fly stängs av om: " + time + " sekunder."));
                            break;
                        }
                    }

                    time--;
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}