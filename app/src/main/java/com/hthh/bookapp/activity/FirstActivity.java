package com.hthh.bookapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hthh.bookapp.R;
import com.hthh.bookapp.fragment.LoginFragment;
import com.hthh.bookapp.fragment.SignUpFragment;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.parseColor("#FFFFFF"));
            window.setNavigationBarColor(getResources().getColor(R.color.color_black));
        }
        startLogin();
    }


    public void startLogin() {
        LoginFragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frContainer, loginFragment)
                .commit();
    }

    public void startSignup() {
        SignUpFragment loginFragment = new SignUpFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frContainer, loginFragment)
                .addToBackStack(null)
                .commit();
    }
}
