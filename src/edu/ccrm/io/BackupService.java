package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BackupService {

    public Path backupDir(Path sourceFolder, Path backupsBase) throws IOException {
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path snapshot = backupsBase.resolve("backup_" + stamp);
        Files.createDirectories(snapshot);
        // copy recursively using Files.walk
        try (Stream<Path> walk = Files.walk(sourceFolder)) {
            walk.forEach(p -> {
                try {
                    Path rel = sourceFolder.relativize(p);
                    Path dest = snapshot.resolve(rel);
                    if (Files.isDirectory(p)) {
                        if (!Files.exists(dest)) Files.createDirectories(dest);
                    } else {
                        Files.copy(p, dest, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        return snapshot;
    }
}