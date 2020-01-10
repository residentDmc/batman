package com.codinginflow.batman.utils;

import android.content.Context;
import android.widget.Toast;

public class HandelErrorTools {

    private static HandelErrorTools handel_error_tools_instance = null;

    // static method to create instance of Singleton class
    public static HandelErrorTools getInstance()
    {
        if (handel_error_tools_instance == null)
            handel_error_tools_instance = new HandelErrorTools();

        return handel_error_tools_instance;
    }

    public void setHandelError(Exception e) {
        Toast.makeText(App.context, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
