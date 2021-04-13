package me.hazedev.hapiexample.example.config;

import me.hazedev.hapi.config.ConfigurableSection;
import me.hazedev.hapi.config.YamlConfigReader;
import me.hazedev.hapi.config.YamlConfigurableFile;
import me.hazedev.hapi.config.YamlOption;
import me.hazedev.hapi.config.value.OptionConfigurableSection;
import me.hazedev.hapi.config.value.OptionInteger;
import me.hazedev.hapi.config.value.OptionLong;
import me.hazedev.hapi.config.value.OptionString;
import me.hazedev.hapi.io.YamlFileHandler;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

// You may also choose to store a YamlFileHandler as a field and delegate methods
public class ExampleConfig implements YamlConfigurableFile {

    private final YamlFileHandler fileHandler;
    public final YamlOption<String> exampleOption = new OptionString("example-string", "defaultValue");
    public final YamlOption<Integer> exampleNumber = new OptionInteger("number", 10);
    public final YamlOption<ExampleConfigurableSection> exampleSection = new OptionConfigurableSection<>(new ExampleConfigurableSection(this, "example-section"));

    public ExampleConfig(YamlFileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void reload() {
        // Reload configuration file from disk (to load in any changes)
        fileHandler.reloadConfig();
        // This will save the default values to the file if there isn't an existing value
        YamlConfigReader.saveDefaults(this);
        // This will automatically read options from config and update the values in the YamlOption fields
        YamlConfigReader.read(this);
    }

    @NotNull
    @Override
    public YamlConfiguration getConfiguration() {
        return fileHandler.getConfiguration();
    }

    @NotNull
    @Override
    public String getFileName() {
        return fileHandler.getFile().getName();
    }

    @Override
    public void saveConfig() throws IOException {
        fileHandler.saveConfig();
    }

}
