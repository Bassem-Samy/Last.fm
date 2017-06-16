package com.bassem.lastfm.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bassem.lastfm.R;
import com.bassem.lastfm.ui.BaseFragment;
import com.bassem.lastfm.ui.topalbumslisting.TopAlbumsFragment;
import com.bassem.lastfm.ui.topartistslisting.TopArtistsFragment;

/**
 * Created by Bassem Samy on 6/15/2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final int NUMBER_OF_ITEMS = 3;
    private static final int TOP_ARTISTS_INDEX = 0;
    private static final int TOP_ALBUMS_INDEX = 1;
    private static final int TOP_TRACKS_INDEX = 2;
    private String topArtistsTitle;
    private String topTracksTitle;
    private String topAlbumsTitle;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        topArtistsTitle = context.getString(R.string.top_artists_title);
        topTracksTitle = context.getString(R.string.top_tracks_title);
        topAlbumsTitle = context.getString(R.string.top_albums_title);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case TOP_ARTISTS_INDEX:{
               return TopArtistsFragment.newInstance();
            }
            case TOP_ALBUMS_INDEX:{
                return TopAlbumsFragment.newInstance();
            }
        }
        return new TopArtistsFragment();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TOP_ARTISTS_INDEX: {
                return topArtistsTitle;
            }
            case TOP_ALBUMS_INDEX: {

                return topAlbumsTitle;
            }
            case TOP_TRACKS_INDEX: {
                return topTracksTitle;
            }
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_ITEMS;
    }
}
