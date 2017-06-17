package com.bassem.lastfm.ui.toptrackslisting.di;

import com.bassem.lastfm.ui.toptrackslisting.TopTracksInteractor;
import com.bassem.lastfm.ui.toptrackslisting.TopTracksInteractorImpl;
import com.bassem.lastfm.ui.toptrackslisting.TopTracksPresenter;
import com.bassem.lastfm.ui.toptrackslisting.TopTracksPresenterImpl;
import com.bassem.lastfm.ui.toptrackslisting.TopTracksView;
import com.bassem.lastfm.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bassem Samy on 6/17/2017.
 */
@Module
public class TopTracksModule {
    TopTracksView mView;

    public TopTracksModule(TopTracksView view) {
        mView = view;
    }

    // provides the view to create the top tracks presenter
    @Singleton
    @Provides
    public TopTracksView providesTopTracksView() {
        return this.mView;
    }

    // provides a converter factory to create the retrofit instance
    @Singleton
    @Provides
    public Converter.Factory providesConverterFactory() {
        return GsonConverterFactory.create();
    }

    // provides a call adapter factory needed to integrate rxjava with retrofit
    @Singleton
    @Provides
    public CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    // provides a retrofit instance to create the top tracks interactor
    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converter, CallAdapter.Factory adapter) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(adapter)
                .addConverterFactory(converter)
                .build();
    }

    // provides top tracks interactor to make an instance of the presenter
    @Singleton
    @Provides
    public TopTracksInteractor providesTopTopTracksInteractor(Retrofit retrofit) {
        return new TopTracksInteractorImpl(retrofit);
    }

    // provides top albums presenter
    @Singleton
    @Provides
    public TopTracksPresenter providesTopTracksPresenter(TopTracksView view, TopTracksInteractor interactor) {
        return new TopTracksPresenterImpl(view, interactor);

    }
}
