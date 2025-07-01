package com.example.util;

public class Session {
    private static Integer loggedInUserId = null;

    public static void setLoggedInUserId(Integer id) {
        loggedInUserId = id;
    }

    public static Integer getLoggedInUserId() {
        return loggedInUserId;
    }

    public static boolean isLoggedIn() {
        return loggedInUserId != null;
    }

    public static void logout() {
        loggedInUserId = null;
    }
}
