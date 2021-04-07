package me.hazedev.hapiexample.example;

import me.hazedev.hapi.io.YamlFileHandler;

import java.io.File;
import java.io.IOException;

// You may also choose to store a YamlFileHandler as a field and delegate methods
public class ExampleConfig extends YamlFileHandler {

    private String exampleValue;

    public ExampleConfig(File yamlFile) throws IOException {
        super(yamlFile);
        setDefaults(defaults -> {
            defaults.set("example", "value");
        });
        reloadConfig();
    }

    @Override
    public void reloadConfig() {
        // If you override this method it's important to include the super call which will actually reload the yml file into memory
        super.reloadConfig();
        exampleValue = configuration.getString("example");
    }

    @Override
    public void save() throws IOException {
        configuration.set("example", exampleValue);
        // Call super method to save the actual file
        super.save();
    }

    public String getExampleValue() {
        return exampleValue;
    }

    public String setExampleValue() {
        return exampleValue;
    }

}
