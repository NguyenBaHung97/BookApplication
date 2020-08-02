package com.hthh.bookapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hthh.bookapp.fragment.OneStoryFragment;
import com.hthh.bookapp.fragment.ThreeStoryFragment;
import com.hthh.bookapp.fragment.TwoStoryFragment;

import java.util.List;

public class ClassifyAdapter extends FragmentPagerAdapter {
    private OneStoryFragment oneStoryFragment;
    private TwoStoryFragment twoStoryFragment;
    private ThreeStoryFragment threeStoryFragment;

    private List<String> list;


    public ClassifyAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (oneStoryFragment == null) {
                    oneStoryFragment = new OneStoryFragment();
                }
                return oneStoryFragment;
            case 1:
                if (twoStoryFragment == null) {
                    twoStoryFragment = new TwoStoryFragment();
                }
                return twoStoryFragment;
            case 2:
                if (threeStoryFragment == null) {
                    threeStoryFragment = new ThreeStoryFragment();
                }
                return threeStoryFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
