package com.hthh.bookapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {
    public static final String SHARED_PREFERENCES_NAME = "app_story";
    public static final String USER_ID = "user_id";

    public static void setUser(Context context, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, id);
        editor.apply();
    }

    public static String getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_ID,"");
    }
}
