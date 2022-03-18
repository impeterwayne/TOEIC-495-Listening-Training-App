package com.example.a900toeic.Utils;

import com.google.firebase.auth.FirebaseAuth;

public class Utils {

    public static String getFirebaseUser() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    public static String getTimeString(long millis) {
        StringBuffer buf = new StringBuffer();
        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);
        buf
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));
        return buf.toString();
    }
}
