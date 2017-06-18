package com.bassem.lastfm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.lang.ref.WeakReference;

/**
 * Helper class that is used to load images
 * it's purpose is to make the code independent of the 3rd party library used to load image, like if we decide to use picasso instead of glide, w will just have to update the usage here and not in every occurrence
 * Created by Bassem Samy on 6/16/2017..
 */

public class ImageLoader {
    public static void loadImage(Context context, String imageUrl, int placeHolderResourceID, ImageView imageView) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        Glide.with(weakReference.get()).load(imageUrl).asBitmap().placeholder(placeHolderResourceID).into(imageView);
    }

    // load image with callbacks
    public static void loadImage(Context context, String imageUrl, int placeHolderResourceID, ImageView imageView, final ImageLoaderCallbacks callbacks) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        Glide.with(weakReference.get()).load(imageUrl).asBitmap().placeholder(placeHolderResourceID)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        if (callbacks != null) {
                            callbacks.onFail(e);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (callbacks != null) {
                            callbacks.onSuccess();
                        }
                        return false;
                    }
                })
                .into(imageView);
    }

    public interface ImageLoaderCallbacks {
        void onSuccess();

        void onFail(Exception e);
    }
}
