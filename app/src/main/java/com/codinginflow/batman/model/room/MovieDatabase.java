package com.codinginflow.batman.model.room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.codinginflow.batman.model.model.model_movie_detail.MovieDetail;
import com.codinginflow.batman.model.model.model_movie_list.Movie;
import com.codinginflow.batman.utils.App;

import static com.codinginflow.batman.utils.AppConstants.MOVIE;

@Database(entities = {Movie.class, MovieDetail.class,}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;

    public abstract MovieDao movieDao();

    public abstract MovieDetailDao movieDetailDao();

    public static synchronized MovieDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(App.context.getApplicationContext(),
                    MovieDatabase.class, MOVIE)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
