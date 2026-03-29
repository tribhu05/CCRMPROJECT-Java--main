
package edu.ccrm.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();
    private final Path dataDir;

    private AppConfig() {
        dataDir = Paths.get("data");
        try {
            if (!Files.exists(dataDir)) Files.createDirectories(dataDir);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create data dir", e);
        }
    }

    public static AppConfig getInstance() { return INSTANCE; }

    public Path getDataDir() { return dataDir; }
}
