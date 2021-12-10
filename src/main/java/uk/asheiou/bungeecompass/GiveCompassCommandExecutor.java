package uk.asheiou.bungeecompass;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCompassCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("givecompass")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack compass = new CompassItemStack().getCompass();
                player.getInventory().addItem(compass);
                player.sendMessage(ChatColor.AQUA+"Compass given successfully.");
                return true;
            } else {
                sender.sendMessage("This command cannot be run from the console.");
                return false;
            }
        }
        return false;
    }
}