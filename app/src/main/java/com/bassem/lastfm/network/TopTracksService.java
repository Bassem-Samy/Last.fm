package com.bassem.lastfm.network;

import com.bassem.lastfm.models.TopArtistsResponse;
import com.bassem.lastfm.models.TopTracksResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bassem Samy on 6/17/2017.
 */

public interface TopTracksService {
    @GET("?method=user.gettoptracks&format=json")
    Single<TopTracksResponse> getTopTracks(@Query("user") String user, @Query("limit") int limit, @Query("api_key") String apiKey);

}
