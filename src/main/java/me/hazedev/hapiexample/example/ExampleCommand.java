package me.hazedev.hapiexample.example;

import me.hazedev.hapi.chat.ChatUtils;
import me.hazedev.hapi.command.CommandHelper;
import me.hazedev.hapi.inventory.Inventories;
import me.hazedev.hapi.inventory.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ExampleCommand extends Command {

    public ExampleCommand() {
        // Define commands dynamically, they will only be registered if the component is enabled, also no more registering in plugin.yml!
        // you could tie these values to a configuration file
        super("example", "This is an example command", "/example", Arrays.asList("examplealias", "aliastwo"));
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        // The CommandHelper allows you to specify messages to send but by ommiting that argument it will send a default message.
        Player player = CommandHelper.validatePlayer(sender, "&cOnly players can use this command!");
        if (player != null) {
            if (CommandHelper.checkPermission(sender, "example.use", "&cYou don't have permission to use this command")) {
                ItemStack exampleItem = new ItemBuilder()
                        .name("&aExample Item")
                        .material(Material.DIRT)
                        .build();
                Inventories.addItemToInventory(player, true, exampleItem);
                ChatUtils.sendMessage(sender, "&aYou have received an example item!");
            }
        }
        return true;
    }
}
