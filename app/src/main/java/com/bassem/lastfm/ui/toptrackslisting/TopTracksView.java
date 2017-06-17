package com.bassem.lastfm.ui.toptrackslisting;

import com.bassem.lastfm.models.Track;

import java.util.List;

/**
 * Created by Bassem Samy on 6/17/2017.
 */

public interface TopTracksView {
    void showProgress();

    void hideProgress();

    void showError();

    void updateData(List<Track> tracks);
    void showEmpty();
    void hidEmpty();
}
