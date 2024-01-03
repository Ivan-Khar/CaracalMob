package com.aqupd.caracal.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@SuppressWarnings("unused")
public class AqConfig {

  private AqConfig() {}

  public static final AqConfig INSTANCE = new AqConfig();

  private boolean loaded;
  private final Properties aqprop = new Properties();

  public boolean getBooleanProperty(String key) {
    if (!loaded) load();
    return Boolean.parseBoolean(aqprop.getProperty(key));
  }

  public String getStringProperty(String key) {
    if (!loaded) load();
    return aqprop.getProperty(key);
  }

  public int getNumberProperty(String key) {
    if (!loaded) load();
    return Integer.parseInt(aqprop.getProperty(key));
  }

  public double getDoubleProperty(String key) {
    if (!loaded) load();
    return Double.parseDouble(aqprop.getProperty(key));
  }

  private final File file = new File("./config/AqMods/caracal.config");

  @SuppressWarnings("ResultOfMethodCallIgnored")
  private void load() {
    loaded = true;
    try {
      Files.createDirectories(Paths.get("./config/AqMods/"));

      if (file.exists()) {
        var reader = new FileReader(file);
        aqprop.load(reader);
        reader.close();
      } else {
        var writer = new FileOutputStream(file);
        file.createNewFile();
        aqprop.setProperty("config.version", "1");
        aqprop.setProperty("entity.health", "10.0");
        aqprop.setProperty("entity.speed", "0.3");
        aqprop.setProperty("entity.follow", "20.0");
        aqprop.setProperty("entity.damage", "2.5");
        aqprop.setProperty("entity.knockback", "0.5");
        aqprop.store(writer, "Configuration file for Caracal mod. spawn.biomes doesn't work for now");
        writer.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
