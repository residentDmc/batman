package com.codinginflow.batman.model.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.codinginflow.batman.model.model.model_movie_list.*;
import com.codinginflow.batman.model.network.api.ApiRequest;
import com.codinginflow.batman.model.network.retrofit.RetrofitRequest;
import com.codinginflow.batman.model.room.MovieDao;
import com.codinginflow.batman.model.room.MovieDatabase;
import com.codinginflow.batman.utils.NetworkTools;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieListRepository {

    private static final String TAG = MovieListRepository.class.getSimpleName();
    private ApiRequest apiRequest;
    private MovieDatabase appDatabase;
    private MovieDao movieDao;
    private List<Movie> allMovie;

    public MovieListRepository() {
        appDatabase = MovieDatabase.getInstance();
        movieDao = appDatabase.movieDao();
        allMovie = movieDao.getAllMovie();
        apiRequest = RetrofitRequest.getApiRequest();
    }


    @SuppressLint("CheckResult")
    public LiveData<Object> getMovieListArticles(String key, String movieListParam) {
        final MutableLiveData<Object> data = new MutableLiveData<>();
        if (NetworkTools.getInstance().isNetworkAvailable() && !allMovie.isEmpty())
            data.setValue(allMovie);
        else
            apiRequest.movieList(key, movieListParam).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(movieListNetwork -> {
                        if (movieListNetwork != null) {
                            if (allMovie.isEmpty()) movieDao.insert(movieListNetwork.getMovies()).subscribeOn(Schedulers.io()).subscribe();
                            else movieDao.update(movieListNetwork.getMovies()).subscribeOn(Schedulers.io()).subscribe();
                            data.setValue(movieListNetwork.getMovies());
                        }
                    }, data::setValue);
        return data;
    }
}
