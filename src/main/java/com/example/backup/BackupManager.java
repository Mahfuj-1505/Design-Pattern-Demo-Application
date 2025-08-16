package com.example.backup;

import java.util.Timer;
import java.util.TimerTask;

public class BackupManager {
    private static BackupManager instance;
    private final DatabaseBackupHelper dbHelper;
    private final Timer timer;

    private BackupManager() {
        this.dbHelper = new DatabaseBackupHelper();
        this.timer = new Timer(true); // daemon thread
    }

    public static BackupManager getInstance() {
        if (instance == null) {
            instance = new BackupManager();
        }
        return instance;
    }

    public void scheduleAutomaticBackup() {
        Command backupCommand = new BackupCommand(dbHelper);

        // Run every 30 minutes
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                backupCommand.execute();
            }
        }, 0, 15 * 60 * 1000);

        System.out.println("⏳ Automated backup scheduled every 30 minutes.");
    }

    public void manualBackup() {
        new BackupCommand(dbHelper).execute();
    }

    public void restoreFrom(String backupFile) {
        new RestoreCommand(dbHelper, backupFile).execute();
    }
}
