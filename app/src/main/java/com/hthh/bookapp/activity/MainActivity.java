package com.hthh.bookapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hthh.bookapp.R;
import com.hthh.bookapp.adapter.MainAdapter;

public class MainActivity extends AppCompatActivity {
    private LinearLayout btnHome;
    private ImageView imgHome;
    private TextView txtHome;
    private LinearLayout btnClassify;
    private ImageView imgClassify;
    private TextView txtClassify;
    private LinearLayout btnSetting;
    private ImageView imgSetting;
    private TextView txtSetting;
    private ViewPager vpMain;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.parseColor("#FFFFFF"));
            window.setNavigationBarColor(getResources().getColor(R.color.color_black));
        }
        initView();
        setUpAdapter();
        registerListener();
    }

    private void initView() {
        btnHome = findViewById(R.id.btnHome);
        imgHome = findViewById(R.id.imgHome);
        txtHome = findViewById(R.id.txtHome);
        btnClassify = findViewById(R.id.btnClassify);
        imgClassify = findViewById(R.id.imgClassify);
        txtClassify = findViewById(R.id.txtClassify);
        btnSetting = findViewById(R.id.btnSetting);
        imgSetting = findViewById(R.id.imgSetting);
        txtSetting = findViewById(R.id.txtSetting);
        vpMain = findViewById(R.id.vpMain);
    }

    private void setUpAdapter() {
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        vpMain.setAdapter(mainAdapter);
    }

    private void registerListener() {
        vpMain.setCurrentItem(0);
        setUpHome();
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnHome:
                        vpMain.setCurrentItem(0);
                        setUpHome();
                        break;
                    case R.id.btnClassify:
//                        vpMain.setCurrentItem(1);
                        setUpClassify();
                        break;
                    case R.id.btnSetting:
//                        vpMain.setCurrentItem(2);
                        setUpSetting();
                        break;
                }
            }
        };
        btnHome.setOnClickListener(clickListener);
        btnClassify.setOnClickListener(clickListener);
        btnSetting.setOnClickListener(clickListener);

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setUpHome();
                        break;
                    case 1:
                        setUpClassify();
                        break;
                    case 2:
                        setUpSetting();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setUpHome() {
        imgHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_color));
        txtHome.setTextColor(Color.parseColor("#1565C0"));
        imgClassify.setImageDrawable(getResources().getDrawable(R.drawable.ic_queue));
        txtClassify.setTextColor(Color.parseColor("#BDBDBD"));
        imgSetting.setImageDrawable(getResources().getDrawable(R.drawable.ic_gear));
        txtSetting.setTextColor(Color.parseColor("#BDBDBD"));
    }

    public void setUpClassify() {
        imgHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home));
        txtHome.setTextColor(Color.parseColor("#BDBDBD"));
        imgClassify.setImageDrawable(getResources().getDrawable(R.drawable.ic_queue_color));
        txtClassify.setTextColor(Color.parseColor("#1565C0"));
        imgSetting.setImageDrawable(getResources().getDrawable(R.drawable.ic_gear));
        txtSetting.setTextColor(Color.parseColor("#BDBDBD"));
    }

    public void setUpSetting() {
        imgHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home));
        txtHome.setTextColor(Color.parseColor("#BDBDBD"));
        imgClassify.setImageDrawable(getResources().getDrawable(R.drawable.ic_queue));
        txtClassify.setTextColor(Color.parseColor("#BDBDBD"));
        imgSetting.setImageDrawable(getResources().getDrawable(R.drawable.ic_gear_color));
        txtSetting.setTextColor(Color.parseColor("#1565C0"));
    }
}