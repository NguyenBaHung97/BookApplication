package com.hthh.bookapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hthh.bookapp.R;
import com.hthh.bookapp.Utils;
import com.hthh.bookapp.adapter.RateAdapter;
import com.hthh.bookapp.model.Rate;
import com.hthh.bookapp.model.RateData;
import com.hthh.bookapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateFragment extends Fragment {
    private int idStory;
    private TextView txtComment;
    private TextView txtName;
    private RatingBar ratingBar;
    private EditText edtComment;
    private RecyclerView rcvRate;
    private RateAdapter rateAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (R.layout.fragment_rate == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = inflater.inflate(R.layout.fragment_rate, container, false);
            initView(view);
            listener();
            callApiGetRating(idStory);
            return view;
        }
    }

    private void initView(View view) {
        txtComment = view.findViewById(R.id.txtComment);
        txtName = view.findViewById(R.id.txtNameUser);
        ratingBar = view.findViewById(R.id.rating);
        edtComment = view.findViewById(R.id.edtComment);
        rcvRate = view.findViewById(R.id.rcvRate);

        txtName.setText(Utils.getEmail(getActivity()));
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }

    public void callApiGetRating(int idStory) {
        Call<List<Rate>> call = RetrofitClient.getService().getRate(idStory);
        Callback<List<Rate>> callback = new Callback<List<Rate>>() {
            @Override
            public void onResponse(Call<List<Rate>> call, Response<List<Rate>> response) {
                if (response.body() == null) {

                } else {
                    rateAdapter = new RateAdapter(response.body(), getActivity());
                    rcvRate.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rcvRate.setAdapter(rateAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Rate>> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }

    public void callApiRateStory() {
        Call<RateData> call = RetrofitClient.getService().insertRate(String.valueOf(idStory),
                Utils.getUser(getActivity()), Utils.getEmail(getActivity()), edtComment.getText().toString(), ratingBar.getRating());
        Callback<RateData> callback = new Callback<RateData>() {
            @Override
            public void onResponse(Call<RateData> call, Response<RateData> response) {
                if (response.body().getStatus() == 0) {
                    Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), response.body().getData(), Toast.LENGTH_SHORT).show();
                    callApiGetRating(idStory);
                }
            }

            @Override
            public void onFailure(Call<RateData> call, Throwable t) {
                Toast.makeText(getActivity(), "Bình luận lỗi", Toast.LENGTH_SHORT).show();
            }
        };
        call.enqueue(callback);
    }

    public void listener() {
        txtComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callApiRateStory();
            }
        });
    }
}
