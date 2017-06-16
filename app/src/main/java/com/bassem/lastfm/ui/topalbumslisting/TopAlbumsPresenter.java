package com.bassem.lastfm.ui.topalbumslisting;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public interface TopAlbumsPresenter {
    void onDestroy();
    void getTopAlbums(String userName,int limit,String apiKey);
}
