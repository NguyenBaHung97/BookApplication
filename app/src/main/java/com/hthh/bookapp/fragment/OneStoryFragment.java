package com.hthh.bookapp.fragment;

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
import com.hthh.bookapp.adapter.StoryAdapter;
import com.hthh.bookapp.model.Story;

import java.util.ArrayList;
import java.util.List;

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
            setUpAdapter();
            return view;
        }
    }

    public void initView(View view) {
        rcvOneStory = view.findViewById(R.id.rcvOneStory);
    }

    public void setUpAdapter() {
        stories = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        strings.add("Tình cảm");
        strings.add("Hành động");
        stories.add(new Story(R.drawable.book_test, "Tấm cám", strings));
        stories.add(new Story(R.drawable.book_test, "Tấm cám", strings));
        stories.add(new Story(R.drawable.book_test, "Tấm cám", strings));
        stories.add(new Story(R.drawable.book_test, "Tấm cám", strings));
        stories.add(new Story(R.drawable.book_test, "Tấm cám", strings));
        storyAdapter = new StoryAdapter(getActivity(), stories);
        rcvOneStory.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcvOneStory.setAdapter(storyAdapter);
    }


}
