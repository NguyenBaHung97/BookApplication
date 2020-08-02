package com.hthh.bookapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.hthh.bookapp.R;
import com.hthh.bookapp.activity.ChapActivity;
import com.hthh.bookapp.adapter.BookAdapter;
import com.hthh.bookapp.adapter.OfferAdapter;
import com.hthh.bookapp.adapter.SlideAdapter;
import com.hthh.bookapp.adapter.StoryAdapter;
import com.hthh.bookapp.api.AddStory;
import com.hthh.bookapp.api.Apirun;
import com.hthh.bookapp.model.Book;
import com.hthh.bookapp.model.Story;
import com.hthh.bookapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements AddStory {
    private List<Integer> integerList;
    private SlideAdapter adapter;
    private ViewPager vpSlide;
    private CircleIndicator indicator;

    private RecyclerView rcvBook;
    private BookAdapter bookAdapter;
    private List<Story> stories = new ArrayList<>();

    private RecyclerView rcvOffer;
    private OfferAdapter offerAdapter;
    private List<Story> storiesOffer = new ArrayList<>();

    private RecyclerView rcvBookHot;
    private BookAdapter bookHotAdapter;
    private List<Story> storiesHot = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (R.layout.fragment_home == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = inflater.inflate(R.layout.fragment_home, container, false);
            initView(view);
            setSlideAdapter();
            callApiGetData(5);
            callApiGetDataHot(6);
            callApiGetDataOffer(7);
            return view;
        }


    }

    private void initView(View view) {
        vpSlide = view.findViewById(R.id.vpSlide);
        rcvBook = view.findViewById(R.id.rcvBook);
        rcvBookHot = view.findViewById(R.id.rcvBookHot);
        rcvOffer = view.findViewById(R.id.rcvOffer);


        indicator = view.findViewById(R.id.indicator);
    }


    public void setSlideAdapter() {
        integerList = new ArrayList<>();
        integerList.add(R.drawable.poster_doraemon);
        integerList.add(R.drawable.poster_one_piece);
        integerList.add(R.drawable.poster_naruto);
        integerList.add(R.drawable.slide_test);
        integerList.add(R.drawable.slide_test);

        adapter = new SlideAdapter(getActivity(), integerList);
        vpSlide.setAdapter(adapter);
        indicator.setViewPager(vpSlide);
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
                    bookAdapter = new BookAdapter(getActivity(), stories);
                    bookAdapter.setOnClickItemListener(new BookAdapter.OnClickItemListener() {
                        @Override
                        public void onClicked(Story story) {
                            Intent intent = new Intent(getActivity(), ChapActivity.class);
                            intent.putExtra("Story", story);
                            startActivity(intent);
                        }

                        @Override
                        public void onLongClick(final Story story) {
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
                                    String sql = "INSERT INTO `story_talk` (`id`, `id_story`, `id_user`) VALUES (NULL, '" + 6 + "', '1')";
                                    new Apirun(sql, HomeFragment.this).execute();
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });
                    rcvBook.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    rcvBook.setAdapter(bookAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }

    public void callApiGetDataHot(int id_type) {
        Call<List<Story>> call = RetrofitClient.getService().getStoryByType(id_type);
        Callback<List<Story>> callback = new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                if (response.body() == null || response.body().size() == 0) {


                } else {
                    storiesHot.clear();
                    storiesHot.addAll(response.body());
                    bookHotAdapter = new BookAdapter(getActivity(), storiesHot);
                    bookHotAdapter.setOnClickItemListener(new BookAdapter.OnClickItemListener() {
                        @Override
                        public void onClicked(Story story) {
                            Intent intent = new Intent(getActivity(), ChapActivity.class);
                            intent.putExtra("Story", story);
                            startActivity(intent);
                        }

                        @Override
                        public void onLongClick(Story story) {

                        }
                    });
                    rcvBookHot.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    rcvBookHot.setAdapter(bookHotAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }

    public void callApiGetDataOffer(int id_type) {
        Call<List<Story>> call = RetrofitClient.getService().getStoryByType(id_type);
        Callback<List<Story>> callback = new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                if (response.body() == null || response.body().size() == 0) {

                } else {
                    storiesOffer.clear();
                    storiesOffer.addAll(response.body());
                    offerAdapter = new OfferAdapter(getActivity(), storiesOffer);
                    offerAdapter.setClickItemListener(new OfferAdapter.OnClickItemListener() {
                        @Override
                        public void onClickItem(Story story) {
                            Intent intent = new Intent(getActivity(), ChapActivity.class);
                            intent.putExtra("Story", story);
                            startActivity(intent);
                        }

                        @Override
                        public void onClickLong(Story story) {

                        }
                    });
                    rcvOffer.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rcvOffer.setAdapter(offerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {

            }
        };
        call.enqueue(callback);

    }


    @Override
    public void start() {

    }

    @Override
    public void finish(String data) {

    }

    @Override
    public void error() {

    }
}
