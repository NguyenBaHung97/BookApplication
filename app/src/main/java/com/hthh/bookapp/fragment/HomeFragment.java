package com.hthh.bookapp.fragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.hthh.bookapp.R;
import com.hthh.bookapp.adapter.BookAdapter;
import com.hthh.bookapp.adapter.SlideAdapter;
import com.hthh.bookapp.model.Book;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {
    private List<Integer> integerList;
    private List<Book> books;
    private SlideAdapter adapter;
    private BookAdapter bookAdapter;
    private ViewPager vpSlide;
    private RecyclerView rcvBook;
    private ImageView imgBook;
    private TextView txtName;
    private ImageView imgBook1;
    private TextView txtName1;
    private ImageView imgBook2;
    private TextView txtName2;
    private ImageView imgBook3;
    private TextView txtName3;
    private CircleIndicator indicator;

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
            setUpProposal();
            setSlideAdapter();
            setBookAdapter();
            return view;
        }


    }

    private void initView(View view) {
        vpSlide = view.findViewById(R.id.vpSlide);
        rcvBook = view.findViewById(R.id.rcvBook);
        imgBook = view.findViewById(R.id.imgAvatar);
        imgBook1 = view.findViewById(R.id.imgAvatar1);
        imgBook2 = view.findViewById(R.id.imgAvatar2);
        imgBook3 = view.findViewById(R.id.imgAvatar3);
        txtName = view.findViewById(R.id.txtName);
        txtName1 = view.findViewById(R.id.txtName1);
        txtName2 = view.findViewById(R.id.txtName2);
        txtName3 = view.findViewById(R.id.txtName3);
        indicator = view.findViewById(R.id.indicator);
    }

    private void setUpProposal() {
        imgBook.setImageDrawable(getResources().getDrawable(R.drawable.book_test));
        imgBook1.setImageDrawable(getResources().getDrawable(R.drawable.book_test));
        imgBook2.setImageDrawable(getResources().getDrawable(R.drawable.book_test));
        imgBook3.setImageDrawable(getResources().getDrawable(R.drawable.book_test));

        txtName.setText("Tấm cám");
        txtName1.setText("Tấm cám");
        txtName2.setText("Tấm cám");
        txtName3.setText("Tấm cám");
    }

    public void setSlideAdapter() {
        integerList = new ArrayList<>();
        integerList.add(R.drawable.slide_test);
        integerList.add(R.drawable.slide_test);
        integerList.add(R.drawable.slide_test);
        integerList.add(R.drawable.slide_test);
        integerList.add(R.drawable.slide_test);

        adapter = new SlideAdapter(getActivity(), integerList);
        vpSlide.setAdapter(adapter);
        indicator.setViewPager(vpSlide);
    }

    public void setBookAdapter() {
        books = new ArrayList<>();
        books.add(new Book(R.drawable.book_test, "Tấm cám"));
        books.add(new Book(R.drawable.book_test, "Tấm cám"));
        books.add(new Book(R.drawable.book_test, "Tấm cám"));
        books.add(new Book(R.drawable.book_test, "Tấm cám"));
        books.add(new Book(R.drawable.book_test, "Tấm cám"));
        books.add(new Book(R.drawable.book_test, "Tấm cám"));
        books.add(new Book(R.drawable.book_test, "Tấm cám"));

        bookAdapter = new BookAdapter(getActivity(), books);
        rcvBook.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rcvBook.setAdapter(bookAdapter);
    }


}
