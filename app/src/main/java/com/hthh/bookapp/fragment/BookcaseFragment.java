package com.hthh.bookapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hthh.bookapp.R;
import com.hthh.bookapp.Utils;
import com.hthh.bookapp.activity.ChapActivity;
import com.hthh.bookapp.adapter.BookcaseAdapter;
import com.hthh.bookapp.model.StoryData;
import com.hthh.bookapp.model.StoryOfBookcase;
import com.hthh.bookapp.network.APIController;
import com.hthh.bookapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookcaseFragment extends Fragment {
    RecyclerView rcvBookcase;
    BookcaseAdapter bookcaseAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (R.layout.fragment_book_case == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = inflater.inflate(R.layout.fragment_book_case, container, false);
            initView(view);
            callApiGetData();
            return view;
        }
    }

    public void callApiGetData() {
        Call<List<StoryOfBookcase>> call = RetrofitClient.getService().getStory(Integer.parseInt(Utils.getUser(getActivity())));
        Callback<List<StoryOfBookcase>> callback = new Callback<List<StoryOfBookcase>>() {
            @Override
            public void onResponse(Call<List<StoryOfBookcase>> call, Response<List<StoryOfBookcase>> response) {
                if (response.body() == null || response.body().size() == 0) {

                } else {
                    bookcaseAdapter = new BookcaseAdapter(getActivity(), response.body());
                    bookcaseAdapter.setClickItemListener(new BookcaseAdapter.OnClickItemListener() {
                        @Override
                        public void onClickItem(StoryOfBookcase storyOfBookcase) {
                            Intent intent = new Intent(getActivity(), ChapActivity.class);
                            intent.putExtra("StoryOfBookcase", storyOfBookcase);
                            startActivity(intent);
                        }

                        @Override
                        public void onLongClicked(final StoryOfBookcase storyOfBookcase) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("Thông báo");
                            builder.setMessage("Bạn có muốn xóa: " + storyOfBookcase.getName() + " khỏi tủ truyện không ?");
                            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    callApiDelete(storyOfBookcase.getId(), Utils.getUser(getActivity()));
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });
                    rcvBookcase.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rcvBookcase.setAdapter(bookcaseAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<StoryOfBookcase>> call, Throwable t) {
                Log.d("1122", "onFailure: " + t.getMessage());
            }
        };
        call.enqueue(callback);
    }

    public void callApiDelete(String id_story, String id_user) {
        Utils.showLoadingDialog(getActivity());
        Call<StoryData> call = RetrofitClient.getService().deleteStory(id_story, id_user);
        Callback<StoryData> callback = new Callback<StoryData>() {
            @Override
            public void onResponse(Call<StoryData> call, Response<StoryData> response) {
                Utils.hideLoadingDialog();
                if (response.body().getStatus() == 0) {
                    Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StoryData> call, Throwable t) {
                Utils.hideLoadingDialog();
                Toast.makeText(getActivity(), "Xóa truyện thất bại", Toast.LENGTH_SHORT).show();
            }
        };
        call.enqueue(callback);
    }

    public void initView(View view) {
        rcvBookcase = view.findViewById(R.id.rcvBookcase);
    }

}
