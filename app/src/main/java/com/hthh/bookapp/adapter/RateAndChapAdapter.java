package com.hthh.bookapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hthh.bookapp.fragment.ChapFragment;
import com.hthh.bookapp.fragment.RateFragment;

public class RateAndChapAdapter extends FragmentPagerAdapter {
    ChapFragment chapFragment;
    RateFragment rateFragment;
    int idStory;
    String[] pageTitle = new String[]{"Đánh giá", "Chapter"};

    public RateAndChapAdapter(@NonNull FragmentManager fm, int id_story) {
        super(fm);
        this.idStory = id_story;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (rateFragment == null) {
                    rateFragment = new RateFragment();
                    rateFragment.setIdStory(idStory);
                }
                return rateFragment;
            case 1:
                if (chapFragment == null) {
                    chapFragment = new ChapFragment();
                    chapFragment.setIdStory(idStory);
                }
                return chapFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return pageTitle[0];
            case 1:
                return pageTitle[1];
        }
        return super.getPageTitle(position);
    }
}
