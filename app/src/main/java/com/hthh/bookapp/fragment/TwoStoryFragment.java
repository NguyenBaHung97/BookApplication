package com.hthh.bookapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hthh.bookapp.R;
import com.hthh.bookapp.activity.ChapActivity;
import com.hthh.bookapp.adapter.StoryAdapter;
import com.hthh.bookapp.model.Story;
import com.hthh.bookapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwoStoryFragment extends Fragment {
    private RecyclerView rcvTwoStory;
    private StoryAdapter storyAdapter;
    private List<Story> stories;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (R.layout.fragment_one_story == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = inflater.inflate(R.layout.fragment_two_story, container, false);
            initView(view);
            callApiGetData(2);
            return view;
        }
    }

    public void initView(View view) {
        rcvTwoStory = view.findViewById(R.id.rcvTwoStory);
        stories = new ArrayList<>();
    }

    public void callApiGetData(int id_type) {
        Call<List<Story>> call = RetrofitClient.getService().getStoryByType(id_type);
        Callback<List<Story>> callback = new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                if (response.body() == null || response.body().size() == 0) {

                } else {
                    stories.clear();
                    stories.addAll(response.body());
                    storyAdapter = new StoryAdapter(getActivity(), stories,"Trinh thám");
                    storyAdapter.setOnClickItemListener(new StoryAdapter.OnClickItemListener() {
                        @Override
                        public void onClicked(Story story) {
                            Intent intent = new Intent(getActivity(), ChapActivity.class);
                            intent.putExtra("Story", story);
                            startActivity(intent);
                        }
                    });
                    rcvTwoStory.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rcvTwoStory.setAdapter(storyAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }

}
