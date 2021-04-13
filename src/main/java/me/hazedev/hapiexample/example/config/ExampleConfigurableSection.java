package me.hazedev.hapiexample.example.config;

import me.hazedev.hapi.config.ConfigurableSection;
import me.hazedev.hapi.config.YamlConfigurableFile;
import me.hazedev.hapi.config.YamlOption;
import me.hazedev.hapi.config.value.OptionBoolean;
import me.hazedev.hapi.config.value.OptionEnum;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class ExampleConfigurableSection extends ConfigurableSection {

    public final YamlOption<Boolean> booleanOption = new OptionBoolean("section-option", true);
    public final YamlOption<Material> materialOption = new OptionEnum<>("material", Material.class, Material.STONE);

    public ExampleConfigurableSection(@NotNull YamlConfigurableFile root, @NotNull String path) {
        super(root, path);
    }

}
