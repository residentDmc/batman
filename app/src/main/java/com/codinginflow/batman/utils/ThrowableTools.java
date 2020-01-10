package com.codinginflow.batman.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.codinginflow.batman.R;

import java.util.Objects;

import retrofit2.HttpException;

public class ThrowableTools {

    private static ThrowableTools throwable_error_tools_instance = null;

    // static method to create instance of Singleton class
    public static ThrowableTools getInstance() {
        if (throwable_error_tools_instance == null)
            throwable_error_tools_instance = new ThrowableTools();
        return throwable_error_tools_instance;
    }

    public String getThrowableError(Throwable error) {
        if (NetworkTools.getInstance().isNetworkAvailable()) {
            return App.context.getString(R.string.no_connection);
        } else if (error instanceof HttpException) {
            int code = ((HttpException) error).code();
            if (code == 401) {
                return App.context.getString(R.string.unauthorized);
            } else if (code >= 400 && code < 500) {
                return App.context.getString(R.string.bad_request);
            } else if (code >= 500 && code < 600) {
                return App.context.getString(R.string.server_error);
            }
        }
        return App.context.getString(R.string.unknown_error);
    }
}
