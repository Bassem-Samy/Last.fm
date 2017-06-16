package com.bassem.lastfm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bassem.lastfm.R;
import com.bassem.lastfm.models.Artist;
import com.bassem.lastfm.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bassem Samy on 6/16/2017.
 */

public class TopArtistsAdapter extends RecyclerView.Adapter<TopArtistsAdapter.ViewHolder> {
    private List<Artist> mDataset;
    private View.OnClickListener mOnClickListener;
    private Context mContext;

    public TopArtistsAdapter(List<Artist> items, Context context, View.OnClickListener onItemClickListener) {
        this.mDataset = items;
        this.mOnClickListener = onItemClickListener;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageLoader.loadImage(mContext, mDataset.get(position).getImageUrl(), R.drawable.default_artist, holder.artistImageView);
        holder.artistTextView.setText(mDataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public void setDataset(List<Artist> items) {
        this.mDataset = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_artist)
        ImageView artistImageView;
        @BindView(R.id.txt_artist_name)
        TextView artistTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
