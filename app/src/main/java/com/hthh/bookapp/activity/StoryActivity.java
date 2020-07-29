package com.hthh.bookapp.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hthh.bookapp.R;
import com.hthh.bookapp.adapter.ViewStoryAdapter;
import com.hthh.bookapp.model.LinkImage;
import com.hthh.bookapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryActivity extends AppCompatActivity {
    private RecyclerView rcvStory;
    private ViewStoryAdapter storyAdapter;
    private int idChap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
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
                    storyAdapter = new ViewStoryAdapter(StoryActivity.this, response.body());
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
