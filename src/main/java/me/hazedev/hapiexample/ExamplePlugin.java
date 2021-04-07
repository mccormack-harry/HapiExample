package me.hazedev.hapiexample;

import me.hazedev.hapi.bossbar.BossBar;
import me.hazedev.hapi.component.ComponentManager;
import me.hazedev.hapi.joinquit.JoinQuitHandler;
import me.hazedev.hapi.userdata.UserDataManager;
import me.hazedev.hapiexample.example.ExampleComponent;

public class ExamplePlugin extends ComponentManager {

    // This is the only method that should exist in this class
    // Any features will be defined by the components
    // If you do choose to override methods be sure to include a super method call if necessary

    @Override
    protected void registerComponents() {
        // There are a few components provided by default
        registerComponent(new UserDataManager());
        registerComponent(new BossBar());
        registerComponent(new JoinQuitHandler());

        // Register our ExampleComponent
        registerComponent(new ExampleComponent());
    }

}
