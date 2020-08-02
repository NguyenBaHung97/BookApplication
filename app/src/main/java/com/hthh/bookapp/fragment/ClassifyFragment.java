package com.hthh.bookapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hthh.bookapp.R;
import com.hthh.bookapp.adapter.ClassifyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClassifyFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager vpClassify;
    private ClassifyAdapter classifyAdapter;
    private List<String> strings;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (R.layout.fragment_classify == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            View view = inflater.inflate(R.layout.fragment_classify, container, false);
            initView(view);
            setUpViewPager();
            return view;
        }
    }

    public void initView(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        vpClassify = view.findViewById(R.id.vpClassify);
    }

    public void setUpViewPager() {
        classifyAdapter = new ClassifyAdapter(getActivity().getSupportFragmentManager());
        strings = new ArrayList<>();
        strings.add("Tình cảm");
        strings.add("Trinh thám");
        strings.add("Hành động");
        classifyAdapter.setList(strings);
        vpClassify.setAdapter(classifyAdapter);
        tabLayout.setupWithViewPager(vpClassify);
    }

}
