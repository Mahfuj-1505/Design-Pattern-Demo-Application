package com.example.backup;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class DatabaseBackupHelper {
    private static final String DB_PATH = "pos.db";
    private static final String BACKUP_DIR = "backups/";

    static {
        File dir = new File(BACKUP_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void backup() {
        try {
            String backupFile = BACKUP_DIR + "backup_" + System.currentTimeMillis() + ".db";
            Files.copy(Paths.get(DB_PATH), Paths.get(backupFile), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Backup created: " + backupFile);
        } catch (IOException e) {
            System.err.println("Backup error: " + e.getMessage());
        }
    }

    public void restore(String backupFile) {
        try {
            Files.copy(Paths.get(backupFile), Paths.get(DB_PATH), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Database restored from: " + backupFile);
        } catch (IOException e) {
            System.err.println("Restore error: " + e.getMessage());
        }
    }
}
