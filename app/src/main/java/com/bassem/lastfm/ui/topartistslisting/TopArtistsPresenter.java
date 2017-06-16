package com.bassem.lastfm.ui.topartistslisting;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public interface TopArtistsPresenter {
    void onDestroy();

    void getUserTopArtists(String userName, int limit, String apiKey);
}
