package com.bassem.lastfm.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bassem.lastfm.ui.artistslisting.ArtistsFragment;

/**
 * Created by Bassem Samy on 6/15/2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final int NUMBER_OF_ITEMS = 3;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ArtistsFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_ITEMS;
    }
}
