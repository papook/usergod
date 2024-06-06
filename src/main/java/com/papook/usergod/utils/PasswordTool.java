package com.papook.usergod.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordTool {

    public static String hash(final String password) {
        String hashedPassword = DigestUtils.sha256Hex(password);

        return hashedPassword;
    }

    public static boolean verify(final String password, final String hash) {
        String hashedPassword = hash(password);
        
        return password.equals(hashedPassword);
    }

}
