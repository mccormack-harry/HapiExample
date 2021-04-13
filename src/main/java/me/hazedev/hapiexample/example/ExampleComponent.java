package me.hazedev.hapiexample.example;

import me.hazedev.hapi.chat.ChatUtils;
import me.hazedev.hapi.component.Component;
import me.hazedev.hapi.config.YamlConfigReader;
import me.hazedev.hapi.io.YamlFileHandler;
import me.hazedev.hapi.logging.Log;
import me.hazedev.hapiexample.example.config.ExampleConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ExampleComponent extends Component implements Listener {

    public ExampleConfig config;

    public ExampleComponent() {
        super("example");
    }

    @Override
    protected List<Class<? extends Component>> getDependencies(boolean hard) {
        // You can override this method to define what other copmonents this component depends on
        // Similar to plugin dependencies:
        // hard dependencies are required to be enabled to attempt to enable this component
        // soft dependencies will always enabled before this component if it's present
        if (hard) {
            return Collections.emptyList();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    protected boolean onEnable() {
        // You have to register commands and listeners through the methods provided in Component
        // This way when the component is disabled, the commands & listeners will also be disabled
        this.registerCommand(new ExampleCommand());
        this.registerListener(new ExampleListener());

        // load config
        YamlFileHandler fileHandler;
        try {
            // plugins/PluginName/ComponentId/example.yml
            fileHandler = getYamlFileHandler("example.yml");
        } catch (IOException e) {
            // Custom Log class with static access and more features
            Log.warning(this, "Failed to load config");
            Log.error(this, e);
            return false;
        }
        config = new ExampleConfig(fileHandler);
        return reload();
    }

    // No need for an onDisable method here as Commands & Listeners are automatically unregistered!
    @Override
    protected void onDisable() {}

    // Save will automatically be called when a component is disabled and by the autosave feature!
    @Override
    protected void save() {
        try {
            config.saveConfig();
        } catch (IOException e) {
            Log.warning(this, "Failed to save config");
            Log.error(this, e);
        }
    }

    // This can be called by using /components <id> reload
    // See: ComponentCommandHandler
    @Override
    protected boolean reload() {
        config.reload();
        return true;
    }

    // If a component class itself implements listener it will automatically be registered as a listener by the ComponentManager
    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        ChatUtils.sendMessage(event.getPlayer(), "Welcome to this example server");
    }

}
