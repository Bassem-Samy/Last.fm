package com.bassem.lastfm.ui.topalbumslisting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bassem.lastfm.R;
import com.bassem.lastfm.models.Album;
import com.bassem.lastfm.ui.BaseFragment;
import com.bassem.lastfm.ui.topalbumslisting.di.DaggerTopAlbumsComponent;
import com.bassem.lastfm.ui.topalbumslisting.di.TopAlbumsModule;
import com.bassem.lastfm.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple Fragment that displays top albums for user
 */
public class TopAlbumsFragment extends BaseFragment implements TopAlbumsView {
    @BindView(R.id.rclr_albums)
    RecyclerView albumsRecyclerView;
    @BindView(R.id.prgrs_main)
    ProgressBar mainprogressBar;
    @Inject
    TopAlbumsPresenter mPresenter;
    private OnFragmentInteractionListener mListener;

    public TopAlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_albums, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTopAlbumsComponent.builder().topAlbumsModule(new TopAlbumsModule(this)).build().inject(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getTopAlbums("rj", Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void searchUserName(String userName) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void updateData(List<Album> topAlbums) {

    }

    public static TopAlbumsFragment newInstance() {
        return new TopAlbumsFragment();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {

        void onAlbumClicked(Album album);
    }
}
