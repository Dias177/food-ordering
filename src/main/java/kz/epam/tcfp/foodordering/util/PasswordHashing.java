package kz.epam.tcfp.foodordering.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {

    private static final String ALGORITHM_MD5 = "MD5";
    private static final Logger logger = LogManager.getLogger();

    private PasswordHashing() {
        throw new IllegalStateException("Utility class");
    }

    public static String hash(String password) {
        String hashedPassword;
        MessageDigest md;
        byte[] bytes = new byte[0];
        try {
            md = MessageDigest.getInstance(ALGORITHM_MD5);
            md.update(password.getBytes());
            bytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such algorithm for password hashing", e);
        }
        //Conversion from decimal format to hexadecimal one
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toString((b & 0xFF) + 0x100, 16).substring(1));
        }
        hashedPassword = sb.toString();
        return hashedPassword;
    }
}
