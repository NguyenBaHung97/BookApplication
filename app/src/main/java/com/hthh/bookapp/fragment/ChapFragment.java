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
import com.hthh.bookapp.Utils;
import com.hthh.bookapp.activity.ChapActivity;
import com.hthh.bookapp.activity.StoryActivity;
import com.hthh.bookapp.adapter.ChapAdapter;
import com.hthh.bookapp.model.ChapStory;
import com.hthh.bookapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapFragment extends Fragment {
    private RecyclerView rcvChap;
    private ChapAdapter chapAdapter;
    private int idStory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (R.layout.fragment_chap == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = inflater.inflate(R.layout.fragment_chap, container, false);
            initView(view);
            callApiGetData(idStory);
            return view;
        }
    }

    private void initView(View view) {
        rcvChap = view.findViewById(R.id.rcvChap);
    }

    public void callApiGetData(int id) {
        Utils.showLoadingDialog(getActivity());
        Call<List<ChapStory>> call = RetrofitClient.getService().getChap(id);
        Callback<List<ChapStory>> callback = new Callback<List<ChapStory>>() {
            @Override
            public void onResponse(Call<List<ChapStory>> call, Response<List<ChapStory>> response) {
                Utils.hideLoadingDialog();
                if (response.body() == null || response.body().size() == 0) {

                } else {
                    chapAdapter = new ChapAdapter(getActivity(), response.body());
                    chapAdapter.setOnClickItemListener(new ChapAdapter.OnClickItemListener() {
                        @Override
                        public void onClicked(int id) {
                            Intent intent = new Intent(getActivity(), StoryActivity.class);
                            intent.putExtra("id_chap", id);
                            startActivity(intent);
                        }
                    });
                    rcvChap.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rcvChap.setAdapter(chapAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ChapStory>> call, Throwable t) {
                Utils.hideLoadingDialog();
            }
        };
        call.enqueue(callback);
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }
}
