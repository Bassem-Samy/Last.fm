package com.bassem.lastfm.ui.topartistslisting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bassem.lastfm.R;
import com.bassem.lastfm.adapters.TopArtistsAdapter;
import com.bassem.lastfm.models.Artist;
import com.bassem.lastfm.ui.BaseFragment;
import com.bassem.lastfm.ui.topartistslisting.di.DaggerTopArtistsComponent;
import com.bassem.lastfm.ui.topartistslisting.di.TopArtistsModule;
import com.bassem.lastfm.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopArtistsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TopArtistsFragment extends BaseFragment implements TopArtistsView {
    @BindView(R.id.rclr_artists)
    RecyclerView artistsRecyclerView;
    @BindView(R.id.prgrs_main)
    ProgressBar mainProgressBar;
    private OnFragmentInteractionListener mListener;
    @Inject
    TopArtistsPresenter mPresenter;
    TopArtistsAdapter mAdapter;

    public TopArtistsFragment() {
        // Required empty public constructor
    }

    @Override
    protected void searchUserName(String userName) {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artists, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTopArtistsComponent.builder().topArtistsModule(new TopArtistsModule(this)).build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getUserTopArtists("rj", Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //      mListener.onFragmentInteraction(uri);
        }
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
    public void showProgress() {
        mainProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mainProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateData(List<Artist> topArtists) {
        if (mAdapter == null) {
            mAdapter = new TopArtistsAdapter(topArtists, getContext(), onArtistclickedListener);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            artistsRecyclerView.setLayoutManager(linearLayoutManager);
            artistsRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.setDataset(topArtists);
        }
    }

    View.OnClickListener onArtistclickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    @Override
    public void showError() {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        //void onFragmentInteraction(Uri uri);
    }
}
