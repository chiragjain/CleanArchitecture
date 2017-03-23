package com.cjlib.cleanarchitecture;


public class Utils {

    private static String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static boolean isEmpty(CharSequence sequence) {
        return sequence == null || sequence.length() == 0;
    }

    public static boolean isEmailValid(String emailId) {
        return emailId.matches(EMAIL_PATTERN);
    }
}
