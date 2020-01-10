package com.codinginflow.batman.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.codinginflow.batman.R;

public class GlideTools {

    private static GlideTools glide_tools_instance = null;

    // static method to create instance of Singleton class
    public static GlideTools getInstance()
    {
        if (glide_tools_instance == null)
            glide_tools_instance = new GlideTools();

        return glide_tools_instance;
    }

    public void displayImageOriginal( ImageView img, String url) {
        try {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.ic_load_pic);
            Glide.with(App.context).load(url)
                    .apply(options)
                    .into(img);
        } catch (Exception e) {
            HandelErrorTools.getInstance().setHandelError(e);
        }
    }
}
