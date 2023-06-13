package com.aqupd.caracal.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@SuppressWarnings("unused")
public class AqDebug {

  private AqDebug() {}

  private boolean loaded;
  public static final AqDebug INSTANCE = new AqDebug();
  @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
  private final Properties aqdebug = new Properties();

  public boolean startDebug(boolean key) {
    if (key && !loaded) load();
    return (true);
  }

  private final File dfile = new File("./config/AqMods/biomes.config");

  @SuppressWarnings("ResultOfMethodCallIgnored")
  private void load() {
    loaded = true;
    try {
      Files.createDirectories(Paths.get("./config/AqMods/"));
      var writer = new FileOutputStream(dfile);

      dfile.createNewFile();
      //aqdebug.setProperty("biome.list",);
      aqdebug.store(writer, "All Biometypes for spawnconfiguration");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
