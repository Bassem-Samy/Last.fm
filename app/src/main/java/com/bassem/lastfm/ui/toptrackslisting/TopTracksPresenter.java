package com.bassem.lastfm.ui.toptrackslisting;

/**
 * Created by Bassem Samy on 6/17/2017.
 */

public interface TopTracksPresenter  {
    void onDestroy();
    void getTopTracks(String userName,int limit,String apiKey);

}
