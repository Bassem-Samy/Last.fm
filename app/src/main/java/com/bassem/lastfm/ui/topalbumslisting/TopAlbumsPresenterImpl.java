package com.bassem.lastfm.ui.topalbumslisting;

import com.bassem.lastfm.models.Album;
import com.bassem.lastfm.models.TopAlbumsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public class TopAlbumsPresenterImpl implements TopAlbumsPresenter {
    Disposable mDisposable;
    TopAlbumsInteractor mInteractor;
    TopAlbumsView mView;

    public TopAlbumsPresenterImpl(TopAlbumsView view, TopAlbumsInteractor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    @Override
    public void getTopAlbums(String userName, int limit, String apiKey) {
        mView.showProgress();
        mView.hidEmpty();
        disposeRequest();
        mDisposable = mInteractor.getTopAlbums(userName, limit, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TopAlbumsResponse, List<Album>>() {
                    @Override
                    public List<Album> apply(@NonNull TopAlbumsResponse topAlbumsResponse) throws Exception {
                        if (topAlbumsResponse != null && topAlbumsResponse.getTopAlbums() != null && topAlbumsResponse.getTopAlbums().getAlbums() != null) {
                            return topAlbumsResponse.getTopAlbums().getAlbums();
                        }
                        return new ArrayList<Album>();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Album>>() {
                    @Override
                    public void accept(@NonNull List<Album> albums) throws Exception {
                        mView.hideProgress();
                        if (albums.size() == 0) {
                            mView.showEmpty();
                        }
                        mView.updateData(albums);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.hideProgress();
                        mView.showError();
                    }
                });
    }

    @Override
    public void onDestroy() {
        disposeRequest();
    }

    private void disposeRequest() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

}
