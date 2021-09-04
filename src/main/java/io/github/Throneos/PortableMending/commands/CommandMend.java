package io.github.Throneos.PortableMending.commands;

import io.github.Throneos.PortableMending.util.FeedbackHelper;
import io.github.Throneos.PortableMending.util.mend.MendCases;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandMend implements CommandExecutor, TabCompleter {

    private final String[] groupings = {"hands", "armor", "equip", "all"};

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 1) {
                sender.sendMessage("Too many arguments");
                return false;
            }
            String arg = (args.length == 0 ? "" : args[0].toLowerCase());

            switch (arg) {
                case "all":
                    FeedbackHelper.feedbackFromBool(player, MendCases.mendHand(player) && MendCases.mendArmor(player) && MendCases.mendOffHand(player) && MendCases.mendHotbar(player) && MendCases.mendInventory(player));
                    break;
                case "equip":
                    FeedbackHelper.feedbackFromBool(player, MendCases.mendHand(player) && MendCases.mendArmor(player) && MendCases.mendOffHand(player) && MendCases.mendHotbar(player));
                    break;
                case "armor":
                    FeedbackHelper.feedbackFromBool(player, MendCases.mendArmor(player));
                    break;
                case "hands":
                    FeedbackHelper.feedbackFromBool(player, MendCases.mendHand(player) && MendCases.mendOffHand(player));
                    break;
                case "":
                    MendCases.mendHand(player, true);
                    break;
                default:
                    return false;
            }
            return true;
        } else {
            sender.sendMessage("Only players can use this command");
            return false;
        }

    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> complete = new ArrayList<>();

        if (sender instanceof Player && args.length == 1) {
            complete.addAll(Arrays.asList(groupings));
            complete.removeIf(s -> !s.startsWith(args[0]));
        }

        return complete;
    }
}
