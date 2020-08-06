package com.hthh.bookapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hthh.bookapp.R;
import com.hthh.bookapp.Utils;
import com.hthh.bookapp.activity.ChapActivity;
import com.hthh.bookapp.adapter.StoryAdapter;
import com.hthh.bookapp.model.Story;
import com.hthh.bookapp.model.StoryData;
import com.hthh.bookapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneStoryFragment extends Fragment {
    private RecyclerView rcvOneStory;
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
            View view = inflater.inflate(R.layout.fragment_one_story, container, false);
            initView(view);
            callApiGetData(1);
            return view;
        }
    }

    public void initView(View view) {
        rcvOneStory = view.findViewById(R.id.rcvOneStory);
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
                    storyAdapter = new StoryAdapter(getActivity(), stories, "Tình cảm");
                    storyAdapter.setOnClickItemListener(new StoryAdapter.OnClickItemListener() {
                        @Override
                        public void onClicked(Story story) {
                            Intent intent = new Intent(getActivity(), ChapActivity.class);
                            intent.putExtra("Story", story);
                            startActivity(intent);
                        }

                        @Override
                        public void onLongClicked(final Story story) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("Thông báo");
                            builder.setMessage("Bạn có muốn thêm: " + story.getName_story() + " vào tủ truyện không ?");
                            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    callApiAddStory(story);
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });
                    rcvOneStory.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rcvOneStory.setAdapter(storyAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
            }
        };
        call.enqueue(callback);

    }

    public void callApiAddStory(Story story) {
        Utils.showLoadingDialog(getActivity());
        Call<StoryData> call = RetrofitClient.getService().insertStory(story.getId(), Utils.getUser(getActivity()));
        Callback<StoryData> callback = new Callback<StoryData>() {
            @Override
            public void onResponse(Call<StoryData> call, Response<StoryData> response) {
                Utils.hideLoadingDialog();
                if (response.body().getStatus() == 0) {
                    Toast.makeText(getActivity(),response.body().getData(),Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),response.body().getData(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StoryData> call, Throwable t) {
                Utils.hideLoadingDialog();
                Toast.makeText(getActivity(),"Thêm truyện thất bại",Toast.LENGTH_SHORT).show();
            }
        };
        call.enqueue(callback);
    }
}
