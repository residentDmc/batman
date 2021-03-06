package com.codinginflow.batman.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.codinginflow.batman.model.model.model_movie_list.Movie;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(List<Movie> movies);

    @Update
    Completable update(List<Movie> movies);

    @Query("SELECT * FROM movie_table")
    List<Movie> getAllMovie();
}
