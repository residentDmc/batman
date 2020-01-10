package com.codinginflow.batman.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideTools {

    public static void displayImageOriginal(Context ctx, ImageView img, String url) {
        try {
            Glide.with(ctx).load(url)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img);
        } catch (Exception e) {
        }
    }
}
