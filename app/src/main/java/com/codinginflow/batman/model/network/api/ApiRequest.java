package com.codinginflow.batman.model.network.api;

import com.codinginflow.batman.model.model.model_movie_detail.MovieDetail;
import com.codinginflow.batman.model.model.model_movie_list.MovieList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.codinginflow.batman.utils.AppConstants.API_KEY_PARAM;
import static com.codinginflow.batman.utils.AppConstants.MOVIE_DETAIL_PARAM;
import static com.codinginflow.batman.utils.AppConstants.MOVIE_LIST_PARAM;

public interface ApiRequest {

    @GET(".")
    Observable<MovieList> movieList(@Query(API_KEY_PARAM) String key, @Query(MOVIE_LIST_PARAM) String movieList);

    @GET(".")
    Observable<MovieDetail> movieDetail(@Query(API_KEY_PARAM) String key, @Query(MOVIE_DETAIL_PARAM) String movieDetail);

}