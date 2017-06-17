package com.bassem.lastfm.ui.toptrackslisting;

import com.bassem.lastfm.models.TopAlbumsResponse;
import com.bassem.lastfm.models.TopTracksResponse;
import com.bassem.lastfm.network.TopAlbumsService;
import com.bassem.lastfm.network.TopTracksService;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Bassem Samy on 6/17/2017.
 */

public class TopTracksInteractorImpl implements TopTracksInteractor {
    Retrofit mRetrofit;

    public TopTracksInteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey) {
        return mRetrofit.create(TopTracksService.class).getTopTracks(userName, limit, apiKey);
    }
}
