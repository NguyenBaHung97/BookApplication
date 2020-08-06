package com.hthh.bookapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hthh.bookapp.R;
import com.hthh.bookapp.adapter.ChapAdapter;
import com.hthh.bookapp.model.ChapStory;
import com.hthh.bookapp.model.Story;
import com.hthh.bookapp.model.StoryOfBookcase;
import com.hthh.bookapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapActivity extends AppCompatActivity {
    private RecyclerView rcvChap;
    private ImageView imgAvatar;
    private TextView txtName;
    private ChapAdapter chapAdapter;
    private StoryOfBookcase storyOfBookcase;
    private Story story;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.parseColor("#FFFFFF"));
            window.setNavigationBarColor(getResources().getColor(R.color.color_black));
        }
        initView();
        if (getIntent().hasExtra("StoryOfBookcase")) {
            storyOfBookcase = (StoryOfBookcase) getIntent().getSerializableExtra("StoryOfBookcase");
            Glide.with(this).load(storyOfBookcase.getLinkimage()).into(imgAvatar);
            txtName.setText(storyOfBookcase.getName());
            callApiGetData(Integer.parseInt(storyOfBookcase.getId()));
        }
        if (getIntent().hasExtra("Story")){
            story = (Story) getIntent().getSerializableExtra("Story");
            Glide.with(this).load(story.getImage_story()).into(imgAvatar);
            txtName.setText(story.getName_story());
            callApiGetData(Integer.parseInt(story.getId()));
        }
    }

    public void callApiGetData(int id) {
        Call<List<ChapStory>> call = RetrofitClient.getService().getChap(id);
        Callback<List<ChapStory>> callback = new Callback<List<ChapStory>>() {
            @Override
            public void onResponse(Call<List<ChapStory>> call, Response<List<ChapStory>> response) {
                if (response.body() == null || response.body().size() == 0) {

                } else {
                    chapAdapter = new ChapAdapter(ChapActivity.this, response.body());
                    chapAdapter.setOnClickItemListener(new ChapAdapter.OnClickItemListener() {
                        @Override
                        public void onClicked(int id) {
                            Intent intent = new Intent(ChapActivity.this, StoryActivity.class);
                            intent.putExtra("id_chap", id);
                            startActivity(intent);
                        }
                    });
                    rcvChap.setLayoutManager(new LinearLayoutManager(ChapActivity.this));
                    rcvChap.setAdapter(chapAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ChapStory>> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }

    private void initView() {
        rcvChap = findViewById(R.id.rcvChap);
        imgAvatar = findViewById(R.id.imgAvatar);
        txtName = findViewById(R.id.txtName);
    }

}
