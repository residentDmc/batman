package com.codinginflow.batman.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class GlideTools {

    private static GlideTools glide_tools_instance = null;

    // static method to create instance of Singleton class
    public static GlideTools getInstance()
    {
        if (glide_tools_instance == null)
            glide_tools_instance = new GlideTools();

        return glide_tools_instance;
    }

    public void displayImageOriginal(Context ctx, ImageView img, String url) {
        try {
            Glide.with(ctx).load(url)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img);
        } catch (Exception e) {
        }
    }
}
