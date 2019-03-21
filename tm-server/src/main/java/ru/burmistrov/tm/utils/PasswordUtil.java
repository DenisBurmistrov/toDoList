package ru.burmistrov.tm.utils;

import org.jetbrains.annotations.NotNull;

import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    public static String hashPassword(@NotNull final String md5) throws NoSuchAlgorithmException {
            @NotNull final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            @NotNull final byte[] array = md.digest(md5.getBytes());
            @NotNull final StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        }
}
