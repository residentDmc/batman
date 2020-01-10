package com.codinginflow.batman.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.codinginflow.batman.R;

import java.util.Objects;

import retrofit2.HttpException;

public class NetworkTools {

    private static NetworkTools network_error_tools_instance = null;

    // static method to create instance of Singleton class
    public static NetworkTools getInstance() {
        if (network_error_tools_instance == null)
            network_error_tools_instance = new NetworkTools();
        return network_error_tools_instance;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        return activeNetworkInfo == null;
    }
}
