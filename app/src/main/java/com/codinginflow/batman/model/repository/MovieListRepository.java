package com.codinginflow.batman.model.repository;

import android.annotation.SuppressLint;
import android.app.Application;

import com.codinginflow.batman.model.model.model_movie_list.*;
import com.codinginflow.batman.model.network.api.ApiRequest;
import com.codinginflow.batman.model.network.retrofit.RetrofitRequest;
import com.codinginflow.batman.model.room.MovieDao;
import com.codinginflow.batman.model.room.MovieDatabase;
import com.codinginflow.batman.utils.App;
import com.codinginflow.batman.utils.NetworkTools;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieListRepository {

    private static final String TAG = MovieListRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public MovieListRepository() {
        apiRequest = RetrofitRequest.getApiRequest();
    }

    @SuppressLint("CheckResult")
    public LiveData<Object> getMovieListArticles(String key, String movieListParam) {
        final MutableLiveData<Object> data = new MutableLiveData<>();
        apiRequest.movieList(key, movieListParam).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList -> {
                    if (movieList != null) data.setValue(movieList.getMovies());
                }, data::setValue);
        return data;
    }
}
