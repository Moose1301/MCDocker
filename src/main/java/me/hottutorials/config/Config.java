package me.hottutorials.config;

import me.grison.jtoml.impl.Toml;
import me.hottutorials.utils.Logger;
import me.hottutorials.utils.OSUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Config {

    private final File file = new File(OSUtils.getUserData() + "config.toml");
    public File getFile() { return file; }

    private static final Config INSTANCE = new Config();
    public static Config getConfig() { return INSTANCE; }

    public void changeSetting(String name, Object value) {
        ConfigSerializer newConfig = loadSettings();
    }

    public void init() {
        Logger.log("Initiating config");
        try {
            if(!Config.getConfig().getFile().exists()) {
                Config.getConfig().getFile().createNewFile();
                saveDefaultSettings();
                Logger.log("Created new config");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigSerializer loadSettings() {
        return Toml.parse(file).getAs("config", ConfigSerializer.class);
    }

    public ConfigSerializer getDefaultSettings() {
        return new ConfigSerializer();
    }

    public void saveDefaultSettings() {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(Toml.serialize("config", getDefaultSettings()));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}