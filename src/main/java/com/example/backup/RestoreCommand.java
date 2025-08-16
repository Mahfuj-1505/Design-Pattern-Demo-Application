package com.example.backup;

public class RestoreCommand implements Command {
    private final DatabaseBackupHelper dbHelper;
    private final String backupFile;

    public RestoreCommand(DatabaseBackupHelper dbHelper, String backupFile) {
        this.dbHelper = dbHelper;
        this.backupFile = backupFile;
    }

    @Override
    public void execute() {
        dbHelper.restore(backupFile);
    }
}
