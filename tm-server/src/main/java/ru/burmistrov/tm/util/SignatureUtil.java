package ru.burmistrov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.NoSuchAlgorithmException;

public class SignatureUtil {

    public static String sign(@Nullable final String value, int cycle, @NotNull final String salt) throws NoSuchAlgorithmException {
        if(value == null) return null;
        String result = null;
        for (int i = 0; i < cycle; i++) {
            result = PasswordUtil.hashPassword(salt + value + salt);
        }
        return result;
    }
}
