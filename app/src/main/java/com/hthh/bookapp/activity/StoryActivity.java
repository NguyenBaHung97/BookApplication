package com.hthh.bookapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hthh.bookapp.R;
import com.hthh.bookapp.adapter.ImageAdapter;
import com.hthh.bookapp.model.LinkImage;
import com.hthh.bookapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryActivity extends AppCompatActivity {
    private RecyclerView rcvStory;
    private ImageAdapter storyAdapter;
    private int idChap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.parseColor("#FFFFFF"));
            window.setNavigationBarColor(getResources().getColor(R.color.color_black));
        }
        initView();
        idChap = getIntent().getIntExtra("id_chap",0);
        callApiGetData(idChap);
    }

    public void callApiGetData(int idChap) {
        Call<List<LinkImage>> call = RetrofitClient.getService().getLinkImage(idChap);
        Callback<List<LinkImage>> callback = new Callback<List<LinkImage>>() {
            @Override
            public void onResponse(Call<List<LinkImage>> call, Response<List<LinkImage>> response) {
                if (response.body() == null || response.body().size() == 0) {
                    Log.d("7881", "onFailure11: " );
                } else {
                    storyAdapter = new ImageAdapter(StoryActivity.this, response.body());
                    rcvStory.setLayoutManager(new LinearLayoutManager(StoryActivity.this));
                    rcvStory.setAdapter(storyAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<LinkImage>> call, Throwable t) {
                Log.d("7881", "onFailure: " + t.getMessage());
            }
        };
        call.enqueue(callback);
    }


    public void initView() {
        rcvStory = findViewById(R.id.rcvStory);
    }

}
