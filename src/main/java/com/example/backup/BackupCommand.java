package com.example.backup;

public class BackupCommand implements Command {
    private final DatabaseBackupHelper dbHelper;

    public BackupCommand(DatabaseBackupHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void execute() {
        dbHelper.backup();
    }
}
