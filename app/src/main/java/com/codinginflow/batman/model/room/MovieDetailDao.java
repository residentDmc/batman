package com.codinginflow.batman.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.codinginflow.batman.model.model.model_movie_detail.MovieDetail;
import com.codinginflow.batman.model.model.model_movie_list.Movie;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(MovieDetail movies);

    @Update
    Completable update(MovieDetail movies);

    @Query("SELECT * FROM movie_detail_table WHERE imdb_id IN (:imdb_id) ")
    MovieDetail getDetailMovie(String imdb_id);
}
