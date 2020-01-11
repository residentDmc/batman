package com.codinginflow.batman.utils;

import androidx.room.TypeConverter;

import com.codinginflow.batman.model.model.model_movie_detail.Rating;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ListRatingTypeConverter {

    @TypeConverter
    public static List<Rating> storedStringToRating(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Rating>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ratingToStoredString(List<Rating> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }
}