package me.hazedev.hapiexample.example;

import me.hazedev.hapi.chat.CCUtils;
import me.hazedev.hapi.chat.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ExampleListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        // This will broadcast to every player online
        ChatUtils.broadcast(CCUtils.GREEN + player.getName() + " Just died! RIP");
        // You can specify a Predicate<Player> to test for who should receive the broadcast
        // This will send to everyone except the player who died
        ChatUtils.broadcast(CCUtils.ORANGE + "&cEveryone bully him >:)", receiver -> !receiver.getUniqueId().equals(player.getUniqueId()));
    }

}
