package com.bassem.lastfm.ui.topartistslisting.di;

import com.bassem.lastfm.ui.topartistslisting.TopARtistsPresenterImpl;
import com.bassem.lastfm.ui.topartistslisting.TopArtistsInteractor;
import com.bassem.lastfm.ui.topartistslisting.TopArtistsInteractorImpl;
import com.bassem.lastfm.ui.topartistslisting.TopArtistsPresenter;
import com.bassem.lastfm.ui.topartistslisting.TopArtistsView;
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
 * Created by Bassem Samy on 6/16/2017.
 */

@Module
public class TopArtistsModule {
    TopArtistsView mView;

    public TopArtistsModule(TopArtistsView view) {
        mView = view;
    }

    // provides the view to create the top artists presenter
    @Singleton
    @Provides
    public TopArtistsView providesTopArtistsView() {
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

    // provides a retrofit instance to create the top artists interactor
    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converter, CallAdapter.Factory adapter) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(adapter)
                .addConverterFactory(converter)
                .build();
    }

    // provides top artists interactor to make an instance of the presenter
    @Singleton
    @Provides
    public TopArtistsInteractor providesTopArtistsInteractor(Retrofit retrofit) {
        return new TopArtistsInteractorImpl(retrofit);
    }

    // provides top artists presenter
    @Singleton
    @Provides
    public TopArtistsPresenter providesTopArtistsPresenter(TopArtistsView view, TopArtistsInteractor interactor) {
        return new TopARtistsPresenterImpl(view, interactor);

    }
}

