package com.hthh.bookapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class Utils {
    public static final String SHARED_PREFERENCES_NAME = "app_story";
    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "user_email";
    private static ProgressDialog progressDialog;

    public static void setUser(Context context, String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, id);
        editor.apply();
    }

    public static void removeUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_ID);
        editor.apply();
    }

    public static String getUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_ID, "");
    }

    public static void setEmail(Context context, String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_EMAIL, email);
        editor.apply();
    }

    public static void removeEmail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(USER_EMAIL);
        editor.apply();
    }

    public static String getEmail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_EMAIL, "");
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static void hideLoadingDialog() {
        progressDialog.cancel();
    }
}
