package ru.burmistrov.tm.utils;

public class SignatureUtil {

    public static String sign(String value, int cycle, String salt) {
        if(value == null) return null;
        String result = null;
        for (int i = 0; i < cycle; i++) {
            result = PasswordUtil.hashPassword(salt + value + salt);
        }
        return result;
    }
}
