package com.codinginflow.batman.model.room;

import com.codinginflow.batman.model.model.model_movie_list.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(Movie movies);

    @Query("SELECT * FROM movie_table")
    LiveData<List<Movie>> getAllMovie();
}
