package com.hthh.bookapp.activity;

import android.media.Image;
import android.os.Bundle;
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
    private StoryOfBookcase story;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        initView();
        story = (StoryOfBookcase) getIntent().getSerializableExtra("story");
        Glide.with(this).load(story.getLinkimage()).into(imgAvatar);
        txtName.setText(story.getName());
        callApiGetData(Integer.parseInt(story.getId()));
    }

    public void callApiGetData(int id) {
        Call<List<ChapStory>> call = RetrofitClient.getService().getChap(id);
        Callback<List<ChapStory>> callback = new Callback<List<ChapStory>>() {
            @Override
            public void onResponse(Call<List<ChapStory>> call, Response<List<ChapStory>> response) {
                if (response.body() == null || response.body().size() == 0) {

                } else {
                    chapAdapter = new ChapAdapter(ChapActivity.this, response.body());
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
