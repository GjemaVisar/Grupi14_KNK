package com.jmc.AutoSalon.Services;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {
    private static final int WORKLOAD = 15; // adjust as needed

    public static String hashPassword(String plainTextPassword) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
