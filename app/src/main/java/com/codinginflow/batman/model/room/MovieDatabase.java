package com.codinginflow.batman.model.room;

import android.content.Context;

import com.codinginflow.batman.model.model.model_movie_list.Movie;
import com.codinginflow.batman.utils.App;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;

    public abstract MovieDao noteDao();

    public static synchronized MovieDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(App.context.getApplicationContext(),
                    MovieDatabase.class, "movie_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
