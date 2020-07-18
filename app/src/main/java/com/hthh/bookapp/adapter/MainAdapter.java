package com.hthh.bookapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hthh.bookapp.fragment.HomeFragment;

public class MainAdapter extends FragmentPagerAdapter {
    private HomeFragment homeFragment;

    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                return homeFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
