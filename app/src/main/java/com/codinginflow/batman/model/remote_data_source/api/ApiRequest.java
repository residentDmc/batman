package com.codinginflow.batman.model.remote_data_source.api;

import com.codinginflow.batman.model.remote_data_source.model.model_movie_list.MovieList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.codinginflow.batman.utils.AppConstants.MOVIE_LIST_PARAM;

public interface WebService {

    @GET()
    Observable<MovieList> moveList(@Query(MOVIE_LIST_PARAM) String movieList);
 
}