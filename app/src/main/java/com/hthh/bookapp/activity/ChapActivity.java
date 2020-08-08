package com.hthh.bookapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.hthh.bookapp.R;
import com.hthh.bookapp.adapter.RateAndChapAdapter;
import com.hthh.bookapp.model.Story;
import com.hthh.bookapp.model.StoryOfBookcase;

public class ChapActivity extends AppCompatActivity {
    private ImageView imgAvatar;
    private TextView txtName;
    private StoryOfBookcase storyOfBookcase;
    private Story story;
    private RateAndChapAdapter rateAndChapAdapter;
    private ViewPager vpRateAndChap;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        changeColorStatusBar();
        initView();
        getData();

    }

    public void changeColorStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.parseColor("#FFFFFF"));
            window.setNavigationBarColor(getResources().getColor(R.color.color_black));
        }
    }

    public void getData() {
        if (getIntent().hasExtra("StoryOfBookcase")) {
            storyOfBookcase = (StoryOfBookcase) getIntent().getSerializableExtra("StoryOfBookcase");
            Glide.with(this).load(storyOfBookcase.getLinkimage()).into(imgAvatar);
            txtName.setText(storyOfBookcase.getName());
            rateAndChapAdapter = new RateAndChapAdapter(getSupportFragmentManager(), Integer.parseInt(storyOfBookcase.getId()));
            vpRateAndChap.setAdapter(rateAndChapAdapter);
            tabLayout.setupWithViewPager(vpRateAndChap);
        }
        if (getIntent().hasExtra("Story")) {
            story = (Story) getIntent().getSerializableExtra("Story");
            Glide.with(this).load(story.getImage_story()).into(imgAvatar);
            txtName.setText(story.getName_story());
            rateAndChapAdapter = new RateAndChapAdapter(getSupportFragmentManager(), Integer.parseInt(story.getId()));
            vpRateAndChap.setAdapter(rateAndChapAdapter);
            tabLayout.setupWithViewPager(vpRateAndChap);
        }
    }

    private void initView() {
        tabLayout = findViewById(R.id.tabLayout);
        vpRateAndChap = findViewById(R.id.vpRateAndChap);
        imgAvatar = findViewById(R.id.imgAvatar);
        txtName = findViewById(R.id.txtName);
    }

}
