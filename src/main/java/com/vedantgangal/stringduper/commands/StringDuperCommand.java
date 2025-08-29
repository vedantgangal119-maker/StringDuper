package com.vedantgangal.stringduper.commands;

import com.vedantgangal.stringduper.StringDuper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StringDuperCommand implements CommandExecutor {

    private final StringDuper plugin;

    public StringDuperCommand(StringDuper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("stringduper.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.AQUA + "StringDuper commands:");
            sender.sendMessage(ChatColor.GRAY + "/" + label + " toggle " + ChatColor.DARK_GRAY + "- enable/disable");
            sender.sendMessage(ChatColor.GRAY + "/" + label + " reload " + ChatColor.DARK_GRAY + "- reload config");
            sender.sendMessage(ChatColor.GRAY + "/" + label + " set <amount> " + ChatColor.DARK_GRAY + "- set drop amount");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "toggle":
                boolean now = !plugin.getConfig().getBoolean("enabled", true);
                plugin.getConfig().set("enabled", now);
                plugin.saveConfig();
                sender.sendMessage(ChatColor.GREEN + "StringDuper is now " + (now ? "enabled" : "disabled") + ".");
                return true;
            case "reload":
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Config reloaded.");
                return true;
            case "set":
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "Usage: /" + label + " set <amount>");
                    return true;
                }
                try {
                    int amt = Integer.parseInt(args[1]);
                    if (amt < 0 || amt > 2304) {
                        sender.sendMessage(ChatColor.RED + "Amount must be between 0 and 2304.");
                        return true;
                    }
                    plugin.getConfig().set("drop-amount", amt);
                    plugin.saveConfig();
                    sender.sendMessage(ChatColor.GREEN + "Drop amount set to " + amt + ".");
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Please enter a number.");
                }
                return true;
            default:
                sender.sendMessage(ChatColor.RED + "Unknown subcommand. Use: toggle | reload | set <amount>");
                return true;
        }
    }
}
