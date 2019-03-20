package ru.burmistrov.tm.utils;

public class SignatureUtil {

    public static String sign(String value) {
        if(value == null) return null;
        String result = null;
        for (int i = 0; i < 201; i++) {
            result = PasswordUtil.hashPassword(value);
        }
        return result;
    }
}
